package nl.tinkoczy.villa.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RelatiePersoon {

	private final ObjectProperty<Long> relatiePersoonId;
	private final StringProperty relatiePersoonVoornaam;
	private final StringProperty relatiePersoonTussenvoegsel;
	private final StringProperty relatiePersoonAchternaam;
	private final StringProperty relatiePersoonOmschrijving;
	private final StringProperty relatiePersoonTelefoon;
	private final StringProperty relatiePersoonMobiel;
	private final StringProperty relatiePersoonEmail;
	private final StringProperty relatiePersoonInternet;
	private final StringProperty relatiePersoonAantekening;
	private final StringProperty relatieCode;

	public RelatiePersoon() {
		this(null, null);
	}

	public RelatiePersoon(final Long relatiePersoonId, final String relatieCode) {
		this.relatiePersoonId = new SimpleObjectProperty<Long>(relatiePersoonId);
		this.relatieCode = new SimpleStringProperty(relatieCode);

		this.relatiePersoonVoornaam = new SimpleStringProperty();
		this.relatiePersoonTussenvoegsel = new SimpleStringProperty();
		this.relatiePersoonAchternaam = new SimpleStringProperty();
		this.relatiePersoonOmschrijving = new SimpleStringProperty();
		this.relatiePersoonTelefoon = new SimpleStringProperty();
		this.relatiePersoonMobiel = new SimpleStringProperty();
		this.relatiePersoonEmail = new SimpleStringProperty();
		this.relatiePersoonInternet = new SimpleStringProperty();
		this.relatiePersoonAantekening = new SimpleStringProperty();
	}

	public Long getRelatiePersoonId() {
		return relatiePersoonId.get();
	}

	public void setRelatiePersoonId(final Long relatiePersoonId) {
		this.relatiePersoonId.set(relatiePersoonId);
	}

	public ObjectProperty<Long> relatiePersoonIdProperty() {
		return relatiePersoonId;
	}

	public String getRelatiePersoonVoornaam() {
		return relatiePersoonVoornaam.get();
	}

	public void setRelatiePersoonVoornaam(final String relatiePersoonVoornaam) {
		this.relatiePersoonVoornaam.set(relatiePersoonVoornaam);
	}

	public StringProperty relatiePersoonVoornaamProperty() {
		return relatiePersoonVoornaam;
	}

	public String getRelatiePersoonTussenvoegsel() {
		return relatiePersoonTussenvoegsel.get();
	}

	public void setRelatiePersoonTussenvoegsel(final String relatiePersoonTussenvoegsel) {
		this.relatiePersoonTussenvoegsel.set(relatiePersoonTussenvoegsel);
	}

	public StringProperty relatiePersoonTussenvoegselProperty() {
		return relatiePersoonTussenvoegsel;
	}

	public String getRelatiePersoonAchternaam() {
		return relatiePersoonAchternaam.get();
	}

	public void setRelatiePersoonAchternaam(final String relatiePersoonAchternaam) {
		this.relatiePersoonAchternaam.set(relatiePersoonAchternaam);
	}

	public StringProperty relatiePersoonAchternaamProperty() {
		return relatiePersoonAchternaam;
	}

	public String getRelatiePersoonOmschrijving() {
		return relatiePersoonOmschrijving.get();
	}

	public void setRelatiePersoonOmschrijving(final String relatiePersoonOmschrijving) {
		this.relatiePersoonOmschrijving.set(relatiePersoonOmschrijving);
	}

	public StringProperty relatiePersoonOmschrijvingProperty() {
		return relatiePersoonOmschrijving;
	}

	public String getRelatiePersoonTelefoon() {
		return relatiePersoonTelefoon.get();
	}

	public void setRelatiePersoonTelefoon(final String relatiePersoonTelefoon) {
		this.relatiePersoonTelefoon.set(relatiePersoonTelefoon);
	}

	public StringProperty relatiePersoonTelefoonProperty() {
		return relatiePersoonTelefoon;
	}

	public String getRelatiePersoonMobiel() {
		return relatiePersoonMobiel.get();
	}

	public void setRelatiePersoonMobiel(final String relatiePersoonMobiel) {
		this.relatiePersoonMobiel.set(relatiePersoonMobiel);
	}

	public StringProperty relatiePersoonMobielProperty() {
		return relatiePersoonMobiel;
	}

	public String getRelatiePersoonEmail() {
		return relatiePersoonEmail.get();
	}

	public void setRelatiePersoonEmail(final String relatiePersoonEmail) {
		this.relatiePersoonEmail.set(relatiePersoonEmail);
	}

	public StringProperty relatiePersoonEmailProperty() {
		return relatiePersoonEmail;
	}

	public String getRelatiePersoonInternet() {
		return relatiePersoonInternet.get();
	}

	public void setRelatiePersoonInternet(final String relatiePersoonInternet) {
		this.relatiePersoonInternet.set(relatiePersoonInternet);
	}

	public StringProperty relatiePersoonInternetProperty() {
		return relatiePersoonInternet;
	}

	public String getRelatiePersoonAantekening() {
		return relatiePersoonAantekening.get();
	}

	public void setRelatiePersoonAantekening(final String relatiePersoonAantekening) {
		this.relatiePersoonAantekening.set(relatiePersoonAantekening);
	}

	public StringProperty relatiePersoonAantekeningProperty() {
		return relatiePersoonAantekening;
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
}
