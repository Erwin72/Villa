package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Relatie;

public interface IRelatieService {

	void saveOrUpdateRelatie(Relatie relatie);

	void deleteRelatie(Relatie relatie);

	List<Relatie> getAllRelaties();

	Relatie getRelatieById(long id);

	Relatie getRelatieByRelatieCode(String relatieCode);

}
