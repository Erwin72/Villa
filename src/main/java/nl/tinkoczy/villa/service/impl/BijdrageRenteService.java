package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.BijdrageRenteEntity;
import nl.tinkoczy.villa.model.BijdrageRente;
import nl.tinkoczy.villa.service.IBijdrageRenteService;

public class BijdrageRenteService implements IBijdrageRenteService {

	final static Logger logger = LoggerFactory.getLogger(BijdrageRenteService.class);

	public BijdrageRenteService() {
	}

	@Override
	public void saveOrUpdateBijdrageRente(final BijdrageRente bijdrageRente) {
		BijdrageRenteEntity bijdrageRenteEntity = convert(bijdrageRente);
		logger.debug("saveOrUpdateBijdrageRente: " + bijdrageRenteEntity.toString());
		DataBroker.saveOrUpdate(bijdrageRenteEntity);
	}

	@Override
	public void deleteBijdrageRente(final BijdrageRente bijdrageRente) {
		BijdrageRenteEntity bijdrageRenteEntity = convert(bijdrageRente);
		logger.debug("deleteBijdrageRente: " + bijdrageRenteEntity.toString());
		DataBroker.delete(bijdrageRenteEntity);
	}

	@Override
	public List<BijdrageRente> getAllBijdrageRentes() {
		List<BijdrageRente> results = new ArrayList<>();
		for (BijdrageRenteEntity bijdrageRenteEntity : DataBroker.getAllBijdrageRentes()) {
			logger.debug("getAllBijdrageRentes: " + bijdrageRenteEntity.toString());
			results.add(convert(bijdrageRenteEntity));
		}
		return results;
	}

	@Override
	public BijdrageRente getBijdrageRenteById(final long id) {
		BijdrageRenteEntity bijdrageRenteEntity = DataBroker.getBijdrageRenteById(id);
		logger.debug("getBijdrageRenteById: id=" + id + ", result=" + bijdrageRenteEntity.toString());
		return convert(bijdrageRenteEntity);
	}

	private BijdrageRente convert(final BijdrageRenteEntity bijdrageRenteEntity) {
		BijdrageRente bijdrageRente = new BijdrageRente();
		bijdrageRente.setBijdrageRenteId(bijdrageRenteEntity.getId());
		bijdrageRente.setBijdrageRenteNaVervaldatum(bijdrageRenteEntity.getBijdrageRenteNaVervaldatum());
		bijdrageRente.setBijdrageRentePercentage(bijdrageRenteEntity.getBijdrageRentePercentage());
		return bijdrageRente;
	}

	private BijdrageRenteEntity convert(final BijdrageRente bijdrageRente) {
		BijdrageRenteEntity bijdrageRenteEntity = new BijdrageRenteEntity();
		if (bijdrageRente.getBijdrageRenteId() != null) {
			bijdrageRenteEntity.setId(bijdrageRente.getBijdrageRenteId());
		}
		bijdrageRenteEntity.setBijdrageRenteNaVervaldatum(bijdrageRente.getBijdrageRenteNaVervaldatum());
		bijdrageRenteEntity.setBijdrageRentePercentage(bijdrageRente.getBijdrageRentePercentage());
		return bijdrageRenteEntity;
	}
}
