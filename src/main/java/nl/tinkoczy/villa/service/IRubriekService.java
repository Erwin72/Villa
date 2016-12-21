package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Rubriek;

public interface IRubriekService {

	void saveOrUpdateRubriek(Rubriek rubriek);

	void deleteRubriek(Rubriek rubriek);

	List<Rubriek> getAllRubrieken();

	Rubriek getRubriekById(int id);

	Rubriek getRubriekByRubriekNummer(Integer rubriekNummer);
}
