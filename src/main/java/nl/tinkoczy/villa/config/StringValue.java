package nl.tinkoczy.villa.config;

/**
 * Value that represents a string type
 */
public class StringValue implements Value {

	private final String value;

	public StringValue(final String stringValue) {
		value = stringValue;
	}

	@Override
	public boolean asBoolean() {
		throw new UnsupportedOperationException("Requested boolean for String value");
	}

	@Override
	public int asInt() {
		throw new UnsupportedOperationException("Requested int for String value");
	}

	@Override
	public double asDouble() {
		throw new UnsupportedOperationException("Requested double for String value");
	}

	@Override
	public String asString() {
		return value;
	}

	@Override
	public String convertToString() {
		return value;
	}
}
