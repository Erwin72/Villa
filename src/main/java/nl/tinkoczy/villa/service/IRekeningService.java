package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Rekening;

public interface IRekeningService {

	void saveOrUpdateRekening(Rekening rekening);

	void deleteRekening(Rekening rekening);

	List<Rekening> getAllRekeningen();

	Rekening getRekeningById(final int id);

	Rekening getRekeningByRekeningNaam(final String rekeningNaam);
}
