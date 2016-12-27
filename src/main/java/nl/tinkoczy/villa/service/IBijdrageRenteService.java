package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.BijdrageRente;

public interface IBijdrageRenteService {

	void saveOrUpdateBijdrageRente(BijdrageRente bijdrageRente);

	void deleteBijdrageRente(BijdrageRente bijdrageRente);

	List<BijdrageRente> getAllBijdrageRentes();

	BijdrageRente getBijdrageRenteById(long id);

}
