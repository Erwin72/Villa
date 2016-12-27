package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.BijdrageFrequentieEntity;
import nl.tinkoczy.villa.model.BijdrageFrequentie;
import nl.tinkoczy.villa.service.IBijdrageFrequentieService;

public class BijdrageFrequentieService implements IBijdrageFrequentieService {

	final static Logger logger = LoggerFactory.getLogger(BijdrageFrequentieService.class);

	public BijdrageFrequentieService() {
	}

	@Override
	public void saveOrUpdateBijdrageFrequentie(final BijdrageFrequentie bijdrageFrequentie) {
		BijdrageFrequentieEntity bijdrageFrequentieEntity = convert(bijdrageFrequentie);
		logger.debug("saveOrUpdateBijdrageFrequentie: " + bijdrageFrequentieEntity.toString());
		DataBroker.saveOrUpdate(bijdrageFrequentieEntity);
	}

	@Override
	public void deleteBijdrageFrequentie(final BijdrageFrequentie bijdrageFrequentie) {
		BijdrageFrequentieEntity bijdrageFrequentieEntity = convert(bijdrageFrequentie);
		logger.debug("deleteBijdrageFrequentie: " + bijdrageFrequentieEntity.toString());
		DataBroker.delete(bijdrageFrequentieEntity);
	}

	@Override
	public List<BijdrageFrequentie> getAllBijdrageFrequenties() {
		List<BijdrageFrequentie> results = new ArrayList<>();
		for (BijdrageFrequentieEntity bijdrageFrequentieEntity : DataBroker.getAllBijdrageFrequenties()) {
			logger.debug("getAllBijdrageFrequenties: " + bijdrageFrequentieEntity.toString());
			results.add(convert(bijdrageFrequentieEntity));
		}
		return results;
	}

	@Override
	public BijdrageFrequentie getBijdrageFrequentieById(final long id) {
		BijdrageFrequentieEntity bijdrageFrequentieEntity = DataBroker.getBijdrageFrequentieById(id);
		logger.debug("getBijdrageFrequentieById: id=" + id + ", result=" + bijdrageFrequentieEntity.toString());
		return convert(bijdrageFrequentieEntity);
	}

	@Override
	public BijdrageFrequentie getBijdrageFrequentieByFrequentieCode(final Integer bijdrageFrequentieCode) {
		BijdrageFrequentieEntity bijdrageFrequentieEntity = DataBroker
				.getBijdrageFrequentieByFrequentieCode(bijdrageFrequentieCode);
		logger.debug("getBijdrageFrequentieByFrequentieCode: bijdrageFrequentieCode=" + bijdrageFrequentieCode
				+ ", result=" + bijdrageFrequentieEntity.toString());
		return convert(bijdrageFrequentieEntity);
	}

	private BijdrageFrequentie convert(final BijdrageFrequentieEntity bijdrageFrequentieEntity) {
		BijdrageFrequentie bijdrageFrequentie = new BijdrageFrequentie();
		bijdrageFrequentie.setBijdrageFrequentieId(bijdrageFrequentieEntity.getId());
		bijdrageFrequentie.setBijdrageFrequentieAantalBetaalmomenten(
				bijdrageFrequentieEntity.getBijdrageFrequentieAantalBetaalmomenten());
		bijdrageFrequentie.setBijdrageFrequentieCode(bijdrageFrequentieEntity.getBijdrageFrequentieCode());
		bijdrageFrequentie
				.setBijdrageFrequentieOmschrijving(bijdrageFrequentieEntity.getBijdrageFrequentieOmschrijving());
		return bijdrageFrequentie;
	}

	private BijdrageFrequentieEntity convert(final BijdrageFrequentie bijdrageFrequentie) {
		BijdrageFrequentieEntity bijdrageFrequentieEntity = new BijdrageFrequentieEntity();
		if (bijdrageFrequentie.getBijdrageFrequentieId() != null) {
			bijdrageFrequentieEntity.setId(bijdrageFrequentie.getBijdrageFrequentieId());
		}
		bijdrageFrequentieEntity.setBijdrageFrequentieAantalBetaalmomenten(
				bijdrageFrequentie.getBijdrageFrequentieAantalBetaalmomenten());
		bijdrageFrequentieEntity.setBijdrageFrequentieCode(bijdrageFrequentie.getBijdrageFrequentieCode());
		bijdrageFrequentieEntity
				.setBijdrageFrequentieOmschrijving(bijdrageFrequentie.getBijdrageFrequentieOmschrijving());
		return bijdrageFrequentieEntity;
	}
}
