package nl.tinkoczy.villa.util;

public class InitBijdrageFrequentieData {

	private final int bijdrageFrequentieCode;
	private final int bijdrageFrequentieAantalBetaalmomenten;
	private final String bijdrageFrequentieOmschrijving;

	public InitBijdrageFrequentieData(final int bijdrageFrequentieCode,
			final int bijdrageFrequentieAantalBetaalmomenten, final String bijdrageFrequentieOmschrijving) {
		this.bijdrageFrequentieCode = bijdrageFrequentieCode;
		this.bijdrageFrequentieAantalBetaalmomenten = bijdrageFrequentieAantalBetaalmomenten;
		this.bijdrageFrequentieOmschrijving = bijdrageFrequentieOmschrijving;
	}

	public int getBijdrageFrequentieCode() {
		return bijdrageFrequentieCode;
	}

	public int getBijdrageFrequentieAantalBetaalmomenten() {
		return bijdrageFrequentieAantalBetaalmomenten;
	}

	public String getBijdrageFrequentieOmschrijving() {
		return bijdrageFrequentieOmschrijving;
	}
}
