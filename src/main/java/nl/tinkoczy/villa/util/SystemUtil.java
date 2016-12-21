package nl.tinkoczy.villa.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Helper class which functions as a system utility.
 */
public final class SystemUtil {

	final static Logger logger = LoggerFactory.getLogger(SystemUtil.class);

	private SystemUtil() {
		// only static methods
	}

	/**
	 * enum containing the host-name variables name which belongs to a specific
	 * OS. Example: To get the host-name in Linux systems, the HOSTNAME
	 * environment variable will return the host name, while in windows it is
	 * different.
	 *
	 *
	 */
	public enum Hostname {
		LINUX("HOSTNAME"), WINDOWS("COMPUTERNAME"), UNKNOWN("UNKNOWN");

		private final String hostnameVar;
		private String hostname = "";

		private Hostname(final String hostnameVar) {
			this.hostnameVar = hostnameVar;
		}

		public String getHostnameVar() {
			return hostnameVar;
		}

		/**
		 *
		 * @return the hostname for this variable or an empty string.
		 */
		public String getHostname() {

			if (hostname.isEmpty()) {
				String tempHostname = System.getenv(hostnameVar);
				if (tempHostname != null) {
					hostname = tempHostname;
				}
			}
			return hostname;
		}

		/**
		 *
		 * @return true if this value is not UNKNOWN and the
		 *         {@link #getHostname()} returns a non-empty string.
		 */
		public boolean isValid() {
			return !this.equals(UNKNOWN) && !getHostname().isEmpty();
		}

		@Override
		public String toString() {
			return "Variable = [" + hostnameVar + "] with hostname: [" + getHostname() + "].";
		}
	}

	/**
	 *
	 * @return a list of non-loopback InetAddresses or an empty list.
	 */
	public static List<InetAddress> getNonLoopbackAddresses() {
		List<InetAddress> nonLoopbackAddresses = new ArrayList<InetAddress>();

		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

			for (NetworkInterface i : Collections.list(networkInterfaces)) {
				for (InetAddress adress : Collections.list(i.getInetAddresses())) {
					if (!adress.isLoopbackAddress()) {
						nonLoopbackAddresses.add(adress);
					}
				}
			}
		} catch (SocketException e) {
			logger.debug("Error occured while trying to retrieve non-loopback ip addresses: " + e.getMessage());
		}
		return nonLoopbackAddresses;
	}

	/**
	 *
	 *
	 * @return host name retrieved from the first non loop-back ip address or an
	 *         empty string.
	 */
	public static String getFirstHostnameFromNonLoopBackAddresses() {
		String hostname = "";

		List<InetAddress> netAddresses = SystemUtil.getNonLoopbackAddresses();
		if (netAddresses.isEmpty()) {
			logger.debug("No non-loopback addresses found.");
		} else {
			hostname = netAddresses.get(0).getHostName();
		}

		return hostname;
	}

	/**
	 *
	 * @return a hostname by using HostnameVariable or returns an empty string.
	 */
	public static String getHostnameBySystemVariable() {
		String hostname = "";
		for (Hostname retrievedVar : Hostname.values()) {
			if (retrievedVar.isValid()) {
				hostname = retrievedVar.getHostname();
				break;
			}
		}
		return hostname;
	}

	// /**
	// * This method returns a host name as one of the following:
	// * <ul>
	// * <li>hostname from the enviroment variable</li>
	// * <li>hostname retrieved from the first non loop-back ip address</li>
	// * <li>default hostname from the configuration</li>
	// * </ul>
	// *
	// * @return hostname
	// */
	// public static String getHostname() {
	// String hostname = getHostnameBySystemVariable();
	//
	// if (hostname.isEmpty()) {
	// hostname = SystemUtil.getFirstHostnameFromNonLoopBackAddresses();
	// } else {
	// hostname =
	// ConfigFacade.getStringValue(ApplicationConfiguration.CONSUMER_IDENTIFICATION_HOSTNAME_DEFAULT);
	// }
	//
	// if (logger.isDebugEnabled()) {
	// logger.debug("Hostname found by getting first non loopback ip: " +
	// hostname);
	// }
	// return hostname;
	// }

	// /**
	// * This method creates a String containing the host name and a UUID that
	// * looks as:
	// *
	// * <b>Format: "</b><i>hostname.UUD</i>"
	// * <ul>
	// * <li><i>hostname</i> is either the hostname or the ip address if no
	// * hostname can be retrieved which is retrieved by
	// * {@link #getHostname()}</li>
	// * <li><i>UUID</i> a random uuid</li>
	// * </ul>
	// *
	// *
	// * @return the consumer identification.
	// */
	// public static String createHostnameDotUUID() {
	// String hostnameDotUUID = getHostname() + "." +
	// UUID.randomUUID().toString();
	//
	// if (logger.isDebugEnabled()) {
	// logger.debug("Hostname dot UUID created: " + hostnameDotUUID);
	// }
	// return hostnameDotUUID;
	// }

	/**
	 * This method returns the returned system property or an empty string if
	 * system property is null.
	 *
	 * @param systemProperty
	 * @return not null
	 */
	public static String getSystemProperty(final String systemProperty) {
		String property = System.getProperty(systemProperty);
		if (property == null) {
			return StringUtils.EMPTY;
		} else {
			return property;
		}
	}

	/**
	 * Name of the property is the same as the enum with the following changes:
	 * Capitals are now lower case, underscores '_' are dots '.'.
	 *
	 * @param enumName
	 * @return
	 */
	public static String convertEnumToProperty(final String enumName) {
		return enumName.toLowerCase(Locale.ENGLISH).replace('_', '.');
	}
}
