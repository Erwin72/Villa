package nl.tinkoczy.villa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BijdrageSchema {

	private final LongProperty bijdrageSchemaId;
	private final StringProperty bijdrageSchemaNaam;
	private final IntegerProperty bijdrageFrequentieCode;
	private final LongProperty bijdrageRenteFk;

	public BijdrageSchema() {
		this(null, null, null);
	}

	public BijdrageSchema(final Long bijdrageSchemaId, final String bijdrageSchemaNaam,
			final Integer bijdrageFrequentieCode) {
		this.bijdrageSchemaId = new SimpleLongProperty(bijdrageSchemaId);
		this.bijdrageSchemaNaam = new SimpleStringProperty(bijdrageSchemaNaam);
		this.bijdrageFrequentieCode = new SimpleIntegerProperty(bijdrageFrequentieCode);

		this.bijdrageRenteFk = new SimpleLongProperty();
	}

	public Long getBijdrageSchemaId() {
		return bijdrageSchemaId.get();
	}

	public void setBijdrageSchemaId(final Long bijdrageSchemaId) {
		this.bijdrageSchemaId.set(bijdrageSchemaId);
	}

	public LongProperty bijdrageSchemaIdProperty() {
		return bijdrageSchemaId;
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

	public Integer getBijdrageFrequentieCode() {
		return bijdrageFrequentieCode.get();
	}

	public void setBijdrageFrequentieCode(final Integer bijdrageFrequentieCode) {
		this.bijdrageFrequentieCode.set(bijdrageFrequentieCode);
	}

	public IntegerProperty bijdrageFrequentieCodeProperty() {
		return bijdrageFrequentieCode;
	}

	public Long getBijdrageRenteFk() {
		return bijdrageRenteFk.get();
	}

	public void setBijdrageRenteFk(final Long bijdrageRenteFk) {
		this.bijdrageRenteFk.set(bijdrageRenteFk);
	}

	public LongProperty bijdrageRenteFkProperty() {
		return bijdrageRenteFk;
	}
}
