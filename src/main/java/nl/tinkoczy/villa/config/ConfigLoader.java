package nl.tinkoczy.villa.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.util.SystemUtil;

/**
 * Normally this class is used through the {@link ConfigFacade}, this class
 * explains the usage of the public functions.
 */
public class ConfigLoader {

	final static Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

	/**
	 * Map from enum config set to value. The generic type of the key should
	 * reflect this, but java doesn't support the & (generics) operator at this
	 * place.
	 */
	private final Map<ConfigItemEnum, Value> configValues = new HashMap<ConfigItemEnum, Value>();

	/**
	 * List with all the (human readable) errors.
	 */
	private final List<String> errors = new ArrayList<String>();

	/**
	 * Loads a set of configurations based on the given enum class.
	 *
	 * Errors are not reported but will be returned by {@link #getErrors()}. If
	 * there are no errors reported the {@link #getValue(Enum)} will report a
	 * valid value object when called with an element from this list. All
	 * elements from the {@link ConfigItemEnum} are added to the internal map
	 * that stores all the configuration elements, so this function can be
	 * called several times with different elements.
	 *
	 * @param configSet
	 *            The enum that contains all configurable files.
	 * @param url
	 *            The url that locates the resource for the configuration.
	 */
	public <E extends Enum<?> & ConfigItemEnum> void loadConfig(final Class<E> configSet, final URL url) {
		Properties p = loadPropertiesFile(url);
		processLoadedProperties(p, configSet);
	}

	/**
	 * Loads a set of configurations based on the given enum class. This method
	 * uses the provided properties-object as the resource for the
	 * configuration.<br>
	 *
	 * Errors are not reported but will be returned by {@link #getErrors()}. If
	 * there are no errors reported the {@link #getValue(Enum)} will report a
	 * valid value object when called with an element from this list. All
	 * elements from the {@link ConfigItemEnum} are added to the internal map
	 * that stores all the configuration elements, so this function can be
	 * called several times with different elements.
	 *
	 * @param configSet
	 *            The enum that contains all configurable files.
	 * @param properties
	 *            {@link Properties}-object to load the configuration from.
	 */
	public <E extends Enum<?> & ConfigItemEnum> void loadConfig(final Class<E> configSet, final Properties properties) {
		processLoadedProperties(properties, configSet);
	}

	/**
	 * @param properties
	 *            loaded properties
	 * @param configSet
	 *            The enum that contains all loadable properties.
	 */
	private <E extends Enum<?> & ConfigItemEnum> void processLoadedProperties(final Properties properties,
			final Class<E> configSet) {
		Map<String, String> propertiesMap = propertiesToMap(properties);

		if (logger.isDebugEnabled()) {
			logger.debug("Loading properties from map: " + propertiesMap.toString());
		}

		for (E element : configSet.getEnumConstants()) {
			if (configValues.containsKey(element)) {
				recordError(element + ": was already added before");
			} else {
				loadPropertyElement(propertiesMap, element);
			}
		}

		for (String key : propertiesMap.keySet()) {
			recordError("Property " + key + " not found in config-set " + configSet.getName());
		}
	}

	/**
	 * This loads a single element from the properties map into the internal map
	 * of values. After processing the element it iwll be removed from the
	 * propertiesMap.
	 *
	 * @param <E>
	 * @param propertiesMap
	 *
	 * @param element
	 *            Het te lezen element
	 */
	private <E extends Enum<?> & ConfigItemEnum> void loadPropertyElement(final Map<String, String> propertiesMap,
			final E element) {
		String propertyName = SystemUtil.convertEnumToProperty(element.name());

		logger.debug("Loading property: " + propertyName);

		if (propertiesMap.containsKey(propertyName)) {
			// Property is set
			try {
				Value value = element.getType().parseValue(propertiesMap.get(propertyName));
				configValues.put(element, value);
			} catch (ConfigFormatException e) {
				recordError(element + ": " + ExceptionUtils.getStackTrace(e));
				configValues.put(element, element.getDefaultValue());
			}

			// Property is set, so remove it from the map.
			propertiesMap.remove(propertyName);
		} else {
			// Property is not set
			configValues.put(element, element.getDefaultValue());
			logger.debug("Property not set: " + propertyName);
		}
	}

	/**
	 * Returns the configured value of the given config element. The type of the
	 * Value object correspondents with the type the
	 *
	 * @param config
	 *            value
	 * @throws java.lang.IllegalStateException
	 *             Thrown if the 'config' element was not loaded.
	 * @return a value object.
	 */
	public <E extends Enum<?> & ConfigItemEnum> Value getValue(final E config) {
		if (!configValues.containsKey(config)) {
			throw new IllegalStateException("Config element was not loaded: " + config);
		}

		return configValues.get(config);
	}

	/**
	 * Returns the name and value of each item in a particular
	 * {@link ConfigItemEnum} of which its value was overwritten during startup.
	 *
	 * @param <E>
	 * @param configSet
	 * @return Properties with name/value pairs of all modified items in the
	 *         provided {@link ConfigItemEnum} set.
	 */
	public <E extends Enum<?> & ConfigItemEnum> Properties getModifiedValuesAsProperties(final Class<E> configSet) {
		Properties props = new Properties();

		for (E item : configSet.getEnumConstants()) {
			Value value = getValue(item);

			// Compare the actual {@link Value} objects, not their String
			// representations.
			// This way, if a Value is overwritten, even with the same value as
			// the default, the objects shall differ
			// and the value is returned as "modified".
			if (item.getDefaultValue() != value) {
				props.setProperty(SystemUtil.convertEnumToProperty(item.name()), value.convertToString());
			}
		}

		return props;
	}

	/**
	 * <p>
	 * Getter for the field <code>errors</code>.
	 * </p>
	 *
	 * @return A copy of the list of error messages found sofar. These are in a
	 *         humane readable format
	 */
	public List<String> getErrors() {
		return new ArrayList<String>(errors);
	}

	/**
	 * Try to load a properties file from the given url
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private Properties loadPropertiesFile(final URL url) {
		Properties properties = new Properties();

		try {

			InputStream stream = url.openStream();
			properties.load(stream);
		} catch (IOException e) {
			recordError("Error loading: " + url + ": " + e);
		}
		return properties;
	}

	/**
	 * Convert the properties object to a map.
	 *
	 * @param properties
	 * @return A map with as key the property name and as value the property
	 *         value
	 */
	private Map<String, String> propertiesToMap(final Properties properties) {
		Map<String, String> propertiesMap = new HashMap<String, String>();
		for (String property : properties.stringPropertyNames()) {
			propertiesMap.put(property, properties.getProperty(property));
		}
		return propertiesMap;
	}

	/**
	 * Adds the error to the error list.
	 *
	 * @param error
	 */
	private void recordError(final String error) {
		errors.add(error);
	}
}
