package nl.tinkoczy.villa.config;

import static nl.tinkoczy.villa.config.ConfigType.BOOLEAN;
import static nl.tinkoczy.villa.config.ConfigType.DOUBLE;
import static nl.tinkoczy.villa.config.ConfigType.INT;
import static nl.tinkoczy.villa.config.ConfigType.STRING;

public enum ApplicationConfiguration implements ConfigItemEnum {

	DATABASE_NAME("VillaDB");

	private final ConfigType type;
	private final Value defaultValue;

	private ApplicationConfiguration(final String stringDefaultValue) {
		type = STRING;
		defaultValue = new StringValue(stringDefaultValue);
	}

	private ApplicationConfiguration(final int intDefaultValue) {
		type = INT;
		defaultValue = new IntegerValue(intDefaultValue);
	}

	private ApplicationConfiguration(final boolean booleanDefaultValue) {
		type = BOOLEAN;
		defaultValue = new BooleanValue(booleanDefaultValue);
	}

	private ApplicationConfiguration(final double doubleDefaultValue) {
		type = DOUBLE;
		defaultValue = new DoubleValue(doubleDefaultValue);
	}

	@Override
	public ConfigType getType() {
		return type;
	}

	@Override
	public Value getDefaultValue() {
		return defaultValue;
	}
}
