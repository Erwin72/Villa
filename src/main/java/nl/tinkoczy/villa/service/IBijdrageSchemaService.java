package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.BijdrageSchema;

public interface IBijdrageSchemaService {

	void saveOrUpdateBijdrageSchema(BijdrageSchema bijdrageSchema);

	void saveOrUpdateBijdrageSchemaWithFrequentieAndRente(BijdrageSchema bijdrageSchema, int bijdrageFrequentieCode,
			long bijdrageRenteFk);

	void deleteBijdrageSchema(BijdrageSchema bijdrageSchema);

	List<BijdrageSchema> getAllBijdrageSchemas();

	BijdrageSchema getBijdrageSchemaById(long id);

	BijdrageSchema getBijdrageSchemaByBijdrageSchemaNaam(String bijdrageSchemaNaam);
}
