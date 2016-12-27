package nl.tinkoczy.villa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.domain.FaktuurEntity;
import nl.tinkoczy.villa.domain.PostEntity;
import nl.tinkoczy.villa.domain.RelatieEntity;
import nl.tinkoczy.villa.model.Faktuur;
import nl.tinkoczy.villa.service.IFaktuurService;

public class FaktuurService implements IFaktuurService {

	final static Logger logger = LoggerFactory.getLogger(FaktuurService.class);

	public FaktuurService() {
	}

	@Override
	public void saveOrUpdateFaktuur(final Faktuur faktuur) {
		FaktuurEntity faktuurEntity = convert(faktuur);
		logger.debug("saveOrUpdateFaktuur: " + faktuurEntity.toString());
		DataBroker.saveOrUpdate(faktuurEntity);
	}

	@Override
	public void saveOrUpdateFaktuurWithRelatieAndPost(final Faktuur faktuur, final Long relatieId, final Long postId) {
		RelatieEntity relatieEntity = new RelatieEntity(relatieId);
		PostEntity postEntity = new PostEntity(postId);
		FaktuurEntity faktuurEntity = convert(faktuur);
		faktuurEntity.setRelatie(relatieEntity);
		faktuurEntity.setPost(postEntity);
		logger.debug("saveOrUpdateFaktuurWithRelatieAndPost: " + relatieEntity.toString());
		DataBroker.saveOrUpdate(relatieEntity);
	}

	@Override
	public void deleteFaktuur(final Faktuur faktuur) {
		FaktuurEntity faktuurEntity = convert(faktuur);
		logger.debug("deleteFaktuur: " + faktuurEntity.toString());
		DataBroker.delete(faktuurEntity);
	}

	@Override
	public List<Faktuur> getAllFakturen() {
		List<Faktuur> results = new ArrayList<>();
		for (FaktuurEntity faktuurEntity : DataBroker.getAllFakturen()) {
			logger.debug("getAllFakturen: " + faktuurEntity.toString());
			results.add(convert(faktuurEntity));
		}
		return results;
	}

	@Override
	public Faktuur getFaktuurById(final int id) {
		FaktuurEntity faktuurEntity = DataBroker.getFaktuurById(id);
		logger.debug("getFaktuurById: id=" + id + ", result=" + faktuurEntity.toString());
		return convert(faktuurEntity);
	}

	@Override
	public Faktuur getFaktuurByFaktuurNummer(final String faktuurNummer) {
		FaktuurEntity faktuurEntity = DataBroker.getFaktuurByFaktuurNummer(faktuurNummer);
		logger.debug(
				"getFaktuurByFaktuurNummer: faktuurNummer=" + faktuurNummer + ", result=" + faktuurEntity.toString());
		return convert(faktuurEntity);
	}

	@Override
	public List<Faktuur> getAllFakturenByRelatieCode(final String relatieCode) {
		List<Faktuur> results = new ArrayList<>();
		for (FaktuurEntity faktuurEntity : DataBroker.getAllFakturenByRelatieCode(relatieCode)) {
			logger.debug(
					"getAllFakturenByRelatieCode: relatieCode=" + relatieCode + ", result=" + faktuurEntity.toString());
			results.add(convert(faktuurEntity));
		}
		return results;
	}

	private Faktuur convert(final FaktuurEntity faktuurEntity) {
		Faktuur faktuur = new Faktuur();
		faktuur.setFaktuurId(faktuurEntity.getId());
		faktuur.setFaktuurNummer(faktuurEntity.getFaktuurNummer());
		faktuur.setFaktuurBedrag(faktuurEntity.getFaktuurBedrag());
		faktuur.setFaktuurDatum(faktuurEntity.getFaktuurDatum());
		faktuur.setFaktuurOmschrijving(faktuurEntity.getFaktuurOmschrijving());
		faktuur.setFaktuurSaldo(faktuurEntity.getFaktuurSaldo());
		faktuur.setFaktuurVerwerkt(faktuurEntity.getFaktuurVerwerkt());
		faktuur.setFaktuurVorigePeriode(faktuurEntity.getFaktuurVorigePeriode());
		if (faktuurEntity.getRelatie() != null) {
			faktuur.setRelatieCode(faktuurEntity.getRelatie().getRelatieCode());
		}
		if (faktuurEntity.getPost() != null) {
			faktuur.setPostNummer(faktuurEntity.getPost().getPostNummer());
		}
		return faktuur;
	}

	private FaktuurEntity convert(final Faktuur faktuur) {
		FaktuurEntity faktuurEntity = new FaktuurEntity();
		if (faktuur.getFaktuurId() != null) {
			faktuurEntity.setId(faktuur.getFaktuurId());
		}
		faktuurEntity.setFaktuurNummer(faktuur.getFaktuurNummer());
		faktuurEntity.setFaktuurBedrag(faktuur.getFaktuurBedrag());
		faktuurEntity.setFaktuurDatum(faktuur.getFaktuurDatum());
		faktuurEntity.setFaktuurOmschrijving(faktuur.getFaktuurOmschrijving());
		faktuurEntity.setFaktuurSaldo(faktuur.getFaktuurSaldo());
		faktuurEntity.setFaktuurVerwerkt(faktuur.isFaktuurVerwerkt());
		faktuurEntity.setFaktuurVorigePeriode(faktuur.isFaktuurVorigePeriode());
		return faktuurEntity;
	}

}
