package nl.tinkoczy.villa.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class WerkDatumUtil {

	final static Logger logger = LoggerFactory.getLogger(WerkDatumUtil.class);

	private static final String DATE_PATTERN = "dd-MM-yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
	private static LocalDate villaWerkDatum = LocalDate.now();
	private static SimpleObjectProperty<LocalDate> villaWerkDatumProperty = new SimpleObjectProperty<>(villaWerkDatum);
	private static SimpleStringProperty villaWerkDatumPropertyAsString = new SimpleStringProperty(
			DATE_FORMATTER.format(villaWerkDatum));

	private WerkDatumUtil() {
		super();
	}

	public static LocalDate getVillaWerkDatum() {
		return villaWerkDatum;
	}

	public static SimpleObjectProperty<LocalDate> villaWerkDatumProperty() {
		return villaWerkDatumProperty;
	}

	public static SimpleStringProperty getVillaWerkDatumAsString() {
		return villaWerkDatumPropertyAsString;
	}

	/**
	 * Set the current date
	 *
	 * @param villaWerkDatum
	 */
	public static void setVillaWerkDatum(final LocalDate villaWerkDatum) {
		WerkDatumUtil.villaWerkDatum = villaWerkDatum;
		WerkDatumUtil.villaWerkDatumProperty.set(villaWerkDatum);
		WerkDatumUtil.villaWerkDatumPropertyAsString.set(DATE_FORMATTER.format(villaWerkDatum));
	}
}
