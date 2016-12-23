package nl.tinkoczy.villa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Boeking {

	private final ObjectProperty<Long> boekingId;
	private final ObjectProperty<LocalDate> boekingDatum;
	private final ObjectProperty<BigDecimal> boekingBedrag;
	private final StringProperty boekingOmschrijving;
	private final BooleanProperty boekingVorigePeriode;
	private final IntegerProperty boekstukVolgnummer;

	public Boeking() {
		this(null, null, null);
	}

	public Boeking(final Long boekingId, final LocalDate boekingDatum, final Integer boekstukVolgnummer) {
		this.boekingId = new SimpleObjectProperty<Long>(boekingId);
		this.boekingDatum = new SimpleObjectProperty<LocalDate>(boekingDatum);
		this.boekstukVolgnummer = new SimpleIntegerProperty(boekstukVolgnummer);

		// Some initial dummy data, just for convenient testing.
		this.boekingBedrag = new SimpleObjectProperty<>();
		this.boekingOmschrijving = new SimpleStringProperty("");
		this.boekingVorigePeriode = new SimpleBooleanProperty(false);
	}

	public Long getBoekingId() {
		return boekingId.get();
	}

	public void setBoekingId(final Long boekingId) {
		this.boekingId.set(boekingId);
	}

	public ObjectProperty<Long> boekingIdProperty() {
		return boekingId;
	}

	public LocalDate getBoekingDatum() {
		return boekingDatum.get();
	}

	public void setBoekingDatum(final LocalDate boekingDatum) {
		this.boekingDatum.set(boekingDatum);
	}

	public ObjectProperty<LocalDate> boekingDatumProperty() {
		return boekingDatum;
	}

	public BigDecimal getBoekingBedrag() {
		return boekingBedrag.get();
	}

	public void setBoekingBedrag(final BigDecimal boekingBedrag) {
		this.boekingBedrag.set(boekingBedrag);
	}

	public ObjectProperty<BigDecimal> boekingBedragProperty() {
		return boekingBedrag;
	}

	public String getBoekingOmschrijving() {
		return boekingOmschrijving.get();
	}

	public void setBoekingOmschrijving(final String boekingOmschrijving) {
		this.boekingOmschrijving.set(boekingOmschrijving);
	}

	public StringProperty boekingOmschrijvingProperty() {
		return boekingOmschrijving;
	}

	public Boolean getBoekingVorigePeriode() {
		return boekingVorigePeriode.get();
	}

	public void setBoekingVorigePeriode(final Boolean boekingVorigePeriode) {
		this.boekingVorigePeriode.set(boekingVorigePeriode);
	}

	public BooleanProperty boekingVorigePeriodeProperty() {
		return boekingVorigePeriode;
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
}
