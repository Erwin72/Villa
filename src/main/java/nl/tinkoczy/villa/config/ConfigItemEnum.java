package nl.tinkoczy.villa.config;

/**
 * Interface that should be used for Enums that represent config elements. Some
 * enums will all have the same type and implement {@link #getType()} once and
 * others will vary it based on configured element.
 */
public interface ConfigItemEnum {

	/**
	 * The type of the configured element.
	 *
	 * @return a {@link nl.prorail.sbg.iface.configuration.internal.ConfigType}
	 *         object.
	 */
	public ConfigType getType();

	/**
	 * The default value, this must match the type of {@link #getType()}!
	 *
	 * @return a {@link nl.prorail.sbg.iface.configuration.internal.Value}
	 *         object.
	 */
	public Value getDefaultValue();
}
