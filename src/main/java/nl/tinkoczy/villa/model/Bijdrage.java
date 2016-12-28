package nl.tinkoczy.villa.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bijdrage {

	private static final String DATE_PATTERN = "dd-MM-yyyy";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	private final ObjectProperty<Long> bijdrageId;
	private final ObjectProperty<LocalDate> bijdrageDatum;
	private final ObjectProperty<BigDecimal> bijdrageBedrag;
	private final BooleanProperty bijdrageVoldaan;
	private final StringProperty bijdrageSchemaNaam;

	// TODO koppelen aan appartement

	public Bijdrage() {
		this(null, null);
	}

	public Bijdrage(final Long bijdrageId, final LocalDate bijdrageDatum) {
		this.bijdrageId = new SimpleObjectProperty<Long>(bijdrageId);
		this.bijdrageDatum = new SimpleObjectProperty<>(bijdrageDatum);

		this.bijdrageBedrag = new SimpleObjectProperty<>();
		this.bijdrageVoldaan = new SimpleBooleanProperty(false);
		this.bijdrageSchemaNaam = new SimpleStringProperty();
	}

	public Long getBijdrageId() {
		return bijdrageId.get();
	}

	public void setBijdrageId(final Long bijdrageId) {
		this.bijdrageId.set(bijdrageId);
	}

	public ObjectProperty<Long> bijdrageIdProperty() {
		return bijdrageId;
	}

	public LocalDate getBijdrageDatum() {
		return bijdrageDatum.get();
	}

	public String getBijdrageDatumAsString() {
		return DATE_FORMATTER.format(bijdrageDatum.get());
	}

	public void setBijdrageDatum(final LocalDate bijdrageDatum) {
		this.bijdrageDatum.set(bijdrageDatum);
	}

	public ObjectProperty<LocalDate> bijdrageDatumProperty() {
		return bijdrageDatum;
	}

	public SimpleStringProperty bijdrageDatumAsStringProperty() {
		return new SimpleStringProperty(DATE_FORMATTER.format(bijdrageDatum.get()));
	}

	public BigDecimal getBijdrageBedrag() {
		return bijdrageBedrag.get();
	}

	public void setBijdrageBedrag(final BigDecimal bijdrageBedrag) {
		this.bijdrageBedrag.set(bijdrageBedrag);
	}

	public ObjectProperty<BigDecimal> bijdrageBedragProperty() {
		return bijdrageBedrag;
	}

	public Boolean getBijdrageVoldaan() {
		return bijdrageVoldaan.get();
	}

	public void setBijdrageVoldaan(final Boolean bijdrageVoldaan) {
		this.bijdrageVoldaan.set(bijdrageVoldaan);
	}

	public BooleanProperty bijdrageVoldaanProperty() {
		return bijdrageVoldaan;
	}

	public String getBijdrageSchemaNaam() {
		return bijdrageSchemaNaam.get();
	}

	public void setBijdrageSchemaNaam(final String bijdrageSchemaNaam) {
		this.bijdrageSchemaNaam.set(bijdrageSchemaNaam);
	}

	public StringProperty bijdrageSchemaNaamProperty() {
		return bijdrageSchemaNaam;
	}
}
