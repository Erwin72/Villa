package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.AppartementEntity;
import nl.tinkoczy.villa.domain.BewonerEntity;
import nl.tinkoczy.villa.model.Bewoner;
import nl.tinkoczy.villa.service.IBewonerService;

public class BewonerService implements IBewonerService {

	final static Logger logger = LoggerFactory.getLogger(BewonerService.class);

	public BewonerService() {
	}

	@Override
	public void saveOrUpdateBewoner(final Bewoner bewoner) {
		BewonerEntity bewonerEntity = convert(bewoner);
		logger.debug("saveOrUpdateBewoner: " + bewonerEntity.toString());
		DataBroker.saveOrUpdate(bewonerEntity);
	}

	@Override
	public void saveOrUpdateBewonerWithAppartement(final Bewoner bewoner, final Long appartementId) {
		AppartementEntity appartementEntity = new AppartementEntity(appartementId);
		BewonerEntity bewonerEntity = convert(bewoner);
		bewonerEntity.setAppartement(appartementEntity);
		logger.debug("saveOrUpdateBewonerWithAppartement: " + bewonerEntity.toString());
		DataBroker.saveOrUpdate(bewonerEntity);
	}

	@Override
	public void deleteBewoner(final Bewoner bewoner) {
		BewonerEntity bewonerEntity = convert(bewoner);
		logger.debug("deleteBewoner: " + bewonerEntity.toString());
		DataBroker.delete(bewonerEntity);
	}

	@Override
	public List<Bewoner> getAllBewoners() {
		List<Bewoner> results = new ArrayList<>();
		for (BewonerEntity bewonerEntity : DataBroker.getAllBewoners()) {
			logger.debug("getAllBewoners: " + bewonerEntity.toString());
			results.add(convert(bewonerEntity));
		}
		return results;
	}

	@Override
	public Bewoner getBewonerById(final long id) {
		BewonerEntity bewonerEntity = DataBroker.getBewonerById(id);
		logger.debug("getBewonerById: id=" + id + ", result=" + bewonerEntity.toString());
		return convert(bewonerEntity);
	}

	@Override
	public List<Bewoner> getBewonersByAppartement(final Long appartementId) {
		List<Bewoner> results = new ArrayList<>();
		for (BewonerEntity bewonerEntity : DataBroker.getBewonersByAppartement(appartementId)) {
			logger.debug("getBewonersByAppartement: appartementId=" + appartementId + ", result="
					+ bewonerEntity.toString());
			results.add(convert(bewonerEntity));
		}
		return results;
	}

	private Bewoner convert(final BewonerEntity bewonerEntity) {
		Bewoner bewoner = new Bewoner();
		bewoner.setBewonerId(bewonerEntity.getId());
		bewoner.setBewonerAantekening(bewonerEntity.getBewonerAantekening());
		bewoner.setBewonerAchternaam(bewonerEntity.getBewonerAchternaam());
		bewoner.setBewonerEmail(bewonerEntity.getBewonerEmail());
		bewoner.setBewonerGeslacht(bewonerEntity.getBewonerGeslacht());
		bewoner.setBewonerInternet(bewonerEntity.getBewonerInternet());
		bewoner.setBewonerIsEigenaar(bewonerEntity.getBewonerIsEigenaar());
		bewoner.setBewonerMobiel(bewonerEntity.getBewonerMobiel());
		bewoner.setBewonerTelefoon(bewonerEntity.getBewonerTelefoon());
		bewoner.setBewonerTussenvoegsel(bewonerEntity.getBewonerTussenvoegsel());
		bewoner.setBewonerVoornaam(bewonerEntity.getBewonerVoornaam());
		if (bewonerEntity.getAppartement() != null) {
			bewoner.setAppartementFk(bewonerEntity.getAppartement().getId());
		}
		return bewoner;
	}

	private BewonerEntity convert(final Bewoner bewoner) {
		BewonerEntity bewonerEntity = new BewonerEntity();
		if (bewoner.getBewonerId() != null) {
			bewonerEntity.setId(bewoner.getBewonerId());
		}
		bewonerEntity.setBewonerAantekening(bewoner.getBewonerAantekening());
		bewonerEntity.setBewonerAchternaam(bewoner.getBewonerAchternaam());
		bewonerEntity.setBewonerEmail(bewoner.getBewonerEmail());
		bewonerEntity.setBewonerGeslacht(bewoner.getBewonerGeslacht());
		bewonerEntity.setBewonerInternet(bewoner.getBewonerInternet());
		bewonerEntity.setBewonerIsEigenaar(bewoner.getBewonerIsEigenaar());
		bewonerEntity.setBewonerMobiel(bewoner.getBewonerMobiel());
		bewonerEntity.setBewonerTelefoon(bewoner.getBewonerTelefoon());
		bewonerEntity.setBewonerTussenvoegsel(bewoner.getBewonerTussenvoegsel());
		bewonerEntity.setBewonerVoornaam(bewoner.getBewonerVoornaam());
		return bewonerEntity;
	}

}
