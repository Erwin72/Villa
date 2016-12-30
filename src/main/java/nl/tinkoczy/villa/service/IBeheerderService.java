package nl.tinkoczy.villa.service;

import java.util.List;

import nl.tinkoczy.villa.model.Beheerder;

public interface IBeheerderService {

	void saveOrUpdateBeheerder(Beheerder beheerder);

	void saveOrUpdateBeheerderWithAppartement(Beheerder beheerder, Long appartementId);

	void deleteBeheerder(Beheerder beheerder);

	List<Beheerder> getAllBeheerders();

	Beheerder getBeheerderById(long id);

	List<Beheerder> getBeheerdersByAppartement(Long appartementId);
}
