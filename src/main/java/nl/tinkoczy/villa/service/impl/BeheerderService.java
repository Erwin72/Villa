package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.AppartementEntity;
import nl.tinkoczy.villa.domain.BeheerderEntity;
import nl.tinkoczy.villa.model.Beheerder;
import nl.tinkoczy.villa.service.IBeheerderService;

public class BeheerderService implements IBeheerderService {

	final static Logger logger = LoggerFactory.getLogger(BeheerderService.class);

	public BeheerderService() {
	}

	@Override
	public void saveOrUpdateBeheerder(final Beheerder beheerder) {
		BeheerderEntity beheerderEntity = convert(beheerder);
		logger.debug("saveOrUpdateBeheerder: " + beheerderEntity.toString());
		DataBroker.saveOrUpdate(beheerderEntity);
	}

	@Override
	public void saveOrUpdateBeheerderWithAppartement(final Beheerder beheerder, final Long appartementId) {
		AppartementEntity appartementEntity = new AppartementEntity(appartementId);
		BeheerderEntity beheerderEntity = convert(beheerder);
		beheerderEntity.setAppartement(appartementEntity);
		logger.debug("saveOrUpdateBeheerderWithAppartement: " + beheerderEntity.toString());
		DataBroker.saveOrUpdate(beheerderEntity);
	}

	@Override
	public void deleteBeheerder(final Beheerder beheerder) {
		BeheerderEntity beheerderEntity = convert(beheerder);
		logger.debug("deleteBeheerder: " + beheerderEntity.toString());
		DataBroker.delete(beheerderEntity);
	}

	@Override
	public List<Beheerder> getAllBeheerders() {
		List<Beheerder> results = new ArrayList<>();
		for (BeheerderEntity beheerderEntity : DataBroker.getAllBeheerders()) {
			logger.debug("getAllBeheerders: " + beheerderEntity.toString());
			results.add(convert(beheerderEntity));
		}
		return results;
	}

	@Override
	public Beheerder getBeheerderById(final long id) {
		BeheerderEntity beheerderEntity = DataBroker.getBeheerderById(id);
		logger.debug("getBeheerderById: id=" + id + ", result=" + beheerderEntity.toString());
		return convert(beheerderEntity);
	}

	@Override
	public List<Beheerder> getBeheerdersByAppartement(final Long appartementId) {
		List<Beheerder> results = new ArrayList<>();
		for (BeheerderEntity beheerderEntity : DataBroker.getBeheerdersByAppartement(appartementId)) {
			logger.debug("getBeheerdersByAppartement: appartementId=" + appartementId + ", result="
					+ beheerderEntity.toString());
			results.add(convert(beheerderEntity));
		}
		return results;
	}

	private Beheerder convert(final BeheerderEntity beheerderEntity) {
		Beheerder beheerder = new Beheerder();
		beheerder.setBeheerderId(beheerderEntity.getId());
		beheerder.setBeheerderAantekening(beheerderEntity.getBeheerderAantekening());
		beheerder.setBeheerderAchternaam(beheerderEntity.getBeheerderAchternaam());
		beheerder.setBeheerderAdresStraat(beheerderEntity.getBeheerderAdresStraat());
		beheerder.setBeheerderAdresPostcode(beheerderEntity.getBeheerderAdresPostcode());
		beheerder.setBeheerderAdresPlaats(beheerderEntity.getBeheerderAdresPlaats());
		beheerder.setBeheerderAdresLand(beheerderEntity.getBeheerderAdresLand());
		beheerder.setBeheerderEmail(beheerderEntity.getBeheerderEmail());
		beheerder.setBeheerderGeslacht(beheerderEntity.getBeheerderGeslacht());
		beheerder.setBeheerderInternet(beheerderEntity.getBeheerderInternet());
		beheerder.setBeheerderIsBewoner(beheerderEntity.getBeheerderIsBewoner());
		beheerder.setBeheerderMobiel(beheerderEntity.getBeheerderMobiel());
		beheerder.setBeheerderTelefoon(beheerderEntity.getBeheerderTelefoon());
		beheerder.setBeheerderTussenvoegsel(beheerderEntity.getBeheerderTussenvoegsel());
		beheerder.setBeheerderVoornaam(beheerderEntity.getBeheerderVoornaam());
		if (beheerderEntity.getAppartement() != null) {
			beheerder.setAppartementFk(beheerderEntity.getAppartement().getId());
		}
		return beheerder;
	}

	private BeheerderEntity convert(final Beheerder beheerder) {
		BeheerderEntity beheerderEntity = new BeheerderEntity();
		if (beheerder.getBeheerderId() != null) {
			beheerderEntity.setId(beheerder.getBeheerderId());
		}
		beheerderEntity.setBeheerderAantekening(beheerder.getBeheerderAantekening());
		beheerderEntity.setBeheerderAchternaam(beheerder.getBeheerderAchternaam());
		beheerderEntity.setBeheerderAdresStraat(beheerder.getBeheerderAdresStraat());
		beheerderEntity.setBeheerderAdresPostcode(beheerder.getBeheerderAdresPostcode());
		beheerderEntity.setBeheerderAdresPlaats(beheerder.getBeheerderAdresPlaats());
		beheerderEntity.setBeheerderAdresLand(beheerder.getBeheerderAdresLand());
		beheerderEntity.setBeheerderEmail(beheerder.getBeheerderEmail());
		beheerderEntity.setBeheerderGeslacht(beheerder.getBeheerderGeslacht());
		beheerderEntity.setBeheerderInternet(beheerder.getBeheerderInternet());
		beheerderEntity.setBeheerderIsBewoner(beheerder.getBeheerderIsBewoner());
		beheerderEntity.setBeheerderMobiel(beheerder.getBeheerderMobiel());
		beheerderEntity.setBeheerderTelefoon(beheerder.getBeheerderTelefoon());
		beheerderEntity.setBeheerderTussenvoegsel(beheerder.getBeheerderTussenvoegsel());
		beheerderEntity.setBeheerderVoornaam(beheerder.getBeheerderVoornaam());
		return beheerderEntity;
	}
}
