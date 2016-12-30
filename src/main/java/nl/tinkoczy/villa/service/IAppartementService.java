package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Appartement;

public interface IAppartementService {

	void saveOrUpdateAppartement(Appartement appartement);

	void saveOrUpdatePostWithBijdrageSchema(Appartement appartement, Long bijdrageSchemaId);

	void deleteAppartement(Appartement appartement);

	List<Appartement> getAllAppartementen();

	Appartement getAppartementById(long id);

	Appartement getAppartementByAppartementCode(String appartementCode);

}
