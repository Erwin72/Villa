package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.BijdrageFrequentieEntity;
import nl.tinkoczy.villa.domain.BijdrageRenteEntity;
import nl.tinkoczy.villa.domain.BijdrageSchemaEntity;
import nl.tinkoczy.villa.model.BijdrageSchema;
import nl.tinkoczy.villa.service.IBijdrageSchemaService;

public class BijdrageSchemaService implements IBijdrageSchemaService {

	final static Logger logger = LoggerFactory.getLogger(BijdrageSchemaService.class);

	public BijdrageSchemaService() {
	}

	@Override
	public void saveOrUpdateBijdrageSchemaWithFrequentie(final BijdrageSchema bijdrageSchema,
			final int bijdrageFrequentieCode) {
		BijdrageFrequentieEntity bijdrageFrequentieEntity = DataBroker
				.getBijdrageFrequentieByFrequentieCode(bijdrageFrequentieCode);
		BijdrageSchemaEntity bijdrageSchemaEntity = convert(bijdrageSchema);
		bijdrageSchemaEntity.setBijdrageFrequentie(bijdrageFrequentieEntity);
		logger.debug("saveOrUpdateBijdrageSchemaWithFrequentie: " + bijdrageSchemaEntity.toString());
		DataBroker.saveOrUpdate(bijdrageSchemaEntity);
	}

	@Override
	public void saveOrUpdateBijdrageSchemaWithFrequentieAndRente(final BijdrageSchema bijdrageSchema,
			final int bijdrageFrequentieCode, final long bijdrageRenteFk) {
		BijdrageFrequentieEntity bijdrageFrequentieEntity = DataBroker
				.getBijdrageFrequentieByFrequentieCode(bijdrageFrequentieCode);
		BijdrageRenteEntity bijdrageRenteEntity = DataBroker.getBijdrageRenteById(bijdrageRenteFk);
		BijdrageSchemaEntity bijdrageSchemaEntity = convert(bijdrageSchema);
		bijdrageSchemaEntity.setBijdrageFrequentie(bijdrageFrequentieEntity);
		bijdrageSchemaEntity.setBijdrageRente(bijdrageRenteEntity);
		logger.debug("saveOrUpdateBijdrageSchemaWithFrequentieAndRente: " + bijdrageSchemaEntity.toString());
		DataBroker.saveOrUpdate(bijdrageSchemaEntity);
	}

	@Override
	public void deleteBijdrageSchema(final BijdrageSchema bijdrageSchema) {
		BijdrageSchemaEntity bijdrageSchemaEntity = convert(bijdrageSchema);
		logger.debug("deleteBijdrageSchema: " + bijdrageSchemaEntity.toString());
		DataBroker.delete(bijdrageSchemaEntity);
	}

	@Override
	public List<BijdrageSchema> getAllBijdrageSchemas() {
		List<BijdrageSchema> results = new ArrayList<>();
		for (BijdrageSchemaEntity bijdrageSchemaEntity : DataBroker.getAllBijdrageSchemas()) {
			logger.debug("getAllBijdrageSchemas: " + bijdrageSchemaEntity.toString());
			results.add(convert(bijdrageSchemaEntity));
		}
		return results;
	}

	@Override
	public BijdrageSchema getBijdrageSchemaById(final long id) {
		BijdrageSchemaEntity bijdrageSchemaEntity = DataBroker.getBijdrageSchemaById(id);
		logger.debug("getBijdrageSchemaById: id=" + id + ", result=" + bijdrageSchemaEntity.toString());
		return convert(bijdrageSchemaEntity);
	}

	private BijdrageSchema convert(final BijdrageSchemaEntity bijdrageSchemaEntity) {
		BijdrageSchema bijdrageSchema = new BijdrageSchema();
		bijdrageSchema.setBijdrageSchemaId(bijdrageSchemaEntity.getId());
		bijdrageSchema.setBijdrageSchemaNaam(bijdrageSchemaEntity.getBijdrageSchemaNaam());
		if (bijdrageSchemaEntity.getBijdrageFrequentie() != null) {
			bijdrageSchema.setBijdrageFrequentieCode(
					bijdrageSchemaEntity.getBijdrageFrequentie().getBijdrageFrequentieCode());
		}
		if (bijdrageSchemaEntity.getBijdrageRente() != null) {
			bijdrageSchema.setBijdrageRenteFk(bijdrageSchemaEntity.getBijdrageRente().getId());
		}
		return bijdrageSchema;
	}

	private BijdrageSchemaEntity convert(final BijdrageSchema bijdrageSchema) {
		BijdrageSchemaEntity bijdrageSchemaEntity = new BijdrageSchemaEntity();
		if (bijdrageSchema.getBijdrageSchemaId() != null) {
			bijdrageSchemaEntity.setId(bijdrageSchema.getBijdrageSchemaId());
		}
		bijdrageSchemaEntity.setBijdrageSchemaNaam(bijdrageSchema.getBijdrageSchemaNaam());
		return bijdrageSchemaEntity;
	}
}
