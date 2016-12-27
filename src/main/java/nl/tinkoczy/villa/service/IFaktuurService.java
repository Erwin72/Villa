package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Faktuur;

public interface IFaktuurService {

	void saveOrUpdateFaktuur(Faktuur faktuur);

	void saveOrUpdateFaktuurWithRelatieAndPost(Faktuur faktuur, Long relatieId, Long postId);

	void deleteFaktuur(Faktuur faktuur);

	List<Faktuur> getAllFakturen();

	Faktuur getFaktuurById(final int id);

	Faktuur getFaktuurByFaktuurNummer(final String faktuurNummer);

	List<Faktuur> getAllFakturenByRelatieCode(final String relatieCode);
}
