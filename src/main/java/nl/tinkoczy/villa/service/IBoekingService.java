package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Boeking;

public interface IBoekingService {

	void saveOrUpdateBoeking(Boeking boeking);

	void saveOrUpdateBoekingWithBoekStuk(Boeking boeking, Long boekstukId);

	void deleteBoeking(Boeking boeking);

	List<Boeking> getAllBoekingen();

	List<Boeking> getAllBoekingenSortByPostNummer();

	Boeking getBoekingById(final int id);

	List<Boeking> getAllBoekingenByBoekstukVolgnummer(final int boekstukVolgnummer);

	List<Boeking> getAllBoekingenByPostNummer(final int postNummer);
}
