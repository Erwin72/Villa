package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.AppartementEntity;
import nl.tinkoczy.villa.domain.BijdrageSchemaEntity;
import nl.tinkoczy.villa.model.Appartement;
import nl.tinkoczy.villa.service.IAppartementService;

public class AppartementService implements IAppartementService {

	final static Logger logger = LoggerFactory.getLogger(AppartementService.class);

	public AppartementService() {
	}

	@Override
	public void saveOrUpdateAppartement(final Appartement appartement) {
		AppartementEntity appartementEntity = convert(appartement);
		logger.debug("saveOrUpdateAppartement: " + appartementEntity.toString());
		DataBroker.saveOrUpdate(appartementEntity);
	}

	@Override
	public void saveOrUpdatePostWithBijdrageSchema(final Appartement appartement, final Long bijdrageSchemaId) {
		BijdrageSchemaEntity bijdrageSchemaEntity = new BijdrageSchemaEntity(bijdrageSchemaId);
		AppartementEntity appartementEntity = convert(appartement);
		appartementEntity.setBijdrageSchema(bijdrageSchemaEntity);
		logger.debug("saveOrUpdatePostWithBijdrageSchema: " + appartementEntity.toString());
		DataBroker.saveOrUpdate(appartementEntity);
	}

	@Override
	public void deleteAppartement(final Appartement appartement) {
		AppartementEntity appartementEntity = convert(appartement);
		logger.debug("deleteAppartement: " + appartementEntity.toString());
		DataBroker.delete(appartementEntity);
	}

	@Override
	public List<Appartement> getAllAppartementen() {
		List<Appartement> results = new ArrayList<>();
		for (AppartementEntity appartementEntity : DataBroker.getAllAppartementen()) {
			logger.debug("getAllAppartementen: " + appartementEntity.toString());
			results.add(convert(appartementEntity));
		}
		return results;
	}

	@Override
	public Appartement getAppartementById(final long id) {
		AppartementEntity appartementEntity = DataBroker.getAppartementById(id);
		logger.debug("getAppartementById: id=" + id + ", result=" + appartementEntity.toString());
		return convert(appartementEntity);
	}

	@Override
	public Appartement getAppartementByAppartementCode(final String appartementCode) {
		AppartementEntity appartementEntity = DataBroker.getAppartementByAppartementCode(appartementCode);
		logger.debug("getAppartementByAppartementCode: appartementCode=" + appartementCode + ", result="
				+ appartementEntity.toString());
		return convert(appartementEntity);
	}

	private Appartement convert(final AppartementEntity appartementEntity) {
		Appartement appartement = new Appartement();
		appartement.setAppartementId(appartementEntity.getId());
		appartement.setAppartementCode(appartementEntity.getAppartementCode());
		appartement.setAppartementAdresStraat(appartementEntity.getAppartementAdresStraat());
		appartement.setAppartementAdresPostcode(appartementEntity.getAppartementAdresPostcode());
		appartement.setAppartementAdresPlaats(appartementEntity.getAppartementAdresPlaats());
		appartement.setAppartementTransportdatum(appartementEntity.getAppartementTransportdatum());
		if (appartementEntity.getBijdrageSchema() != null) {
			appartement.setBijdrageSchemaFk(appartementEntity.getBijdrageSchema().getId());
		}
		return appartement;
	}

	private AppartementEntity convert(final Appartement appartement) {
		AppartementEntity appartementEntity = new AppartementEntity();
		if (appartement.getAppartementId() != null) {
			appartementEntity.setId(appartement.getAppartementId());
		}
		appartementEntity.setAppartementCode(appartement.getAppartementCode());
		appartementEntity.setAppartementAdresStraat(appartement.getAppartementAdresStraat());
		appartementEntity.setAppartementAdresPostcode(appartement.getAppartementAdresPostcode());
		appartementEntity.setAppartementAdresPlaats(appartement.getAppartementAdresPlaats());
		appartementEntity.setAppartementTransportdatum(appartement.getAppartementTransportdatum());
		return appartementEntity;
	}
}
