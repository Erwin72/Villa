package nl.tinkoczy.villa.config;

/**
 * Custom exception type, used to indicate a config element could not be
 * formatted
 */
public class ConfigFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConfigFormatException(final String arg0) {
		super(arg0);
	}

	public ConfigFormatException(final Throwable arg0) {
		super(arg0);
	}

	public ConfigFormatException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

}
