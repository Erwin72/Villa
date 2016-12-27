package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Bijdrage;

public interface IBijdrageService {

	void saveOrUpdateBijdrage(Bijdrage bijdrage);

	void saveOrUpdateBijdrageWithBijdrageSchema(Bijdrage bijdrage, String bijdrageSchemaNaam);

	void deleteBijdrage(Bijdrage bijdrage);

	List<Bijdrage> getAllBijdragen();

	List<Bijdrage> getAllBijdragenSortByDatum();

	List<Bijdrage> getAllBijdragenByBijdrageSchema(String bijdrageSchemaNaam);

	List<Bijdrage> getAllBijdragenByAppartementId(long appartementId);
}
