package nl.tinkoczy.villa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Faktuur {

	private final ObjectProperty<Long> faktuurId;
	private final StringProperty faktuurNummer;
	private final ObjectProperty<LocalDate> faktuurDatum;
	private final ObjectProperty<BigDecimal> faktuurBedrag;
	private final ObjectProperty<BigDecimal> faktuurSaldo;
	private final StringProperty faktuurOmschrijving;
	private final BooleanProperty faktuurVorigePeriode;
	private final BooleanProperty faktuurVerwerkt;
	private final StringProperty relatieCode;
	private final ObjectProperty<Integer> postNummer;

	public Faktuur() {
		this(null, null, null, null, null);
	}

	public Faktuur(final Long faktuurId, final String faktuurNummer, final String relatieCode, final Integer postNummer,
			final LocalDate faktuurDatum) {
		this.faktuurId = new SimpleObjectProperty<Long>(faktuurId);
		this.faktuurNummer = new SimpleStringProperty(faktuurNummer);
		this.relatieCode = new SimpleStringProperty(relatieCode);
		this.postNummer = new SimpleObjectProperty<Integer>(postNummer);
		this.faktuurDatum = new SimpleObjectProperty<>(faktuurDatum);

		// Some initial dummy data, just for convenient testing.
		this.faktuurBedrag = new SimpleObjectProperty<BigDecimal>(new BigDecimal(0));
		this.faktuurSaldo = new SimpleObjectProperty<BigDecimal>(new BigDecimal(0));
		this.faktuurOmschrijving = new SimpleStringProperty("");
		this.faktuurVorigePeriode = new SimpleBooleanProperty(false);
		this.faktuurVerwerkt = new SimpleBooleanProperty(false);
	}

	public Long getFaktuurId() {
		return faktuurId.get();
	}

	public void setFaktuurId(final Long faktuurId) {
		this.faktuurId.set(faktuurId);
	}

	public ObjectProperty<Long> faktuurIdProperty() {
		return faktuurId;
	}

	public String getFaktuurNummer() {
		return faktuurNummer.get();
	}

	public void setFaktuurNummer(final String faktuurNummer) {
		this.faktuurNummer.set(faktuurNummer);
	}

	public StringProperty faktuurNummerProperty() {
		return faktuurNummer;
	}

	public LocalDate getFaktuurDatum() {
		return faktuurDatum.get();
	}

	public void setFaktuurDatum(final LocalDate faktuurDatum) {
		this.faktuurDatum.set(faktuurDatum);
	}

	public ObjectProperty<LocalDate> faktuurDatumProperty() {
		return faktuurDatum;
	}

	public BigDecimal getFaktuurBedrag() {
		return faktuurBedrag.get();
	}

	public void setFaktuurBedrag(final BigDecimal faktuurBedrag) {
		this.faktuurBedrag.set(faktuurBedrag);
	}

	public ObjectProperty<BigDecimal> faktuurBedragProperty() {
		return faktuurBedrag;
	}

	public BigDecimal getFaktuurSaldo() {
		return faktuurSaldo.get();
	}

	public void setFaktuurSaldo(final BigDecimal faktuurSaldo) {
		this.faktuurSaldo.set(faktuurSaldo);
	}

	public ObjectProperty<BigDecimal> faktuurSaldoProperty() {
		return faktuurSaldo;
	}

	public String getFaktuurOmschrijving() {
		return faktuurOmschrijving.get();
	}

	public void setFaktuurOmschrijving(final String faktuurOmschrijving) {
		this.faktuurOmschrijving.set(faktuurOmschrijving);
	}

	public StringProperty faktuurOmschrijvingProperty() {
		return faktuurOmschrijving;
	}

	public Boolean isFaktuurVorigePeriode() {
		return faktuurVorigePeriode.get();
	}

	public void setFaktuurVorigePeriode(final Boolean faktuurVorigePeriode) {
		this.faktuurVorigePeriode.set(faktuurVorigePeriode);
	}

	public BooleanProperty faktuurVorigePeriodeProperty() {
		return faktuurVorigePeriode;
	}

	public Boolean isFaktuurVerwerkt() {
		return faktuurVerwerkt.get();
	}

	public void setFaktuurVerwerkt(final Boolean faktuurVerwerkt) {
		this.faktuurVerwerkt.set(faktuurVerwerkt);
	}

	public BooleanProperty faktuurVerwerktProperty() {
		return faktuurVerwerkt;
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

	public Integer getPostNummer() {
		return postNummer.get();
	}

	public void setPostNummer(final Integer postNummer) {
		this.postNummer.set(postNummer);
	}

	public ObjectProperty<Integer> postNummerProperty() {
		return postNummer;
	}
}
