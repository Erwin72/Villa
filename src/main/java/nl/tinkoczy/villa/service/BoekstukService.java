package nl.tinkoczy.villa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.BoekstukEntity;
import nl.tinkoczy.villa.domain.RekeningEntity;
import nl.tinkoczy.villa.model.Boekstuk;

public class BoekstukService implements IBoekstukService {

	final static Logger logger = LoggerFactory.getLogger(BoekstukService.class);

	public BoekstukService() {
	}

	@Override
	public void saveOrUpdateBoekstuk(final Boekstuk boekstuk) {
		BoekstukEntity boekstukEntity = convert(boekstuk);
		logger.debug("saveOrUpdateBoekstuk: " + boekstukEntity.toString());
		DataBroker.saveOrUpdate(boekstukEntity);
	}

	@Override
	public void saveOrUpdateBoekstukWithRekening(final Boekstuk boekstuk, final Long rekeningId) {
		RekeningEntity rekeningEntity = new RekeningEntity(rekeningId);
		BoekstukEntity boekstukEntity = convert(boekstuk);
		boekstukEntity.setRekening(rekeningEntity);
		logger.debug("saveOrUpdateBoekstukWithRekening: " + boekstukEntity.toString());
		DataBroker.saveOrUpdate(boekstukEntity);
	}

	@Override
	public void deleteBoekstuk(final Boekstuk boekstuk) {
		BoekstukEntity boekstukEntity = convert(boekstuk);
		logger.debug("deleteBoekstuk: " + boekstukEntity.toString());
		DataBroker.delete(boekstukEntity);
	}

	@Override
	public List<Boekstuk> getAllBoekstukkenSortByDatum() {
		List<Boekstuk> results = new ArrayList<>();
		for (BoekstukEntity boekstukEntity : DataBroker.getAllBoekstukkenSortByDatum()) {
			logger.debug("getAllBoekstukkenSortByDatum: " + boekstukEntity.toString());
			results.add(convert(boekstukEntity));
		}
		return results;
	}

	@Override
	public List<Boekstuk> getAllBoekstukkenSortByVolgnummer() {
		List<Boekstuk> results = new ArrayList<>();
		for (BoekstukEntity boekstukEntity : DataBroker.getAllBoekstukkenSortByVolgnummer()) {
			logger.debug("getAllBoekstukkenSortByVolgnummer: " + boekstukEntity.toString());
			results.add(convert(boekstukEntity));
		}
		return results;
	}

	@Override
	public Boekstuk getBoekstukById(final int id) {
		BoekstukEntity boekstukEntity = DataBroker.getBoekstukById(id);
		logger.debug("getBoekstukById: id=" + id + ", result=" + boekstukEntity.toString());
		return convert(boekstukEntity);
	}

	@Override
	public Boekstuk getBoekstukByVolgnummer(final int boekstukVolgnummer) {
		BoekstukEntity boekstukEntity = DataBroker.getBoekstukByVolgnummer(boekstukVolgnummer);
		logger.debug("getBoekstukByVolgnummer: boekstukVolgnummer=" + boekstukVolgnummer + ", result="
				+ boekstukEntity.toString());
		return convert(boekstukEntity);
	}

	@Override
	public Boekstuk getBoekstukByAfschriftnummer(final int boekstukAfschriftnummer) {
		BoekstukEntity boekstukEntity = DataBroker.getBoekstukByAfschriftnummer(boekstukAfschriftnummer);
		logger.debug("getBoekstukByAfschriftnummer: boekstukAfschriftnummer=" + boekstukAfschriftnummer + ", result="
				+ boekstukEntity.toString());
		return convert(boekstukEntity);
	}

	@Override
	public List<Boekstuk> getAllRelatiePersonenByRekeningId(final int rekeningId) {
		List<Boekstuk> results = new ArrayList<>();
		for (BoekstukEntity boekstukEntity : DataBroker.getAllRelatiePersonenByRekeningId(rekeningId)) {
			logger.debug("getAllRelatiePersonenByRekeningId: rekeningId=" + rekeningId + ", result="
					+ boekstukEntity.toString());
			results.add(convert(boekstukEntity));
		}
		return results;
	}

	private Boekstuk convert(final BoekstukEntity boekstukEntity) {
		Boekstuk boekstuk = new Boekstuk();
		boekstuk.setBoekstukId(boekstukEntity.getId());
		boekstuk.setBoekstukAantekening(boekstukEntity.getBoekstukAantekening());
		boekstuk.setBoekstukAfschriftnummer(boekstukEntity.getBoekstukAfschriftnummer());
		boekstuk.setBoekstukAfschriftOpmerking(boekstukEntity.getBoekstukAfschriftOpmerking());
		boekstuk.setBoekstukDatum(boekstukEntity.getBoekstukDatum());
		boekstuk.setBoekstukVolgnummer(boekstukEntity.getBoekstukVolgnummer());
		boekstuk.setRekeningNaam(boekstukEntity.getRekening().getRekeningNaam());
		return boekstuk;
	}

	private BoekstukEntity convert(final Boekstuk boekstuk) {
		BoekstukEntity boekstukEntity = new BoekstukEntity();
		if (boekstuk.getBoekstukId() != null) {
			boekstukEntity.setId(boekstuk.getBoekstukId());
		}
		boekstukEntity.setBoekstukAantekening(boekstuk.getBoekstukAantekening());
		boekstukEntity.setBoekstukAfschriftnummer(boekstuk.getBoekstukAfschriftnummer());
		boekstukEntity.setBoekstukAfschriftOpmerking(boekstuk.getBoekstukAfschriftOpmerking());
		boekstukEntity.setBoekstukDatum(boekstuk.getBoekstukDatum());
		boekstukEntity.setBoekstukVolgnummer(boekstuk.getBoekstukVolgnummer());
		return boekstukEntity;
	}
}
