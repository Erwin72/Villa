package nl.tinkoczy.villa.service;

import java.math.BigDecimal;
import java.util.List;

import nl.tinkoczy.villa.model.BijdrageRente;

public interface IBijdrageRenteService {

	void saveOrUpdateBijdrageRente(BijdrageRente bijdrageRente);

	long saveBijdrageRente(BijdrageRente bijdrageRente);

	void deleteBijdrageRente(BijdrageRente bijdrageRente);

	List<BijdrageRente> getAllBijdrageRentes();

	BijdrageRente getBijdrageRenteById(long id);

	BijdrageRente getBijdrageRenteByRentePercentageAndNaVervaldatum(BigDecimal bijdrageRentePercentage,
			int bijdrageRenteNaVervaldatum);
}
