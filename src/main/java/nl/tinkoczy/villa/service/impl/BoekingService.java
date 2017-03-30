package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.AppartementEntity;
import nl.tinkoczy.villa.domain.BoekingEntity;
import nl.tinkoczy.villa.domain.BoekstukEntity;
import nl.tinkoczy.villa.model.Boeking;
import nl.tinkoczy.villa.service.IBoekingService;

public class BoekingService implements IBoekingService {

	final static Logger logger = LoggerFactory.getLogger(BoekingService.class);

	public BoekingService() {
	}

	@Override
	public void saveOrUpdateBoeking(final Boeking boeking) {
		BoekingEntity boekingEntity = convert(boeking);
		logger.debug("saveOrUpdateBoeking: " + boekingEntity.toString());
		DataBroker.saveOrUpdate(boekingEntity);
	}

	@Override
	public void saveOrUpdateBoekingWithBoekStuk(final Boeking boeking, final Long boekstukId) {
		BoekstukEntity boekstukEntity = new BoekstukEntity(boekstukId);
		BoekingEntity boekingEntity = convert(boeking);
		boekingEntity.setBoekstuk(boekstukEntity);
		logger.debug("saveOrUpdateBoekingWithBoekStuk: " + boekingEntity.toString());
		DataBroker.saveOrUpdate(boekingEntity);
	}

	@Override
	public void saveOrUpdateBoekingWithAppartement(final Boeking boeking, final Long appartementId) {
		AppartementEntity appartementEntity = new AppartementEntity(appartementId);
		BoekingEntity boekingEntity = convert(boeking);
		boekingEntity.setAppartement(appartementEntity);
		logger.debug("saveOrUpdateBoekingWithAppartement: " + boekingEntity.toString());
		DataBroker.saveOrUpdate(boekingEntity);
	}

	@Override
	public void deleteBoeking(final Boeking boeking) {
		BoekingEntity boekingEntity = convert(boeking);
		logger.debug("deleteBoeking: " + boekingEntity.toString());
		DataBroker.delete(boekingEntity);
	}

	@Override
	public List<Boeking> getAllBoekingen() {
		List<Boeking> results = new ArrayList<>();
		for (BoekingEntity boekingEntity : DataBroker.getAllBoekingen()) {
			logger.debug("getAllBoekingen: " + boekingEntity.toString());
			results.add(convert(boekingEntity));
		}
		return results;
	}

	@Override
	public List<Boeking> getAllBoekingenSortByPostNummer() {
		List<Boeking> results = new ArrayList<>();
		for (BoekingEntity boekingEntity : DataBroker.getAllBoekingenSortByPostNummer()) {
			logger.debug("getAllBoekingenSortByPostNummer: " + boekingEntity.toString());
			results.add(convert(boekingEntity));
		}
		return results;
	}

	@Override
	public Boeking getBoekingById(final long id) {
		BoekingEntity boekingEntity = DataBroker.getBoekingById(id);
		logger.debug("getBoekingById: id=" + id + ", result=" + boekingEntity.toString());
		return convert(boekingEntity);
	}

	@Override
	public List<Boeking> getAllBoekingenByBoekstukVolgnummer(final int boekstukVolgnummer) {
		List<Boeking> results = new ArrayList<>();
		for (BoekingEntity boekingEntity : DataBroker.getAllBoekingenByBoekstukVolgnummer(boekstukVolgnummer)) {
			logger.debug("getAllBoekingenByBoekstukVolgnummer: boekstukVolgnummer=" + boekstukVolgnummer + ", result="
					+ boekingEntity.toString());
			results.add(convert(boekingEntity));
		}
		return results;
	}

	@Override
	public List<Boeking> getAllBoekingenByPostNummer(final int postNummer) {
		List<Boeking> results = new ArrayList<>();
		for (BoekingEntity boekingEntity : DataBroker.getAllBoekingenByPostNummer(postNummer)) {
			logger.debug(
					"getAllBoekingenByPostNummer: postNummer=" + postNummer + ", result=" + boekingEntity.toString());
			results.add(convert(boekingEntity));
		}
		return results;
	}

	@Override
	public List<Boeking> getAllBoekingenByAppartementCode(final String appartementCode) {
		List<Boeking> results = new ArrayList<>();
		for (BoekingEntity boekingEntity : DataBroker.getAllBoekingenByAppartementCode(appartementCode)) {
			logger.debug("getAllBoekingenByAppartementCode: appartementCode=" + appartementCode + ", result="
					+ boekingEntity.toString());
			results.add(convert(boekingEntity));
		}
		return results;
	}

	@Override
	public List<Boeking> getAllBoekingenBijdragenByAppartementId(final long appartementId) {
		List<Boeking> results = new ArrayList<>();
		for (BoekingEntity boekingEntity : DataBroker.getAllBoekingenBijdragenByAppartementId(appartementId)) {
			logger.debug("getAllBoekingenBijdragenByAppartementId: appartementId=" + appartementId + ", result="
					+ boekingEntity.toString());
			results.add(convert(boekingEntity));
		}
		return results;
	}

	private Boeking convert(final BoekingEntity boekingEntity) {
		Boeking boeking = new Boeking();
		boeking.setBoekingId(boekingEntity.getId());
		boeking.setBoekingBedrag(boekingEntity.getBoekingBedrag());
		boeking.setBoekingDatum(boekingEntity.getBoekingDatum());
		boeking.setBoekingOmschrijving(boekingEntity.getBoekingOmschrijving());
		boeking.setBoekingVorigePeriode(boekingEntity.getBoekingVorigePeriode());
		boeking.setBoekingIsBijdrage(boekingEntity.getBoekingIsBijdrage());
		boeking.setBoekstukVolgnummer(boekingEntity.getBoekstuk().getBoekstukVolgnummer());
		boeking.setPostNummer(boekingEntity.getPost().getPostNummer());
		if (boekingEntity.getFaktuur() != null) {
			boeking.setFaktuurNummer(boekingEntity.getFaktuur().getFaktuurNummer());
		}
		if (boekingEntity.getAppartement() != null) {
			boeking.setAppartementFk(boekingEntity.getAppartement().getId());
		}
		return boeking;
	}

	private BoekingEntity convert(final Boeking boeking) {
		BoekingEntity boekingEntity = new BoekingEntity();
		if (boeking.getBoekingId() != null) {
			boekingEntity.setId(boeking.getBoekingId());
		}
		boekingEntity.setBoekingBedrag(boeking.getBoekingBedrag());
		boekingEntity.setBoekingDatum(boeking.getBoekingDatum());
		boekingEntity.setBoekingOmschrijving(boeking.getBoekingOmschrijving());
		boekingEntity.setBoekingVorigePeriode(boeking.getBoekingVorigePeriode());
		boekingEntity.setBoekingIsBijdrage(boeking.getBoekingIsBijdrage());
		return boekingEntity;
	}

}
