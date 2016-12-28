package nl.tinkoczy.villa.util;

public enum InitBijdrageFrequentieData {
	REC1(1, 12, "Maand"), REC2(2, 6, "Tweemaandelijks"), REC3(3, 4, "Kwartaal"), REC4(4, 2, "Half jaar"), REC5(5, 1,
			"Jaar");

	private InitBijdrageFrequentieData(final int bijdrageFrequentieCode,
			final int bijdrageFrequentieAantalBetaalmomenten, final String bijdrageFrequentieOmschrijving) {
		this.bijdrageFrequentieCode = bijdrageFrequentieCode;
		this.bijdrageFrequentieAantalBetaalmomenten = bijdrageFrequentieAantalBetaalmomenten;
		this.bijdrageFrequentieOmschrijving = bijdrageFrequentieOmschrijving;
	}

	private int bijdrageFrequentieCode;
	private int bijdrageFrequentieAantalBetaalmomenten;
	private String bijdrageFrequentieOmschrijving;

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
