package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.RelatieEntity;
import nl.tinkoczy.villa.model.Relatie;
import nl.tinkoczy.villa.service.IRelatieService;

public class RelatieService implements IRelatieService {

	final static Logger logger = LoggerFactory.getLogger(RelatieService.class);

	public RelatieService() {
	}

	@Override
	public void saveOrUpdateRelatie(final Relatie relatie) {
		RelatieEntity relatieEntity = convert(relatie);
		logger.debug("saveOrUpdateRelatie: " + relatieEntity.toString());
		DataBroker.saveOrUpdate(relatieEntity);
	}

	@Override
	public void deleteRelatie(final Relatie relatie) {
		RelatieEntity relatieEntity = convert(relatie);
		logger.debug("deleteRelatie: " + relatieEntity.toString());
		DataBroker.delete(relatieEntity);
	}

	@Override
	public List<Relatie> getAllRelaties() {
		List<Relatie> results = new ArrayList<>();
		for (RelatieEntity relatieEntity : DataBroker.getAllRelaties()) {
			logger.debug("getAllRelaties: " + relatieEntity.toString());
			results.add(convert(relatieEntity));
		}
		return results;
	}

	@Override
	public Relatie getRelatieById(final long id) {
		RelatieEntity relatieEntity = DataBroker.getRelatieById(id);
		logger.debug("getRelatieById: id=" + id + ", result=" + relatieEntity.toString());
		return convert(relatieEntity);
	}

	@Override
	public Relatie getRelatieByRelatieCode(final String relatieCode) {
		RelatieEntity relatieEntity = DataBroker.getRelatieByRelatieCode(relatieCode);
		logger.debug("getRelatieByRelatieCode: relatieCode=" + relatieCode + ", result=" + relatieEntity.toString());
		return convert(relatieEntity);
	}

	private Relatie convert(final RelatieEntity relatieEntity) {
		Relatie relatie = new Relatie();
		relatie.setRelatieId(relatieEntity.getId());
		relatie.setRelatieAantekening(relatieEntity.getRelatieAantekening());
		relatie.setRelatieAdresPlaats(relatieEntity.getRelatieAdresPlaats());
		relatie.setRelatieAdresPostcode(relatieEntity.getRelatieAdresPostcode());
		relatie.setRelatieAdresStraat(relatieEntity.getRelatieAdresStraat());
		relatie.setRelatieBankIBAN(relatieEntity.getRelatieBankIBAN());
		relatie.setRelatieBankNaam(relatieEntity.getRelatieBankNaam());
		relatie.setRelatieCode(relatieEntity.getRelatieCode());
		relatie.setRelatieNaam(relatieEntity.getRelatieNaam());
		relatie.setRelatieOmschrijving(relatieEntity.getRelatieOmschrijving());
		relatie.setRelatiePostbusNummer(relatieEntity.getRelatiePostbusNummer());
		relatie.setRelatiePostbusPlaats(relatieEntity.getRelatiePostbusPlaats());
		relatie.setRelatiePostbusPostcode(relatieEntity.getRelatiePostbusPostcode());
		return relatie;
	}

	private RelatieEntity convert(final Relatie relatie) {
		RelatieEntity relatieEntity = new RelatieEntity();
		if (relatie.getRelatieId() != null) {
			relatieEntity.setId(relatie.getRelatieId());
		}
		relatieEntity.setRelatieAantekening(relatie.getRelatieAantekening());
		relatieEntity.setRelatieAdresPlaats(relatie.getRelatieAdresPlaats());
		relatieEntity.setRelatieAdresPostcode(relatie.getRelatieAdresPostcode());
		relatieEntity.setRelatieAdresStraat(relatie.getRelatieAdresStraat());
		relatieEntity.setRelatieBankIBAN(relatie.getRelatieBankIBAN());
		relatieEntity.setRelatieBankNaam(relatie.getRelatieBankNaam());
		relatieEntity.setRelatieCode(relatie.getRelatieCode());
		relatieEntity.setRelatieNaam(relatie.getRelatieNaam());
		relatieEntity.setRelatieOmschrijving(relatie.getRelatieOmschrijving());
		relatieEntity.setRelatiePostbusNummer(relatie.getRelatiePostbusNummer());
		relatieEntity.setRelatiePostbusPlaats(relatie.getRelatiePostbusPlaats());
		relatieEntity.setRelatiePostbusPostcode(relatie.getRelatiePostbusPostcode());
		return relatieEntity;
	}
}
