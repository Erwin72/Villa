package nl.tinkoczy.villa.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Relatie {

	private final ObjectProperty<Long> relatieId;
	private final StringProperty relatieCode;
	private final StringProperty relatieOmschrijving;
	private final StringProperty relatieNaam;
	private final StringProperty relatieAdresStraat;
	private final StringProperty relatieAdresPostcode;
	private final StringProperty relatieAdresPlaats;
	private final StringProperty relatiePostbusNummer;
	private final StringProperty relatiePostbusPostcode;
	private final StringProperty relatiePostbusPlaats;
	private final StringProperty relatieBankNaam;
	private final StringProperty relatieBankIBAN;
	private final StringProperty relatieAantekening;

	public Relatie() {
		this(null, null);
	}

	public Relatie(final Long relatieId, final String relatieCode) {
		this.relatieId = new SimpleObjectProperty<Long>(relatieId);
		this.relatieCode = new SimpleStringProperty(relatieCode);

		this.relatieOmschrijving = new SimpleStringProperty();
		this.relatieNaam = new SimpleStringProperty();
		this.relatieAdresStraat = new SimpleStringProperty();
		this.relatieAdresPostcode = new SimpleStringProperty();
		this.relatieAdresPlaats = new SimpleStringProperty();
		this.relatiePostbusNummer = new SimpleStringProperty();
		this.relatiePostbusPostcode = new SimpleStringProperty();
		this.relatiePostbusPlaats = new SimpleStringProperty();
		this.relatieBankNaam = new SimpleStringProperty();
		this.relatieBankIBAN = new SimpleStringProperty();
		this.relatieAantekening = new SimpleStringProperty();
	}

	public Long getRelatieId() {
		return relatieId.get();
	}

	public void setRelatieId(final Long relatieId) {
		this.relatieId.set(relatieId);
	}

	public ObjectProperty<Long> relatieIdProperty() {
		return relatieId;
	}

	public String getRelatieCode() {
		return relatieCode.get();
	}

	public void setRelatieCode(final String relatieCode) {
		this.relatieCode.set(relatieCode);
	}

	public StringProperty relatieCodeProperty() {
		return relatieCode;
	}

	public String getRelatieOmschrijving() {
		return relatieOmschrijving.get();
	}

	public void setRelatieOmschrijving(final String relatieOmschrijving) {
		this.relatieOmschrijving.set(relatieOmschrijving);
	}

	public StringProperty relatieOmschrijvingProperty() {
		return relatieOmschrijving;
	}

	public String getRelatieNaam() {
		return relatieNaam.get();
	}

	public void setRelatieNaam(final String relatieNaam) {
		this.relatieNaam.set(relatieNaam);
	}

	public StringProperty relatieNaamProperty() {
		return relatieNaam;
	}

	public String getRelatieAdresStraat() {
		return relatieAdresStraat.get();
	}

	public void setRelatieAdresStraat(final String relatieAdresStraat) {
		this.relatieAdresStraat.set(relatieAdresStraat);
	}

	public StringProperty relatieAdresStraatProperty() {
		return relatieAdresStraat;
	}

	public String getRelatieAdresPostcode() {
		return relatieAdresPostcode.get();
	}

	public void setRelatieAdresPostcode(final String relatieAdresPostcode) {
		this.relatieAdresPostcode.set(relatieAdresPostcode);
	}

	public StringProperty relatieAdresPostcodeProperty() {
		return relatieAdresPostcode;
	}

	public String getRelatieAdresPlaats() {
		return relatieAdresPlaats.get();
	}

	public void setRelatieAdresPlaats(final String relatieAdresPlaats) {
		this.relatieAdresPlaats.set(relatieAdresPlaats);
	}

	public StringProperty relatieAdresPlaatsProperty() {
		return relatieAdresPlaats;
	}

	public String getRelatiePostbusNummer() {
		return relatiePostbusNummer.get();
	}

	public void setRelatiePostbusNummer(final String relatiePostbusNummer) {
		this.relatiePostbusNummer.set(relatiePostbusNummer);
	}

	public StringProperty relatiePostbusNummerProperty() {
		return relatiePostbusNummer;
	}

	public String getRelatiePostbusPostcode() {
		return relatiePostbusPostcode.get();
	}

	public void setRelatiePostbusPostcode(final String relatiePostbusPostcode) {
		this.relatiePostbusPostcode.set(relatiePostbusPostcode);
	}

	public StringProperty relatiePostbusPostcodeProperty() {
		return relatiePostbusPostcode;
	}

	public String getRelatiePostbusPlaats() {
		return relatiePostbusPlaats.get();
	}

	public void setRelatiePostbusPlaats(final String relatiePostbusPlaats) {
		this.relatiePostbusPlaats.set(relatiePostbusPlaats);
	}

	public StringProperty relatiePostbusPlaatsProperty() {
		return relatiePostbusPlaats;
	}

	public String getRelatieBankNaam() {
		return relatieBankNaam.get();
	}

	public void setRelatieBankNaam(final String relatieBankNaam) {
		this.relatieBankNaam.set(relatieBankNaam);
	}

	public StringProperty relatieBankNaamProperty() {
		return relatieBankNaam;
	}

	public String getRelatieBankIBAN() {
		return relatieBankIBAN.get();
	}

	public void setRelatieBankIBAN(final String relatieBankIBAN) {
		this.relatieBankIBAN.set(relatieBankIBAN);
	}

	public StringProperty relatieBankIBANProperty() {
		return relatieBankIBAN;
	}

	public String getRelatieAantekening() {
		return relatieAantekening.get();
	}

	public void setRelatieAantekening(final String relatieAantekening) {
		this.relatieAantekening.set(relatieAantekening);
	}

	public StringProperty relatieAantekeningProperty() {
		return relatieAantekening;
	}
}
