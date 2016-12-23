package nl.tinkoczy.villa.util;

public enum InitRubriekData {

	REC1(100, "Bijdragen", "BIJDRAGE", "IN", false),
	REC2(110, "Restitutie bijdragen", "BIJDRAGE", "UIT", false),
	REC3(120, "Rente bijdragen", "RENTE_BIJDRAGE", "IN", false),
	REC4(200, "Huisvestingskosten", "STANDAARD", "UIT", true),
	REC5(300, "Subsidies", "STANDAARD", "IN", true),
	REC6(400, "Algemene kosten", "STANDAARD", "UIT", true),
	REC7(500, "Kantoorkosten", "STANDAARD", "UIT", true),
	REC8(600, "Uitkering verzekering", "STANDAARD", "IN", true),
	REC9(700, "Rente inkomsten", "STANDAARD", "IN", true),
	REC10(800, "Kruisposten uitgaven", "STANDAARD", "UIT", false),
	REC11(810, "Kruisposten inkomsten", "STANDAARD", "IN", false),
	REC12(900, "Foutieve inkomsten", "STANDAARD", "UIT", false),
	REC13(910, "Correctie foutieve overschrijving", "STANDAARD", "IN", false);

	private InitRubriekData(final int nummer, final String omschrijving, final String type, final String inUit,
			final boolean inExploitatie) {
		this.nummer = nummer;
		this.omschrijving = omschrijving;
		this.type = type;
		this.inUit = inUit;
		this.inExploitatie = inExploitatie;
	}

	private int nummer;
	private String omschrijving;
	private String type;
	private String inUit;
	private boolean inExploitatie;
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
