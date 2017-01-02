package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Bewoner;

public interface IBewonerService {

	void saveOrUpdateBewoner(Bewoner bewoner);

	void saveOrUpdateBewonerWithAppartement(Bewoner bewoner, Long appartementId);

	void deleteBewoner(Bewoner bewoner);

	List<Bewoner> getAllBewoners();

	Bewoner getBewonerById(long id);

	List<Bewoner> getBewonersByAppartement(Long appartementId);
}
