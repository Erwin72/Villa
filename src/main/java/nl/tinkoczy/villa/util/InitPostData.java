package nl.tinkoczy.villa.util;

public enum InitPostData {
	REC1(100, 101, "Maandelijkse bijdrage"),
	REC2(110, 111, "Restitutie bijdragen"),
	REC3(120, 121, "Rente bijdragen"),
	REC4(200, 201, "Glazenwasser"),
	REC5(200, 202, "Schoonmaak"),
	REC6(200, 203, "Tuinonderhoud"),
	REC7(200, 204, "Onderhoud gebouwen"),
	REC8(200, 205, "Gas, water en electra"),
	REC9(300, 301, "Premie winterschilder"),
	REC10(400, 401, "Verzekeringspremie aansprakelijkheid"),
	REC11(400, 402, "Verzekeringspremie opstal"),
	REC12(400, 403, "Vergoeding administrateur"),
	REC13(400, 404, "Contributie en abonnementen"),
	REC14(400, 405, "Kosten betalingsverkeer"),
	REC15(500, 501, "Vergaderkosten"),
	REC16(500, 502, "Porto-kosten"),
	REC17(500, 503, "Kopieerkosten"),
	REC18(500, 504, "Kantoorartikelen"),
	REC19(600, 601, "Uitkering verzekering"),
	REC20(700, 701, "Ontvangen rente"),
	REC21(800, 801, "Naar deposito rekening"),
	REC22(800, 802, "Naar rekening courant"),
	REC23(810, 811, "Van deposito rekening"),
	REC24(810, 812, "Van rekening courant"),
	REC25(900, 901, "Foutieve overschrijving"),
	REC26(910, 911, "Correctie foutieve overschrijving");

	private InitPostData(final int rubriekNummer, final int nummer, final String omschrijving) {
		this.rubriekNummer= rubriekNummer;
		this.nummer = nummer;
		this.omschrijving = omschrijving;
	}

	private int rubriekNummer;
	private int nummer;
	private String omschrijving;

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
