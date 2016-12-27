package nl.tinkoczy.villa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bijdrage {

	private final LongProperty bijdrageId;
	private final ObjectProperty<LocalDate> bijdrageDatum;
	private final ObjectProperty<BigDecimal> bijdrageBedrag;
	private final BooleanProperty bijdrageVoldaan;
	private final StringProperty bijdrageSchemaNaam;

	// TODO koppelen aan appartement

	public Bijdrage() {
		this(null, null);
	}

	public Bijdrage(final Long bijdrageId, final LocalDate bijdrageDatum) {
		this.bijdrageId = new SimpleLongProperty(bijdrageId);
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

	public LongProperty bijdrageIdProperty() {
		return bijdrageId;
	}

	public LocalDate getBijdrageDatum() {
		return bijdrageDatum.get();
	}

	public void setBijdrageDatum(final LocalDate bijdrageDatum) {
		this.bijdrageDatum.set(bijdrageDatum);
	}

	public ObjectProperty<LocalDate> bijdrageDatumProperty() {
		return bijdrageDatum;
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
