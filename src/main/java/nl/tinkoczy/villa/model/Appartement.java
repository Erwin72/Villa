package nl.tinkoczy.villa.model;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appartement {

	private final ObjectProperty<Long> appartementId;
	private final StringProperty appartementCode;
	private final ObjectProperty<LocalDate> appartementTransportdatum;
	private final StringProperty appartementAdresStraat;
	private final StringProperty appartementAdresPostcode;
	private final StringProperty appartementAdresPlaats;
	private final LongProperty bijdrageSchemaFk;

	public Appartement() {
		this(null, null);
	}

	public Appartement(final Long appartementId, final String appartementCode) {
		this.appartementId = new SimpleObjectProperty<Long>(appartementId);
		this.appartementCode = new SimpleStringProperty(appartementCode);

		// Some initial dummy data, just for convenient testing.
		this.appartementTransportdatum = new SimpleObjectProperty<LocalDate>();
		this.appartementAdresStraat = new SimpleStringProperty();
		this.appartementAdresPostcode = new SimpleStringProperty();
		this.appartementAdresPlaats = new SimpleStringProperty();
		this.bijdrageSchemaFk = new SimpleLongProperty();
	}

	public Long getAppartementId() {
		return appartementId.get();
	}

	public void setAppartementId(final Long appartementId) {
		this.appartementId.set(appartementId);
	}

	public ObjectProperty<Long> appartementIdProperty() {
		return appartementId;
	}

	public String getAppartementCode() {
		return appartementCode.get();
	}

	public void setAppartementCode(final String appartementCode) {
		this.appartementCode.set(appartementCode);
	}

	public StringProperty appartementCodeProperty() {
		return appartementCode;
	}

	public LocalDate getAppartementTransportdatum() {
		return appartementTransportdatum.get();
	}

	public void setAppartementTransportdatum(final LocalDate appartementTransportdatum) {
		this.appartementTransportdatum.set(appartementTransportdatum);
	}

	public ObjectProperty<LocalDate> appartementTransportdatumProperty() {
		return appartementTransportdatum;
	}

	public String getAppartementAdresStraat() {
		return appartementAdresStraat.get();
	}

	public void setAppartementAdresStraat(final String appartementAdresStraat) {
		this.appartementAdresStraat.set(appartementAdresStraat);
	}

	public StringProperty appartementAdresStraatProperty() {
		return appartementAdresStraat;
	}

	public String getAppartementAdresPostcode() {
		return appartementAdresPostcode.get();
	}

	public void setAppartementAdresPostcode(final String appartementAdresPostcode) {
		this.appartementAdresPostcode.set(appartementAdresPostcode);
	}

	public StringProperty appartementAdresPostcodeProperty() {
		return appartementAdresPostcode;
	}

	public String getAppartementAdresPlaats() {
		return appartementAdresPlaats.get();
	}

	public void setAppartementAdresPlaats(final String appartementAdresPlaats) {
		this.appartementAdresPlaats.set(appartementAdresPlaats);
	}

	public StringProperty appartementAdresPlaatsProperty() {
		return appartementAdresPlaats;
	}

	public Long getBijdrageSchemaFk() {
		return bijdrageSchemaFk.get();
	}

	public void setBijdrageSchemaFk(final Long bijdrageSchemaFk) {
		this.bijdrageSchemaFk.set(bijdrageSchemaFk);
	}

	public LongProperty bijdrageSchemaFkProperty() {
		return bijdrageSchemaFk;
	}
}
