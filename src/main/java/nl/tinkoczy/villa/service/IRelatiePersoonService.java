package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.RelatiePersoon;

public interface IRelatiePersoonService {

	void saveOrUpdateRelatiePersoon(RelatiePersoon relatiePersoon);

	void saveOrUpdateRelatiePersoonWithRelatie(RelatiePersoon relatiePersoon, Long relatieId);

	void deleteRelatiePersoon(RelatiePersoon relatiePersoon);

	List<RelatiePersoon> getAllRelatiePersonen();

	RelatiePersoon getRelatiePersoonById(final long id);

	List<RelatiePersoon> getAllRelatiePersonenByRelatieCode(final String relatieCode);
}
