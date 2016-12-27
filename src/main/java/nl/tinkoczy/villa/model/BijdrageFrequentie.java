package nl.tinkoczy.villa.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BijdrageFrequentie {

	private final LongProperty bijdrageFrequentieId;
	private final IntegerProperty bijdrageFrequentieCode;
	private final IntegerProperty bijdrageFrequentieAantalBetaalmomenten;
	private final StringProperty bijdrageFrequentieOmschrijving;

	public BijdrageFrequentie() {
		this(null, null);
	}

	public BijdrageFrequentie(final Long bijdrageFrequentieId, final Integer bijdrageFrequentieCode) {
		this.bijdrageFrequentieId = new SimpleLongProperty(bijdrageFrequentieId);
		this.bijdrageFrequentieCode = new SimpleIntegerProperty(bijdrageFrequentieCode);

		// Some initial dummy data, just for convenient testing.
		this.bijdrageFrequentieAantalBetaalmomenten = new SimpleIntegerProperty();
		this.bijdrageFrequentieOmschrijving = new SimpleStringProperty("");
	}

	public Long getBijdrageFrequentieId() {
		return bijdrageFrequentieId.get();
	}

	public void setBijdrageFrequentieId(final Long bijdrageFrequentieId) {
		this.bijdrageFrequentieId.set(bijdrageFrequentieId);
	}

	public LongProperty bijdrageFrequentieIdProperty() {
		return bijdrageFrequentieId;
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

	public Integer getBijdrageFrequentieAantalBetaalmomenten() {
		return bijdrageFrequentieAantalBetaalmomenten.get();
	}

	public void setBijdrageFrequentieAantalBetaalmomenten(final Integer bijdrageFrequentieAantalBetaalmomenten) {
		this.bijdrageFrequentieAantalBetaalmomenten.set(bijdrageFrequentieAantalBetaalmomenten);
	}

	public IntegerProperty bijdrageFrequentieAantalBetaalmomentenProperty() {
		return bijdrageFrequentieAantalBetaalmomenten;
	}

	public String getBijdrageFrequentieOmschrijving() {
		return bijdrageFrequentieOmschrijving.get();
	}

	public void setBijdrageFrequentieOmschrijving(final String bijdrageFrequentieOmschrijving) {
		this.bijdrageFrequentieOmschrijving.set(bijdrageFrequentieOmschrijving);
	}

	public StringProperty bijdrageFrequentieOmschrijvingProperty() {
		return bijdrageFrequentieOmschrijving;
	}
}
