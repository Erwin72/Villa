package nl.tinkoczy.villa.config;

/**
 * Value type that is an integer
 */
public class IntegerValue implements Value {

	private final int value;

	/**
	 * Takes the int value from the given string. This uses
	 * {@link Integer#parseInt(String)}
	 *
	 * @param stringValue
	 * @throws ConfigFormatException
	 *             Thrown if no valid int is represented by the String
	 *
	 */
	public IntegerValue(final String stringValue) throws ConfigFormatException {
		try {
			value = Integer.parseInt(stringValue);
		} catch (NumberFormatException e) {
			throw new ConfigFormatException("Error parsing integer from: " + stringValue, e);
		}
	}

	/**
	 * Creates an integervalue without parsing
	 *
	 * @param intValue
	 */
	public IntegerValue(final int intValue) {
		value = intValue;
	}

	@Override
	public boolean asBoolean() {
		throw new UnsupportedOperationException("Requested boolean for int value");
	}

	@Override
	public int asInt() {
		return value;
	}

	@Override
	public String asString() {
		throw new UnsupportedOperationException("Requested String for int value");
	}

	@Override
	public double asDouble() {
		throw new UnsupportedOperationException("Requested double for int value");
	}

	@Override
	public String convertToString() {
		return Integer.toString(value);
	}
}
