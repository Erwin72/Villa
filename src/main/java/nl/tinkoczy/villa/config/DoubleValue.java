package nl.tinkoczy.villa.config;

/**
 * Value type that is a Double
 */
public class DoubleValue implements Value {

	private final double value;

	/**
	 * Takes the double value from the given string. This uses
	 * {@link Double#parseDouble(String)}
	 *
	 * @param stringValue
	 * @throws ConfigFormatException
	 *             Thrown if no valid double is represented by the String
	 *
	 */
	public DoubleValue(final String stringValue) throws ConfigFormatException {
		try {
			value = Double.parseDouble(stringValue);
		} catch (NumberFormatException e) {
			throw new ConfigFormatException("Error parsing double from: " + stringValue, e);
		}
	}

	/**
	 * Creates a double value without parsing
	 *
	 * @param doubleValue
	 */
	public DoubleValue(final double doubleValue) {
		value = doubleValue;
	}

	@Override
	public boolean asBoolean() {
		throw new UnsupportedOperationException("Requested boolean for double value");
	}

	@Override
	public int asInt() {
		throw new UnsupportedOperationException("Requested int for double value");
	}

	@Override
	public double asDouble() {
		return value;
	}

	@Override
	public String asString() {
		throw new UnsupportedOperationException("Requested String for double value");
	}

	@Override
	public String convertToString() {
		return Double.toString(value);
	}
}
