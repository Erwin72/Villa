package nl.tinkoczy.villa.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter (for JAXB) to convert between the LocalDate and the ISO 8601 String
 * representation of the date such as '2012-12-03'.
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(final String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override
	public String marshal(final LocalDate v) throws Exception {
		return v.toString();
	}
}