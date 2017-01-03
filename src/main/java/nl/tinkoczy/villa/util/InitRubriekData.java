package nl.tinkoczy.villa.util;

public class InitRubriekData {

	private final int nummer;
	private final String omschrijving;
	private final String type;
	private final String inUit;
	private final boolean inExploitatie;

	public InitRubriekData(final int nummer, final String omschrijving, final String type, final String inUit,
			final boolean inExploitatie) {
		this.nummer = nummer;
		this.omschrijving = omschrijving;
		this.type = type;
		this.inUit = inUit;
		this.inExploitatie = inExploitatie;
	}

	public int getNummer() {
		return nummer;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public String getType() {
		return type;
	}

	public String getInUit() {
		return inUit;
	}

	public boolean isInExploitatie() {
		return inExploitatie;
	}
}
