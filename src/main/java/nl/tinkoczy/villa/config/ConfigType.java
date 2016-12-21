package nl.tinkoczy.villa.config;

/**
 * Enum with all configuration types. To add a type add an element to this enum
 * and assign an object that implements {@link Value}
 */
public enum ConfigType {
	BOOLEAN {
		@Override
		public Value parseValue(final String stringValue) throws ConfigFormatException {
			return new BooleanValue(stringValue);
		}
	},
	INT {
		@Override
		public Value parseValue(final String stringValue) throws ConfigFormatException {
			return new IntegerValue(stringValue);
		}
	},
	STRING {
		@Override
		public Value parseValue(final String stringValue) {
			return new StringValue(stringValue);
		}
	},
	DOUBLE {
		@Override
		public Value parseValue(final String stringValue) throws ConfigFormatException {
			return new DoubleValue(stringValue);
		}
	};

	public abstract Value parseValue(String stringValue) throws ConfigFormatException;

}
