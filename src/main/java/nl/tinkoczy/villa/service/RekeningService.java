package nl.tinkoczy.villa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.RekeningEntity;
import nl.tinkoczy.villa.model.Rekening;

public class RekeningService implements IRekeningService {

	final static Logger logger = LoggerFactory.getLogger(RekeningService.class);

	public RekeningService() {
	}

	@Override
	public void saveOrUpdateRekening(final Rekening rekening) {
		RekeningEntity rekeningEntity = convert(rekening);
		logger.debug("saveOrUpdateRekening: " + rekeningEntity.toString());
		DataBroker.saveOrUpdate(rekeningEntity);
	}

	@Override
	public void deleteRekening(final Rekening rekening) {
		RekeningEntity rekeningEntity = convert(rekening);
		logger.debug("deleteRekening: " + rekeningEntity.toString());
		DataBroker.delete(rekeningEntity);
	}

	@Override
	public List<Rekening> getAllRekeningen() {
		List<Rekening> results = new ArrayList<>();
		for (RekeningEntity rekeningEntity : DataBroker.getAllRekeningen()) {
			logger.debug("getAllRekeningen: " + rekeningEntity.toString());
			results.add(convert(rekeningEntity));
		}
		return results;
	}

	@Override
	public Rekening getRekeningById(final int id) {
		RekeningEntity rekeningEntity = DataBroker.getRekeningById(id);
		logger.debug("getRekeningById: id=" + id + ", result=" + rekeningEntity.toString());
		return convert(rekeningEntity);
	}

	@Override
	public Rekening getRekeningByRekeningNaam(final String rekeningNaam) {
		RekeningEntity rekeningEntity = DataBroker.getRekeningByRekeningNaam(rekeningNaam);
		logger.debug(
				"getRekeningByRekeningNaam: rekeningNaam=" + rekeningNaam + ", result=" + rekeningEntity.toString());
		return convert(rekeningEntity);
	}

	private Rekening convert(final RekeningEntity rekeningEntity) {
		Rekening rekening = new Rekening();
		rekening.setRekeningId(rekeningEntity.getId());
		rekening.setRekeningBankNaam(rekeningEntity.getRekeningBankNaam());
		rekening.setRekeningBeginSaldo(rekeningEntity.getRekeningBeginSaldo());
		rekening.setRekeningIBAN(rekeningEntity.getRekeningIBAN());
		rekening.setRekeningNaam(rekeningEntity.getRekeningNaam());
		rekening.setRekeningPassiva(rekeningEntity.getRekeningPassiva());
		rekening.setRekeningSoort(rekeningEntity.getRekeningSoort());
		return rekening;
	}

	private RekeningEntity convert(final Rekening rekening) {
		RekeningEntity rekeningEntity = new RekeningEntity();
		if (rekening.getRekeningId() != null) {
			rekeningEntity.setId(rekening.getRekeningId());
		}
		rekeningEntity.setRekeningBankNaam(rekening.getRekeningBankNaam());
		rekeningEntity.setRekeningBeginSaldo(rekening.getRekeningBeginSaldo());
		rekeningEntity.setRekeningIBAN(rekening.getRekeningIBAN());
		rekeningEntity.setRekeningNaam(rekening.getRekeningNaam());
		rekeningEntity.setRekeningPassiva(rekening.getRekeningPassiva());
		rekeningEntity.setRekeningSoort(rekening.getRekeningSoort());
		return rekeningEntity;
	}
}
