package nl.tinkoczy.villa.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Boekstuk {

	private final ObjectProperty<Long> boekstukId;
	private final IntegerProperty boekstukVolgnummer;
	private final ObjectProperty<LocalDate> boekstukDatum;
	private final IntegerProperty boekstukAfschriftnummer;
	private final StringProperty boekstukAfschriftOpmerking;
	private final StringProperty boekstukAantekening;
	private final StringProperty rekeningNaam;

	public Boekstuk() {
		this(null, null, null);
	}

	public Boekstuk(final Long boekstukId, final Integer boekstukVolgnummer, final LocalDate boekstukDatum) {
		this.boekstukId = new SimpleObjectProperty<Long>(boekstukId);
		this.boekstukVolgnummer = new SimpleIntegerProperty(boekstukVolgnummer);
		this.boekstukDatum = new SimpleObjectProperty<LocalDate>(boekstukDatum);

		// Some initial dummy data, just for convenient testing.
		this.boekstukAfschriftnummer = new SimpleIntegerProperty();
		this.boekstukAfschriftOpmerking = new SimpleStringProperty("");
		this.boekstukAantekening = new SimpleStringProperty("");
		this.rekeningNaam = new SimpleStringProperty("");
	}

	public Long getBoekstukId() {
		return boekstukId.get();
	}

	public void setBoekstukId(final Long boekstukId) {
		this.boekstukId.set(boekstukId);
	}

	public ObjectProperty<Long> boekstukIdProperty() {
		return boekstukId;
	}

	public Integer getBoekstukVolgnummer() {
		return boekstukVolgnummer.get();
	}

	public void setBoekstukVolgnummer(final Integer boekstukVolgnummer) {
		this.boekstukVolgnummer.set(boekstukVolgnummer);
	}

	public IntegerProperty boekstukVolgnummerProperty() {
		return boekstukVolgnummer;
	}

	public LocalDate getBoekstukDatum() {
		return boekstukDatum.get();
	}

	public void setBoekstukDatum(final LocalDate boekstukDatum) {
		this.boekstukDatum.set(boekstukDatum);
	}

	public ObjectProperty<LocalDate> boekstukDatumProperty() {
		return boekstukDatum;
	}

	public Integer getBoekstukAfschriftnummer() {
		return boekstukAfschriftnummer.get();
	}

	public void setBoekstukAfschriftnummer(final Integer boekstukAfschriftnummer) {
		this.boekstukAfschriftnummer.set(boekstukAfschriftnummer);
	}

	public IntegerProperty boekstukAfschriftnummerProperty() {
		return boekstukAfschriftnummer;
	}

	public String getBoekstukAfschriftOpmerking() {
		return boekstukAfschriftOpmerking.get();
	}

	public void setBoekstukAfschriftOpmerking(final String boekstukAfschriftOpmerking) {
		this.boekstukAfschriftOpmerking.set(boekstukAfschriftOpmerking);
	}

	public StringProperty boekstukAfschriftOpmerkingProperty() {
		return boekstukAfschriftOpmerking;
	}

	public String getBoekstukAantekening() {
		return boekstukAantekening.get();
	}

	public void setBoekstukAantekening(final String boekstukAantekening) {
		this.boekstukAantekening.set(boekstukAantekening);
	}

	public StringProperty boekstukAantekeningProperty() {
		return boekstukAantekening;
	}

	public String getRekeningNaam() {
		return rekeningNaam.get();
	}

	public void setRekeningNaam(final String rekeningNaam) {
		this.rekeningNaam.set(rekeningNaam);
	}

	public StringProperty rekeningNaamProperty() {
		return rekeningNaam;
	}
}
