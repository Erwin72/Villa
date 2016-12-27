package nl.tinkoczy.villa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.RelatieEntity;
import nl.tinkoczy.villa.domain.RelatiePersoonEntity;
import nl.tinkoczy.villa.model.RelatiePersoon;

public class RelatiePersoonService implements IRelatiePersoonService {

	final static Logger logger = LoggerFactory.getLogger(RelatiePersoonService.class);

	public RelatiePersoonService() {
	}

	@Override
	public void saveOrUpdateRelatiePersoon(final RelatiePersoon relatiePersoon) {
		RelatiePersoonEntity relatiePersoonEntity = convert(relatiePersoon);
		logger.debug("saveOrUpdateRelatiePersoon: " + relatiePersoonEntity.toString());
		DataBroker.saveOrUpdate(relatiePersoonEntity);
	}

	@Override
	public void saveOrUpdateRelatiePersoonWithRelatie(final RelatiePersoon relatiePersoon, final Long relatieId) {
		RelatieEntity relatieEntity = new RelatieEntity(relatieId);
		RelatiePersoonEntity relatiePersoonEntity = convert(relatiePersoon);
		relatiePersoonEntity.setRelatie(relatieEntity);
		logger.debug("saveOrUpdateRelatiePersoonWithRelatie: " + relatiePersoonEntity.toString());
		DataBroker.saveOrUpdate(relatiePersoonEntity);
	}

	@Override
	public void deleteRelatiePersoon(final RelatiePersoon relatiePersoon) {
		RelatiePersoonEntity relatiePersoonEntity = convert(relatiePersoon);
		logger.debug("deleteRelatiePersoon: " + relatiePersoonEntity.toString());
		DataBroker.delete(relatiePersoonEntity);
	}

	@Override
	public List<RelatiePersoon> getAllRelatiePersonen() {
		List<RelatiePersoon> results = new ArrayList<>();
		for (RelatiePersoonEntity relatiePersoonEntity : DataBroker.getAllRelatiePersonen()) {
			logger.debug("getAllRelatiePersonen: " + relatiePersoonEntity.toString());
			results.add(convert(relatiePersoonEntity));
		}
		return results;
	}

	@Override
	public RelatiePersoon getRelatiePersoonById(final int id) {
		RelatiePersoonEntity relatiePersoonEntity = DataBroker.getRelatiePersoonById(id);
		logger.debug("getRelatiePersoonById: id=" + id + ", result=" + relatiePersoonEntity.toString());
		return convert(relatiePersoonEntity);
	}

	@Override
	public List<RelatiePersoon> getAllRelatiePersonenByRelatieCode(final String relatieCode) {
		List<RelatiePersoon> results = new ArrayList<>();
		for (RelatiePersoonEntity relatiePersoonEntity : DataBroker.getAllRelatiePersonenByRelatieCode(relatieCode)) {
			logger.debug("getAllRelatiePersonenByRelatieCode: relatieCode=" + relatieCode + ", result="
					+ relatiePersoonEntity.toString());
			results.add(convert(relatiePersoonEntity));
		}
		return results;
	}

	private RelatiePersoon convert(final RelatiePersoonEntity relatiePersoonEntity) {
		RelatiePersoon relatiePersoon = new RelatiePersoon();
		relatiePersoon.setRelatiePersoonId(relatiePersoonEntity.getId());
		relatiePersoon.setRelatiePersoonAantekening(relatiePersoonEntity.getRelatiePersoonAantekening());
		relatiePersoon.setRelatiePersoonAchternaam(relatiePersoonEntity.getRelatiePersoonAchternaam());
		relatiePersoon.setRelatiePersoonEmail(relatiePersoonEntity.getRelatiePersoonEmail());
		relatiePersoon.setRelatiePersoonInternet(relatiePersoonEntity.getRelatiePersoonInternet());
		relatiePersoon.setRelatiePersoonMobiel(relatiePersoonEntity.getRelatiePersoonMobiel());
		relatiePersoon.setRelatiePersoonOmschrijving(relatiePersoonEntity.getRelatiePersoonOmschrijving());
		relatiePersoon.setRelatiePersoonTelefoon(relatiePersoonEntity.getRelatiePersoonTelefoon());
		relatiePersoon.setRelatiePersoonTussenvoegsel(relatiePersoonEntity.getRelatiePersoonTussenvoegsel());
		relatiePersoon.setRelatiePersoonVoornaam(relatiePersoonEntity.getRelatiePersoonVoornaam());
		relatiePersoon.setRelatieCode(relatiePersoonEntity.getRelatie().getRelatieCode());
		return relatiePersoon;
	}

	private RelatiePersoonEntity convert(final RelatiePersoon relatiePersoon) {
		RelatiePersoonEntity relatiePersoonEntity = new RelatiePersoonEntity();
		if (relatiePersoon.getRelatiePersoonId() != null) {
			relatiePersoonEntity.setId(relatiePersoon.getRelatiePersoonId());
		}
		relatiePersoonEntity.setRelatiePersoonAantekening(relatiePersoon.getRelatiePersoonAantekening());
		relatiePersoonEntity.setRelatiePersoonAchternaam(relatiePersoon.getRelatiePersoonAchternaam());
		relatiePersoonEntity.setRelatiePersoonEmail(relatiePersoon.getRelatiePersoonEmail());
		relatiePersoonEntity.setRelatiePersoonInternet(relatiePersoon.getRelatiePersoonInternet());
		relatiePersoonEntity.setRelatiePersoonMobiel(relatiePersoon.getRelatiePersoonMobiel());
		relatiePersoonEntity.setRelatiePersoonOmschrijving(relatiePersoon.getRelatiePersoonOmschrijving());
		relatiePersoonEntity.setRelatiePersoonTelefoon(relatiePersoon.getRelatiePersoonTelefoon());
		relatiePersoonEntity.setRelatiePersoonTussenvoegsel(relatiePersoon.getRelatiePersoonTussenvoegsel());
		relatiePersoonEntity.setRelatiePersoonVoornaam(relatiePersoon.getRelatiePersoonVoornaam());
		return relatiePersoonEntity;
	}
}
