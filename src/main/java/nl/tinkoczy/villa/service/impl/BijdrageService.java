package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.BijdrageEntity;
import nl.tinkoczy.villa.domain.BijdrageSchemaEntity;
import nl.tinkoczy.villa.model.Bijdrage;
import nl.tinkoczy.villa.service.IBijdrageService;

public class BijdrageService implements IBijdrageService {

	final static Logger logger = LoggerFactory.getLogger(BijdrageService.class);

	public BijdrageService() {
	}

	@Override
	public void saveOrUpdateBijdrage(final Bijdrage bijdrage) {
		BijdrageEntity bijdrageEntity = convert(bijdrage);
		logger.debug("saveOrUpdateBijdrage: " + bijdrageEntity.toString());
		DataBroker.saveOrUpdate(bijdrageEntity);
	}

	@Override
	public void saveOrUpdateBijdrageWithBijdrageSchema(final Bijdrage bijdrage, final String bijdrageSchemaNaam) {
		BijdrageSchemaEntity bijdrageSchemaEntity = DataBroker
				.getBijdrageSchemaByBijdrageSchemaNaam(bijdrageSchemaNaam);
		BijdrageEntity bijdrageEntity = convert(bijdrage);
		bijdrageEntity.setBijdrageSchema(bijdrageSchemaEntity);
		logger.debug("saveOrUpdateBijdrageWithBijdrageSchema: " + bijdrageEntity.toString());
		DataBroker.saveOrUpdate(bijdrageEntity);
	}

	@Override
	public void deleteBijdrage(final Bijdrage bijdrage) {
		BijdrageEntity bijdrageEntity = convert(bijdrage);
		logger.debug("deleteBijdrage: " + bijdrageEntity.toString());
		DataBroker.delete(bijdrageEntity);
	}

	@Override
	public List<Bijdrage> getAllBijdragen() {
		List<Bijdrage> results = new ArrayList<>();
		for (BijdrageEntity bijdrageEntity : DataBroker.getAllBijdragen()) {
			logger.debug("getAllBijdragen: " + bijdrageEntity.toString());
			results.add(convert(bijdrageEntity));
		}
		return results;
	}

	@Override
	public List<Bijdrage> getAllBijdragenSortByDatum() {
		List<Bijdrage> results = new ArrayList<>();
		for (BijdrageEntity bijdrageEntity : DataBroker.getAllBijdragenSortByDatum()) {
			logger.debug("getAllBijdragenSortByDatum: " + bijdrageEntity.toString());
			results.add(convert(bijdrageEntity));
		}
		return results;
	}

	@Override
	public List<Bijdrage> getAllBijdragenByBijdrageSchema(final String bijdrageSchemaNaam) {
		List<Bijdrage> results = new ArrayList<>();
		for (BijdrageEntity bijdrageEntity : DataBroker.getAllBijdragenByBijdrageSchema(bijdrageSchemaNaam)) {
			logger.debug("getAllBijdragenSortByDatum: " + bijdrageEntity.toString());
			results.add(convert(bijdrageEntity));
		}
		return results;
	}

	@Override
	public List<Bijdrage> getAllBijdragenByAppartementId(final long appartementId) {
		// TODO Auto-generated method stub
		return null;
	}

	private Bijdrage convert(final BijdrageEntity bijdrageEntity) {
		Bijdrage bijdrage = new Bijdrage();
		bijdrage.setBijdrageId(bijdrageEntity.getId());
		bijdrage.setBijdrageBedrag(bijdrageEntity.getBijdrageBedrag());
		bijdrage.setBijdrageDatum(bijdrageEntity.getBijdrageDatum());
		bijdrage.setBijdrageVoldaan(bijdrageEntity.getBijdrageVoldaan());
		if (bijdrageEntity.getBijdrageSchema() != null) {
			bijdrage.setBijdrageSchemaNaam(bijdrageEntity.getBijdrageSchema().getBijdrageSchemaNaam());
		}
		return bijdrage;
	}

	private BijdrageEntity convert(final Bijdrage bijdrage) {
		BijdrageEntity bijdrageEntity = new BijdrageEntity();
		if (bijdrage.getBijdrageId() != null) {
			bijdrageEntity.setId(bijdrage.getBijdrageId());
		}
		bijdrageEntity.setBijdrageBedrag(bijdrage.getBijdrageBedrag());
		bijdrageEntity.setBijdrageDatum(bijdrage.getBijdrageDatum());
		bijdrageEntity.setBijdrageVoldaan(bijdrage.getBijdrageVoldaan());
		return bijdrageEntity;
	}
}
