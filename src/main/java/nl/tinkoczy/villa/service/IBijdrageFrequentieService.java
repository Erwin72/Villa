package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.BijdrageFrequentie;

public interface IBijdrageFrequentieService {

	void saveOrUpdateBijdrageFrequentie(BijdrageFrequentie bijdrageFrequentie);

	void deleteBijdrageFrequentie(BijdrageFrequentie bijdrageFrequentie);

	List<BijdrageFrequentie> getAllBijdrageFrequenties();

	BijdrageFrequentie getBijdrageFrequentieById(long id);

	BijdrageFrequentie getBijdrageFrequentieByFrequentieCode(Integer bijdrageFrequentieCode);
}
