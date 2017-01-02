package nl.tinkoczy.villa.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bewoner {

	private final ObjectProperty<Long> bewonerId;
	private final StringProperty bewonerVoornaam;
	private final StringProperty bewonerTussenvoegsel;
	private final StringProperty bewonerAchternaam;
	private final StringProperty bewonerGeslacht;
	private final StringProperty bewonerTelefoon;
	private final StringProperty bewonerMobiel;
	private final StringProperty bewonerEmail;
	private final StringProperty bewonerInternet;
	private final StringProperty bewonerAantekening;
	private final BooleanProperty bewonerIsEigenaar;
	private final ObjectProperty<Long> appartementFk;

	public Bewoner() {
		this(null);
	}

	public Bewoner(final Long bewonerId) {
		this.bewonerId = new SimpleObjectProperty<Long>(bewonerId);

		// Some initial dummy data, just for convenient testing.
		this.bewonerVoornaam = new SimpleStringProperty();
		this.bewonerTussenvoegsel = new SimpleStringProperty();
		this.bewonerAchternaam = new SimpleStringProperty();
		this.bewonerGeslacht = new SimpleStringProperty("M");
		this.bewonerTelefoon = new SimpleStringProperty();
		this.bewonerMobiel = new SimpleStringProperty();
		this.bewonerEmail = new SimpleStringProperty();
		this.bewonerInternet = new SimpleStringProperty();
		this.bewonerAantekening = new SimpleStringProperty();
		this.bewonerIsEigenaar = new SimpleBooleanProperty(true);
		this.appartementFk = new SimpleObjectProperty<Long>();
	}

	public Long getBewonerId() {
		return bewonerId.get();
	}

	public void setBewonerId(final Long bewonerId) {
		this.bewonerId.set(bewonerId);
	}

	public ObjectProperty<Long> bewonerIdProperty() {
		return bewonerId;
	}

	public String getBewonerVoornaam() {
		return bewonerVoornaam.get();
	}

	public void setBewonerVoornaam(final String bewonerVoornaam) {
		this.bewonerVoornaam.set(bewonerVoornaam);
	}

	public StringProperty bewonerVoornaamProperty() {
		return bewonerVoornaam;
	}

	public String getBewonerTussenvoegsel() {
		return bewonerTussenvoegsel.get();
	}

	public void setBewonerTussenvoegsel(final String bewonerTussenvoegsel) {
		this.bewonerTussenvoegsel.set(bewonerTussenvoegsel);
	}

	public StringProperty bewonerTussenvoegselProperty() {
		return bewonerTussenvoegsel;
	}

	public String getBewonerAchternaam() {
		return bewonerAchternaam.get();
	}

	public void setBewonerAchternaam(final String bewonerAchternaam) {
		this.bewonerAchternaam.set(bewonerAchternaam);
	}

	public StringProperty bewonerAchternaamProperty() {
		return bewonerAchternaam;
	}

	public String getBewonerGeslacht() {
		return bewonerGeslacht.get();
	}

	public void setBewonerGeslacht(final String bewonerGeslacht) {
		this.bewonerGeslacht.set(bewonerGeslacht);
	}

	public StringProperty bewonerGeslachtProperty() {
		return bewonerGeslacht;
	}

	public String getBewonerTelefoon() {
		return bewonerGeslacht.get();
	}

	public void setBewonerTelefoon(final String bewonerTelefoon) {
		this.bewonerTelefoon.set(bewonerTelefoon);
	}

	public StringProperty bewonerTelefoonProperty() {
		return bewonerTelefoon;
	}

	public String getBewonerMobiel() {
		return bewonerMobiel.get();
	}

	public void setBewonerMobiel(final String bewonerMobiel) {
		this.bewonerMobiel.set(bewonerMobiel);
	}

	public StringProperty bewonerMobielProperty() {
		return bewonerMobiel;
	}

	public String getBewonerEmail() {
		return bewonerEmail.get();
	}

	public void setBewonerEmail(final String bewonerEmail) {
		this.bewonerEmail.set(bewonerEmail);
	}

	public StringProperty bewonerEmailProperty() {
		return bewonerEmail;
	}

	public String getBewonerInternet() {
		return bewonerInternet.get();
	}

	public void setBewonerInternet(final String bewonerInternet) {
		this.bewonerInternet.set(bewonerInternet);
	}

	public StringProperty bewonerInternetProperty() {
		return bewonerInternet;
	}

	public String getBewonerAantekening() {
		return bewonerAantekening.get();
	}

	public void setBewonerAantekening(final String bewonerAantekening) {
		this.bewonerAantekening.set(bewonerAantekening);
	}

	public StringProperty bewonerAantekeningProperty() {
		return bewonerAantekening;
	}

	public Boolean getBewonerIsEigenaar() {
		return bewonerIsEigenaar.get();
	}

	public void setBewonerIsEigenaar(final Boolean bewonerIsEigenaar) {
		this.bewonerIsEigenaar.set(bewonerIsEigenaar);
	}

	public BooleanProperty bewonerIsEigenaarProperty() {
		return bewonerIsEigenaar;
	}

	public Long getAppartementFk() {
		return appartementFk.get();
	}

	public void setAppartementFk(final Long appartementFk) {
		this.appartementFk.set(appartementFk);
	}

	public ObjectProperty<Long> appartementFkProperty() {
		return appartementFk;
	}
}
