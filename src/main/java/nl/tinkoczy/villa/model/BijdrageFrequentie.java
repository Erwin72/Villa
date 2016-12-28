package nl.tinkoczy.villa.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nl.tinkoczy.villa.util.InitBijdrageFrequentieData;

public class BijdrageFrequentie {

	private final ObjectProperty<Long> bijdrageFrequentieId;
	private final ObjectProperty<Integer> bijdrageFrequentieCode;
	private final ObjectProperty<Integer> bijdrageFrequentieAantalBetaalmomenten;
	private final StringProperty bijdrageFrequentieOmschrijving;

	public BijdrageFrequentie() {
		this(null, null);
	}

	public BijdrageFrequentie(final Long bijdrageFrequentieId, final Integer bijdrageFrequentieCode) {
		this.bijdrageFrequentieId = new SimpleObjectProperty<Long>(bijdrageFrequentieId);
		this.bijdrageFrequentieCode = new SimpleObjectProperty<Integer>(bijdrageFrequentieCode);

		// Some initial dummy data, just for convenient testing.
		this.bijdrageFrequentieAantalBetaalmomenten = new SimpleObjectProperty<Integer>();
		this.bijdrageFrequentieOmschrijving = new SimpleStringProperty("");
	}

	public BijdrageFrequentie(final InitBijdrageFrequentieData bijdrageFrequentieData) {
		this.bijdrageFrequentieId = new SimpleObjectProperty<Long>(null);
		this.bijdrageFrequentieCode = new SimpleObjectProperty<Integer>(
				bijdrageFrequentieData.getBijdrageFrequentieCode());

		// Some initial dummy data, just for convenient testing.
		this.bijdrageFrequentieAantalBetaalmomenten = new SimpleObjectProperty<Integer>(
				bijdrageFrequentieData.getBijdrageFrequentieAantalBetaalmomenten());
		this.bijdrageFrequentieOmschrijving = new SimpleStringProperty(
				bijdrageFrequentieData.getBijdrageFrequentieOmschrijving());
	}

	public Long getBijdrageFrequentieId() {
		return bijdrageFrequentieId.get();
	}

	public void setBijdrageFrequentieId(final Long bijdrageFrequentieId) {
		this.bijdrageFrequentieId.set(bijdrageFrequentieId);
	}

	public ObjectProperty<Long> bijdrageFrequentieIdProperty() {
		return bijdrageFrequentieId;
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

	public Integer getBijdrageFrequentieAantalBetaalmomenten() {
		return bijdrageFrequentieAantalBetaalmomenten.get();
	}

	public void setBijdrageFrequentieAantalBetaalmomenten(final Integer bijdrageFrequentieAantalBetaalmomenten) {
		this.bijdrageFrequentieAantalBetaalmomenten.set(bijdrageFrequentieAantalBetaalmomenten);
	}

	public ObjectProperty<Integer> bijdrageFrequentieAantalBetaalmomentenProperty() {
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
