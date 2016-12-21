package nl.tinkoczy.villa.config;

/**
 * Value type that is a boolean
 */
public class BooleanValue implements Value {
	private static final String TRUE = "true";
	private static final String FALSE = "false";

	private final boolean value;

	/**
	 * Takes the boolean value from the given string.
	 *
	 * @param stringValue
	 *            Valid options: "true" or "false" (case-insensitive).
	 * @throws ConfigFormatException
	 *             stringValue does not represent a valid boolean value.
	 */
	public BooleanValue(final String stringValue) throws ConfigFormatException {
		if (!(stringValue.equalsIgnoreCase(TRUE) || stringValue.equalsIgnoreCase(FALSE))) {
			throw new ConfigFormatException("Error parsing boolean from: " + stringValue);
		}

		value = Boolean.parseBoolean(stringValue);
	}

	/**
	 * Constructs a BooleanValue from a boolean value without conversion
	 *
	 * @param booleanValue
	 */
	public BooleanValue(final boolean booleanValue) {
		value = booleanValue;
	}

	@Override
	public boolean asBoolean() {
		return value;
	}

	@Override
	public int asInt() {
		throw new UnsupportedOperationException("Requested int for boolean value");
	}

	@Override
	public String asString() {
		throw new UnsupportedOperationException("Requested String for boolean value");
	}

	@Override
	public double asDouble() {
		throw new UnsupportedOperationException("Requested double for boolean value");
	}

	@Override
	public String convertToString() {
		return Boolean.toString(value);
	}
}
