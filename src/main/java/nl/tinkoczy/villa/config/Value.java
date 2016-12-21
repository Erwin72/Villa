package nl.tinkoczy.villa.config;

/**
 * This interface is a general interface for different types of values. The user
 * of this value should know the type (see {@link ConfigLoader}) and only call
 * the appropriate conversion function. All other functions should throw an
 * {@link UnsupportedOperationException}.
 */
public interface Value {

	/**
	 *
	 * @throws java.lang.UnsupportedOperationException
	 * @return a boolean.
	 */
	public boolean asBoolean();

	/**
	 * @throws java.lang.UnsupportedOperationException
	 * @return an int.
	 */
	public int asInt();

	/**
	 * @throws java.lang.UnsupportedOperationException
	 * @return a double.
	 */
	public double asDouble();

	/**
	 *
	 * @throws java.lang.UnsupportedOperationException
	 * @return a {@link java.lang.String} object.
	 */
	public String asString();

	/**
	 * Converts this Value to a user-friendly, human readable String
	 * representation.
	 *
	 * @return String representation of this Value.
	 */
	public String convertToString();

}
