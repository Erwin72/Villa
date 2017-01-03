package nl.tinkoczy.villa.util;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVReader;
import nl.tinkoczy.villa.model.BijdrageFrequentie;
import nl.tinkoczy.villa.model.Post;
import nl.tinkoczy.villa.model.Rubriek;

public final class InitDataReader {

	final static Logger logger = LoggerFactory.getLogger(InitVillaData.class);

	private static List<BijdrageFrequentie> bijdrageFrequentieList = new ArrayList<>();
	private static List<Post> postList = new ArrayList<>();
	private static List<Rubriek> rubriekList = new ArrayList<>();

	private InitDataReader() {
		// static
	}

	@SuppressWarnings("resource")
	public static void initDefaultBijdrageFrequenties() {
		try {
			CSVReader reader = new CSVReader(new InputStreamReader(
					InitDataReader.class.getResourceAsStream("/initdata/initdata_bijdrage_frequentie.csv")));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {

				int bijdrageFrequentieCode = Integer.valueOf(nextLine[0]);
				int bijdrageFrequentieAantalBetaalmomenten = Integer.valueOf(nextLine[1]);
				String bijdrageFrequentieOmschrijving = nextLine[2];
				InitBijdrageFrequentieData bijdrageFrequentieData = new InitBijdrageFrequentieData(
						bijdrageFrequentieCode, bijdrageFrequentieAantalBetaalmomenten, bijdrageFrequentieOmschrijving);

				bijdrageFrequentieList.add(new BijdrageFrequentie(bijdrageFrequentieData));
			}

		} catch (Exception e) {

		}
	}

	@SuppressWarnings("resource")
	public static void initDefaultRubrieken() {
		try {
			CSVReader reader = new CSVReader(
					new InputStreamReader(InitDataReader.class.getResourceAsStream("/initdata/initdata_rubriek.csv")));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {

				int nummer = Integer.valueOf(nextLine[0]);
				String omschrijving = nextLine[1];
				String type = nextLine[2];
				String inUit = nextLine[3];
				boolean inExploitatie = Boolean.valueOf(nextLine[4]);

				InitRubriekData rubriekData = new InitRubriekData(nummer, omschrijving, type, inUit, inExploitatie);

				rubriekList.add(new Rubriek(rubriekData));
			}

		} catch (Exception e) {

		}
	}

	@SuppressWarnings("resource")
	public static void initDefaultPosten() {
		try {
			CSVReader reader = new CSVReader(
					new InputStreamReader(InitDataReader.class.getResourceAsStream("/initdata/initdata_post.csv")));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {

				int rubriekNummer = Integer.valueOf(nextLine[0]);
				int nummer = Integer.valueOf(nextLine[1]);
				String omschrijving = nextLine[2];
				InitPostData postData = new InitPostData(rubriekNummer, nummer, omschrijving);

				postList.add(new Post(postData));
			}

		} catch (Exception e) {

		}
	}

	public static List<BijdrageFrequentie> getBijdrageFrequentieList() {
		return bijdrageFrequentieList;
	}

	public static List<Post> getPostList() {
		return postList;
	}

	public static List<Rubriek> getRubriekList() {
		return rubriekList;
	}
}
