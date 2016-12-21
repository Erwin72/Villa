package nl.tinkoczy.villa.config;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a facade (design pattern) for the {@link ConfigLoader}. In order to
 * use this class first call {@link #initConfigLoader()} , then load the various
 * config elements using {@link #loadConfig(Class, URL)}. This will fill the
 * error list, which can be retrieved by calling {@link #getErrors()} After that
 * the functions {@link #getBooleanValue(Enum)}, {@link #getIntValue(Enum)} and
 * {@link #getStringValue(Enum)} can be used with the enum values that have been
 * loaded in {@link #loadConfig(Class, URL)}. The {@link ConfigLoader} ensures
 * that if a call has to get a value has succeeded once and the configuration
 * was loaded without errors, the get value functions will always return a valid
 * value.<br/>
 * <br/>
 * The load config will generate the following errors:\
 * <ul>
 * <li>Url is not a valid property file</li>
 * <li>A property has an invalid type</li>
 * <li>The property file contains properties that are not in the enum</li>
 * </ul>
 * For all enum values that are not listed in the file, the default values will
 * be loaded. This class is not thread safe during initialisation. However after
 * calling {@link #initConfigLoader()} and {@link #loadConfig(Class, URL)} all
 * the get functinos can be called safely from a multithreaded environment.
 */
public final class ConfigFacade {

	final static Logger logger = LoggerFactory.getLogger(ConfigFacade.class);

	private static volatile ConfigLoader configLoader = null;

	/**
	 * Suppress default constructor for noninstantiability
	 */
	private ConfigFacade() {
		throw new AssertionError();
	}

	/**
	 * Initialise the configLoader with the default constructor
	 */
	public static void initConfigLoader() {
		initConfigLoader(new ConfigLoader());
	}

	/**
	 * Initialise the config loader with a custom loader. This is only used in
	 * testing.
	 *
	 * @param loader
	 */
	public static void initConfigLoader(final ConfigLoader loader) {
		if (configLoader != null) {
			throw new IllegalStateException("ConfigLoader already initialised");
		}
		configLoader = loader;
	}

	/**
	 * Unload the config loader, this should only be done in test case. It is
	 * important to do this after every testcase/testset, catch and ignore the
	 * invalid state exception if it is done with the @After function in Junit
	 */
	public static void deinitConfigLoader() {
		assertLoaderNotNull();
		configLoader = null;
	}

	/**
	 * Loads config values from a url.
	 *
	 * @param <E>
	 *            An enum that implements {@link ConfigItemEnum}.
	 * @param configSet
	 *            Enum that describes all the properties.
	 * @param url
	 *            The resource location of the properties file
	 */
	public static <E extends Enum<?> & ConfigItemEnum> void loadConfig(final Class<E> configSet, final URL url) {
		assertLoaderNotNull();
		logger.info("Loading " + configSet.getSimpleName() + " " + url);
		configLoader.loadConfig(configSet, url);
	}

	/**
	 * Loads config values from System.properties().
	 *
	 * @param <E>
	 *            An enum that implements {@link ConfigItemEnum}.
	 * @param configSet
	 *            Enum that describes all the properties.
	 * @param properties
	 *            {@link Properties} object to load the config from.
	 */
	public static <E extends Enum<?> & ConfigItemEnum> void loadConfig(final Class<E> configSet,
			final Properties properties) {
		assertLoaderNotNull();
		logger.info("Loading " + configSet.getSimpleName() + " from properties");
		configLoader.loadConfig(configSet, properties);
	}

	/**
	 * Returns the list of errors (human readable strings) generated through
	 * loadConfig.
	 *
	 * @return
	 */
	public static List<String> getErrors() {
		assertLoaderNotNull();
		return configLoader.getErrors();
	}

	/**
	 * Return a config that is a boolean, this will throw an
	 * {@link UnsupportedOperationException} if the config isn't a boolean type
	 *
	 * @param <E>
	 * @param config
	 * @return
	 */
	public static <E extends Enum<?> & ConfigItemEnum> boolean getBooleanValue(final E config) {
		assertLoaderNotNull();
		return configLoader.getValue(config).asBoolean();
	}

	/**
	 * Return a config that is a string, this will throw an
	 * {@link UnsupportedOperationException} if the config isn't a string type
	 *
	 * @param <E>
	 * @param config
	 * @return
	 */
	public static <E extends Enum<?> & ConfigItemEnum> String getStringValue(final E config) {
		assertLoaderNotNull();
		return configLoader.getValue(config).asString();
	}

	/**
	 * Return a config that is a double, this will throw an
	 * {@link UnsupportedOperationException} if the config isn't a double type
	 *
	 * @param <E>
	 * @param config
	 * @return
	 */
	public static <E extends Enum<?> & ConfigItemEnum> double getDoubleValue(final E config) {
		assertLoaderNotNull();
		return configLoader.getValue(config).asDouble();
	}

	/**
	 * Return a config that is an int, this will throw an
	 * {@link UnsupportedOperationException} if the config isn't an int type.
	 * This will throw an {@link IllegalStateException} if the configloader
	 * wasn't initialized or the Enum wasn't loaded
	 *
	 * @param <E>
	 * @param config
	 * @return
	 */
	public static <E extends Enum<?> & ConfigItemEnum> int getIntValue(final E config) {
		assertLoaderNotNull();
		return configLoader.getValue(config).asInt();
	}

	/**
	 * Properties containing items from an {@link ConfigItemEnum} of which the
	 * values were modified during startup. A property is considered 'modified'
	 * if the property's value is set during startup.
	 *
	 * @param <E>
	 * @param configSet
	 * @return Properties with items and their modified values.
	 */
	public static <E extends Enum<?> & ConfigItemEnum> Properties getModifiedValuesAsProperties(
			final Class<E> configSet) {
		assertLoaderNotNull();
		return configLoader.getModifiedValuesAsProperties(configSet);
	}

	/**
	 * Should only be called while holding the loader lock
	 *
	 * @throws IllegalStateException
	 *             When the loader has not been initialized
	 */
	private static void assertLoaderNotNull() {
		if (configLoader == null) {
			throw new IllegalStateException("ConfigLoader not initialised");
		}
	}
}
