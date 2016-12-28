package nl.tinkoczy.villa.util;

import java.util.ArrayList;
import java.util.List;

import nl.tinkoczy.villa.model.BijdrageFrequentie;

public class InitBijdrageFrequentieDataGenerator {

	private static List<BijdrageFrequentie> bijdrageFrequentieList = new ArrayList<>();

	private InitBijdrageFrequentieDataGenerator() {
		//
	}

	public static void initDefaultBijdrageFrequenties() {
		for (InitBijdrageFrequentieData bijdrageFrequentieData : InitBijdrageFrequentieData.values()) {
			bijdrageFrequentieList.add(new BijdrageFrequentie(bijdrageFrequentieData));
		}
	}

	public static List<BijdrageFrequentie> getBijdrageFrequentieList() {
		return bijdrageFrequentieList;
	}
}
