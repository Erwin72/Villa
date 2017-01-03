package nl.tinkoczy.villa.util;

public class InitPostData {

	private final int rubriekNummer;
	private final int nummer;
	private final String omschrijving;

	public InitPostData(final int rubriekNummer, final int nummer, final String omschrijving) {
		this.rubriekNummer = rubriekNummer;
		this.nummer = nummer;
		this.omschrijving = omschrijving;
	}

	public int getRubriekNummer() {
		return rubriekNummer;
	}

	public int getNummer() {
		return nummer;
	}

	public String getOmschrijving() {
		return omschrijving;
	}
}
