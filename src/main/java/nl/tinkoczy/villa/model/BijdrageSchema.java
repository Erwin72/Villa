package nl.tinkoczy.villa.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BijdrageSchema {

	private final ObjectProperty<Long> bijdrageSchemaId;
	private final StringProperty bijdrageSchemaNaam;
	private final ObjectProperty<Integer> bijdrageFrequentieCode;
	private final ObjectProperty<Long> bijdrageRenteFk;

	public BijdrageSchema() {
		this(null, null, null);
	}

	public BijdrageSchema(final Long bijdrageSchemaId, final String bijdrageSchemaNaam,
			final Integer bijdrageFrequentieCode) {
		this.bijdrageSchemaId = new SimpleObjectProperty<Long>(bijdrageSchemaId);
		this.bijdrageSchemaNaam = new SimpleStringProperty(bijdrageSchemaNaam);
		this.bijdrageFrequentieCode = new SimpleObjectProperty<Integer>(bijdrageFrequentieCode);

		this.bijdrageRenteFk = new SimpleObjectProperty<Long>();
	}

	public Long getBijdrageSchemaId() {
		return bijdrageSchemaId.get();
	}

	public void setBijdrageSchemaId(final Long bijdrageSchemaId) {
		this.bijdrageSchemaId.set(bijdrageSchemaId);
	}

	public ObjectProperty<Long> bijdrageSchemaIdProperty() {
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

	public ObjectProperty<Integer> bijdrageFrequentieCodeProperty() {
		return bijdrageFrequentieCode;
	}

	public Long getBijdrageRenteFk() {
		return bijdrageRenteFk.get();
	}

	public void setBijdrageRenteFk(final Long bijdrageRenteFk) {
		this.bijdrageRenteFk.set(bijdrageRenteFk);
	}

	public ObjectProperty<Long> bijdrageRenteFkProperty() {
		return bijdrageRenteFk;
	}
}
