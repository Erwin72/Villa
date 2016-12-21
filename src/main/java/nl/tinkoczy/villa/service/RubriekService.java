package nl.tinkoczy.villa.service;

import java.util.ArrayList;
import java.util.List;

import nl.tinkoczy.villa.domain.RubriekEntity;
import nl.tinkoczy.villa.model.Rubriek;

public class RubriekService implements IRubriekService {

	public RubriekService() {
	}

	@Override
	public void saveOrUpdateRubriek(final Rubriek rubriek) {
		DataBroker.saveOrUpdate(convert(rubriek));
	}

	@Override
	public void deleteRubriek(final Rubriek rubriek) {
		DataBroker.delete(convert(rubriek));
	}

	@Override
	public List<Rubriek> getAllRubrieken() {
		List<Rubriek> results = new ArrayList<>();
		for (RubriekEntity rubriekEntity : DataBroker.getAllRubrieken()) {
			results.add(convert(rubriekEntity));
		}
		return results;
	}

	@Override
	public Rubriek getRubriekById(final int id) {
		RubriekEntity rubriekEntity = DataBroker.getRubriekById(id);
		return convert(rubriekEntity);
	}

	@Override
	public Rubriek getRubriekByRubriekNummer(final Integer rubriekNummer) {
		RubriekEntity rubriekEntity = DataBroker.getRubriekByRubriekNummer(rubriekNummer);
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
