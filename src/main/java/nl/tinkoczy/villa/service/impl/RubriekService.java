package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.RubriekEntity;
import nl.tinkoczy.villa.model.Rubriek;
import nl.tinkoczy.villa.service.IRubriekService;

public class RubriekService implements IRubriekService {

	final static Logger logger = LoggerFactory.getLogger(RubriekService.class);

	public RubriekService() {
	}

	@Override
	public void saveOrUpdateRubriek(final Rubriek rubriek) {
		RubriekEntity rubriekEntity = convert(rubriek);
		logger.debug("saveOrUpdateRubriek: " + rubriekEntity.toString());
		DataBroker.saveOrUpdate(rubriekEntity);
	}

	@Override
	public void deleteRubriek(final Rubriek rubriek) {
		RubriekEntity rubriekEntity = convert(rubriek);
		logger.debug("deleteRubriek: " + rubriekEntity.toString());
		DataBroker.delete(rubriekEntity);
	}

	@Override
	public List<Rubriek> getAllRubrieken() {
		List<Rubriek> results = new ArrayList<>();
		for (RubriekEntity rubriekEntity : DataBroker.getAllRubrieken()) {
			logger.debug("getAllRubrieken: " + rubriekEntity.toString());
			results.add(convert(rubriekEntity));
		}
		return results;
	}

	@Override
	public Rubriek getRubriekById(final int id) {
		RubriekEntity rubriekEntity = DataBroker.getRubriekById(id);
		logger.debug("getRubriekById: id=" + id + ", result=" + rubriekEntity.toString());
		return convert(rubriekEntity);
	}

	@Override
	public Rubriek getRubriekByRubriekNummer(final Integer rubriekNummer) {
		RubriekEntity rubriekEntity = DataBroker.getRubriekByRubriekNummer(rubriekNummer);
		logger.debug(
				"getRubriekByRubriekNummer: rubriekNummer=" + rubriekNummer + ", result=" + rubriekEntity.toString());
		return convert(rubriekEntity);
	}

	private Rubriek convert(final RubriekEntity rubriekEntity) {
		Rubriek rubriek = new Rubriek();
		rubriek.setRubriekId(rubriekEntity.getId());
		rubriek.setRubriekNummer(rubriekEntity.getRubriekNummer());
		rubriek.setRubriekOmschrijving(rubriekEntity.getRubriekOmschrijving());
		rubriek.setRubriekType(rubriekEntity.getRubriekType());
		rubriek.setRubriekInUit(rubriekEntity.getRubriekInUit());
		rubriek.setRubriekInExploRekening(rubriekEntity.getRubriekInExploRekening());
		return rubriek;
	}

	private RubriekEntity convert(final Rubriek rubriek) {
		RubriekEntity rubriekEntity = new RubriekEntity();
		if (rubriek.getRubriekId() != null) {
			rubriekEntity.setId(rubriek.getRubriekId());
		}
		rubriekEntity.setRubriekNummer(rubriek.getRubriekNummer());
		rubriekEntity.setRubriekOmschrijving(rubriek.getRubriekOmschrijving());
		rubriekEntity.setRubriekType(rubriek.getRubriekType());
		rubriekEntity.setRubriekInUit(rubriek.getRubriekInUit());
		rubriekEntity.setRubriekInExploRekening(rubriek.isRubriekInExploRekening());
		return rubriekEntity;
	}
}
