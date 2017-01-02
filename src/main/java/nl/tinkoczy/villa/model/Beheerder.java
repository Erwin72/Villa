package nl.tinkoczy.villa.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Beheerder {

	private final ObjectProperty<Long> beheerderId;
	private final StringProperty beheerderVoornaam;
	private final StringProperty beheerderTussenvoegsel;
	private final StringProperty beheerderAchternaam;
	private final StringProperty beheerderAdresStraat;
	private final StringProperty beheerderAdresPostcode;
	private final StringProperty beheerderAdresPlaats;
	private final StringProperty beheerderAdresLand;
	private final StringProperty beheerderGeslacht;
	private final StringProperty beheerderTelefoon;
	private final StringProperty beheerderMobiel;
	private final StringProperty beheerderEmail;
	private final StringProperty beheerderInternet;
	private final StringProperty beheerderAantekening;
	private final BooleanProperty beheerderIsBewoner;
	private final ObjectProperty<Long> appartementFk;

	public Beheerder() {
		this(null);
	}

	public Beheerder(final Long beheerderId) {
		this.beheerderId = new SimpleObjectProperty<Long>(beheerderId);

		// Some initial dummy data, just for convenient testing.
		this.beheerderVoornaam = new SimpleStringProperty();
		this.beheerderTussenvoegsel = new SimpleStringProperty();
		this.beheerderAchternaam = new SimpleStringProperty();
		this.beheerderAdresStraat = new SimpleStringProperty();
		this.beheerderAdresPostcode = new SimpleStringProperty();
		this.beheerderAdresPlaats = new SimpleStringProperty();
		this.beheerderAdresLand = new SimpleStringProperty();
		this.beheerderGeslacht = new SimpleStringProperty("M");
		this.beheerderTelefoon = new SimpleStringProperty();
		this.beheerderMobiel = new SimpleStringProperty();
		this.beheerderEmail = new SimpleStringProperty();
		this.beheerderInternet = new SimpleStringProperty();
		this.beheerderAantekening = new SimpleStringProperty();
		this.beheerderIsBewoner = new SimpleBooleanProperty(true);
		this.appartementFk = new SimpleObjectProperty<Long>();
	}

	public Long getBeheerderId() {
		return beheerderId.get();
	}

	public void setBeheerderId(final Long beheerderId) {
		this.beheerderId.set(beheerderId);
	}

	public ObjectProperty<Long> beheerderIdProperty() {
		return beheerderId;
	}

	public String getBeheerderVoornaam() {
		return beheerderVoornaam.get();
	}

	public void setBeheerderVoornaam(final String beheerderVoornaam) {
		this.beheerderVoornaam.set(beheerderVoornaam);
	}

	public StringProperty beheerderVoornaamProperty() {
		return beheerderVoornaam;
	}

	public String getBeheerderTussenvoegsel() {
		return beheerderTussenvoegsel.get();
	}

	public void setBeheerderTussenvoegsel(final String beheerderTussenvoegsel) {
		this.beheerderTussenvoegsel.set(beheerderTussenvoegsel);
	}

	public StringProperty beheerderTussenvoegselProperty() {
		return beheerderTussenvoegsel;
	}

	public String getBeheerderAchternaam() {
		return beheerderAchternaam.get();
	}

	public void setBeheerderAchternaam(final String beheerderAchternaam) {
		this.beheerderAchternaam.set(beheerderAchternaam);
	}

	public StringProperty beheerderAchternaamProperty() {
		return beheerderAchternaam;
	}

	public String getBeheerderAdresStraat() {
		return beheerderAdresStraat.get();
	}

	public void setBeheerderAdresStraat(final String beheerderAdresStraat) {
		this.beheerderAdresStraat.set(beheerderAdresStraat);
	}

	public StringProperty beheerderAdresStraatProperty() {
		return beheerderAdresStraat;
	}

	public String getBeheerderAdresPostcode() {
		return beheerderAdresPostcode.get();
	}

	public void setBeheerderAdresPostcode(final String beheerderAdresPostcode) {
		this.beheerderAdresPostcode.set(beheerderAdresPostcode);
	}

	public StringProperty beheerderAdresPostcodeProperty() {
		return beheerderAdresPostcode;
	}

	public String getBeheerderAdresPlaats() {
		return beheerderAdresPlaats.get();
	}

	public void setBeheerderAdresPlaats(final String beheerderAdresPlaats) {
		this.beheerderAdresPlaats.set(beheerderAdresPlaats);
	}

	public StringProperty beheerderAdresPlaatsProperty() {
		return beheerderAdresPlaats;
	}

	public String getBeheerderAdresLand() {
		return beheerderAdresLand.get();
	}

	public void setBeheerderAdresLand(final String beheerderAdresLand) {
		this.beheerderAdresLand.set(beheerderAdresLand);
	}

	public StringProperty beheerderAdresLandProperty() {
		return beheerderAdresLand;
	}

	public String getBeheerderGeslacht() {
		return beheerderGeslacht.get();
	}

	public void setBeheerderGeslacht(final String beheerderGeslacht) {
		this.beheerderGeslacht.set(beheerderGeslacht);
	}

	public StringProperty beheerderGeslachtProperty() {
		return beheerderGeslacht;
	}

	public String getBeheerderTelefoon() {
		return beheerderGeslacht.get();
	}

	public void setBeheerderTelefoon(final String beheerderTelefoon) {
		this.beheerderTelefoon.set(beheerderTelefoon);
	}

	public StringProperty beheerderTelefoonProperty() {
		return beheerderTelefoon;
	}

	public String getBeheerderMobiel() {
		return beheerderMobiel.get();
	}

	public void setBeheerderMobiel(final String beheerderMobiel) {
		this.beheerderMobiel.set(beheerderMobiel);
	}

	public StringProperty beheerderMobielProperty() {
		return beheerderMobiel;
	}

	public String getBeheerderEmail() {
		return beheerderEmail.get();
	}

	public void setBeheerderEmail(final String beheerderEmail) {
		this.beheerderEmail.set(beheerderEmail);
	}

	public StringProperty beheerderEmailProperty() {
		return beheerderEmail;
	}

	public String getBeheerderInternet() {
		return beheerderInternet.get();
	}

	public void setBeheerderInternet(final String beheerderInternet) {
		this.beheerderInternet.set(beheerderInternet);
	}

	public StringProperty beheerderInternetProperty() {
		return beheerderInternet;
	}

	public String getBeheerderAantekening() {
		return beheerderAantekening.get();
	}

	public void setBeheerderAantekening(final String beheerderAantekening) {
		this.beheerderAantekening.set(beheerderAantekening);
	}

	public StringProperty beheerderAantekeningProperty() {
		return beheerderAantekening;
	}

	public Boolean getBeheerderIsBewoner() {
		return beheerderIsBewoner.get();
	}

	public void setBeheerderIsBewoner(final Boolean beheerderIsBewoner) {
		this.beheerderIsBewoner.set(beheerderIsBewoner);
	}

	public BooleanProperty beheerderIsBewonerProperty() {
		return beheerderIsBewoner;
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
