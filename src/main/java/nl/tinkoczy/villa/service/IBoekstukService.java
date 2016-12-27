package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Boekstuk;

public interface IBoekstukService {

	void saveOrUpdateBoekstuk(Boekstuk boekstuk);

	void saveOrUpdateBoekstukWithRekening(Boekstuk boekstuk, Long rekeningId);

	void deleteBoekstuk(Boekstuk boekstuk);

	List<Boekstuk> getAllBoekstukkenSortByDatum();

	List<Boekstuk> getAllBoekstukkenSortByVolgnummer();

	Boekstuk getBoekstukById(final int id);

	Boekstuk getBoekstukByVolgnummer(final int boekstukVolgnummer);

	Boekstuk getBoekstukByAfschriftnummer(final int boekstukAfschriftnummer);

	List<Boekstuk> getAllBoekstukkenByRekeningId(final int rekeningId);
}
