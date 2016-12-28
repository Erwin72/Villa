package nl.tinkoczy.villa.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.config.ApplicationConfiguration;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.domain.BijdrageEntity;
import nl.tinkoczy.villa.domain.BijdrageFrequentieEntity;
import nl.tinkoczy.villa.domain.BijdrageRenteEntity;
import nl.tinkoczy.villa.domain.BijdrageSchemaEntity;
import nl.tinkoczy.villa.domain.BoekingEntity;
import nl.tinkoczy.villa.domain.BoekstukEntity;
import nl.tinkoczy.villa.domain.FaktuurEntity;
import nl.tinkoczy.villa.domain.PostEntity;
import nl.tinkoczy.villa.domain.RekeningEntity;
import nl.tinkoczy.villa.domain.RelatieEntity;
import nl.tinkoczy.villa.domain.RelatiePersoonEntity;
import nl.tinkoczy.villa.domain.RubriekEntity;
import nl.tinkoczy.villa.service.IDataBroker;

public final class DataBroker implements IDataBroker {

	final static Logger logger = LoggerFactory.getLogger(DataBroker.class);

	private static EntityManager em;

	private DataBroker() {
		// prevent initialization
	}

	public static void initDataBroker() {
		String dbName = ConfigFacade.getStringValue(ApplicationConfiguration.DATABASE_NAME);
		logger.debug("Create EntityManager for databasename: " + dbName);
		em = Persistence.createEntityManagerFactory(dbName).createEntityManager();
	}

	public static void delete(final Object t) {
		em.getTransaction().begin();
		Object tToBeRemoved = em.merge(t);
		em.remove(tToBeRemoved);
		em.getTransaction().commit();
	}

	public static void saveOrUpdate(final Object t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	public static Object save(final Object t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		return t;
	}

	public static <T> T getSingleResult(final TypedQuery<T> query) {
		query.setMaxResults(1);
		List<T> list = query.getResultList();
		if (list == null || list.isEmpty()) {
			return null;
		}

		return list.get(0);
	}

	/*
	 * Rubriek
	 */
	public static List<RubriekEntity> getAllRubrieken() {
		return em.createNamedQuery("RubriekEntity.findAll", RubriekEntity.class).getResultList();
	}

	public static RubriekEntity getRubriekById(final long id) {
		return getSingleResult(
				em.createNamedQuery("RubriekEntity.findById", RubriekEntity.class).setParameter("id", id));
	}

	public static RubriekEntity getRubriekByRubriekNummer(final Integer rubriekNummer) {
		return getSingleResult(em.createNamedQuery("RubriekEntity.findByRubriekNummer", RubriekEntity.class)
				.setParameter("rubriekNummer", rubriekNummer));
	}

	/*
	 * Post
	 */
	public static List<PostEntity> getAllPosten() {
		return em.createNamedQuery("PostEntity.findAll", PostEntity.class).getResultList();
	}

	public static PostEntity getPostById(final long id) {
		return getSingleResult(em.createNamedQuery("PostEntity.findById", PostEntity.class).setParameter("id", id));
	}

	public static PostEntity getPostByPostNummer(final Integer postNummer) {
		return getSingleResult(em.createNamedQuery("PostEntity.findByPostNummer", PostEntity.class)
				.setParameter("postNummer", postNummer));
	}

	public static List<PostEntity> getPostenByRubriekNummer(final Integer rubriekNummer) {
		RubriekEntity rubriekEntity = getRubriekByRubriekNummer(rubriekNummer);
		return em.createNamedQuery("PostEntity.findAllByRubriek", PostEntity.class)
				.setParameter("rubriek", rubriekEntity).getResultList();
	}

	/*
	 * Relatie
	 */
	public static List<RelatieEntity> getAllRelaties() {
		return em.createNamedQuery("RelatieEntity.findAll", RelatieEntity.class).getResultList();
	}

	public static RelatieEntity getRelatieById(final long id) {
		return getSingleResult(
				em.createNamedQuery("RelatieEntity.findById", RelatieEntity.class).setParameter("id", id));
	}

	public static RelatieEntity getRelatieByRelatieCode(final String relatieCode) {
		return getSingleResult(em.createNamedQuery("RelatieEntity.findByRelatieCode", RelatieEntity.class)
				.setParameter("relatieCode", relatieCode));
	}

	/*
	 * RelatiePersoon
	 */
	public static List<RelatiePersoonEntity> getAllRelatiePersonen() {
		return em.createNamedQuery("RelatiePersoonEntity.findAll", RelatiePersoonEntity.class).getResultList();
	}

	public static RelatiePersoonEntity getRelatiePersoonById(final long id) {
		return getSingleResult(em.createNamedQuery("RelatiePersoonEntity.findById", RelatiePersoonEntity.class)
				.setParameter("id", id));
	}

	public static List<RelatiePersoonEntity> getAllRelatiePersonenByRelatieCode(final String relatieCode) {
		RelatieEntity relatieEntity = getRelatieByRelatieCode(relatieCode);
		return em.createNamedQuery("RelatiePersoonEntity.findAllByRelatie", RelatiePersoonEntity.class)
				.setParameter("relatie", relatieEntity).getResultList();
	}

	/*
	 * Rekening
	 */
	public static List<RekeningEntity> getAllRekeningen() {
		return em.createNamedQuery("RekeningEntity.findAll", RekeningEntity.class).getResultList();
	}

	public static RekeningEntity getRekeningById(final long id) {
		return getSingleResult(
				em.createNamedQuery("RekeningEntity.findById", RekeningEntity.class).setParameter("id", id));
	}

	public static RekeningEntity getRekeningByRekeningNaam(final String rekeningNaam) {
		return getSingleResult(em.createNamedQuery("RekeningEntity.findByRekeningNaam", RekeningEntity.class)
				.setParameter("rekeningNaam", rekeningNaam));
	}

	/*
	 * Boekstuk
	 */
	public static List<BoekstukEntity> getAllBoekstukkenSortByDatum() {
		return em.createNamedQuery("BoekstukEntity.findAllSortByDatum", BoekstukEntity.class).getResultList();
	}

	public static List<BoekstukEntity> getAllBoekstukkenSortByVolgnummer() {
		return em.createNamedQuery("BoekstukEntity.findAllSortByVolgnummer", BoekstukEntity.class).getResultList();
	}

	public static BoekstukEntity getBoekstukById(final long id) {
		return getSingleResult(
				em.createNamedQuery("BoekstukEntity.findById", BoekstukEntity.class).setParameter("id", id));
	}

	public static BoekstukEntity getBoekstukByVolgnummer(final int boekstukVolgnummer) {
		return getSingleResult(em.createNamedQuery("BoekstukEntity.findByVolgnummer", BoekstukEntity.class)
				.setParameter("boekstukVolgnummer", boekstukVolgnummer));
	}

	public static BoekstukEntity getBoekstukByAfschriftnummer(final int boekstukAfschriftnummer) {
		return getSingleResult(em.createNamedQuery("BoekstukEntity.findByAfschriftnummer", BoekstukEntity.class)
				.setParameter("boekstukAfschriftnummer", boekstukAfschriftnummer));
	}

	public static List<BoekstukEntity> getAllBoekstukkenByRekeningId(final int rekeningId) {
		RekeningEntity rekeningEntity = getRekeningById(rekeningId);
		return em.createNamedQuery("BoekstukEntity.findAllByRekening", BoekstukEntity.class)
				.setParameter("rekening", rekeningEntity).getResultList();
	}

	/*
	 * Boeking
	 */
	public static List<BoekingEntity> getAllBoekingen() {
		return em.createNamedQuery("BoekingEntity.findAllSortByDatum", BoekingEntity.class).getResultList();
	}

	public static List<BoekingEntity> getAllBoekingenSortByPostNummer() {
		return em.createNamedQuery("BoekingEntity.findAllSortByPostAndDatum", BoekingEntity.class).getResultList();
	}

	public static BoekingEntity getBoekingById(final long id) {
		return getSingleResult(
				em.createNamedQuery("BoekingEntity.findById", BoekingEntity.class).setParameter("id", id));
	}

	public static List<BoekingEntity> getAllBoekingenByBoekstukVolgnummer(final int boekstukVolgnummer) {
		BoekstukEntity boekstukEntity = getBoekstukByVolgnummer(boekstukVolgnummer);
		return em.createNamedQuery("BoekingEntity.findAllByBoekstuk", BoekingEntity.class)
				.setParameter("boekstuk", boekstukEntity).getResultList();
	}

	public static List<BoekingEntity> getAllBoekingenByPostNummer(final int postNummer) {
		PostEntity postEntity = getPostByPostNummer(postNummer);
		return em.createNamedQuery("BoekingEntity.findAllByPost", BoekingEntity.class).setParameter("post", postEntity)
				.getResultList();
	}

	/*
	 * Faktuur
	 */
	public static List<FaktuurEntity> getAllFakturen() {
		return em.createNamedQuery("BoekingEntity.findAllSortByDatum", FaktuurEntity.class).getResultList();
	}

	public static FaktuurEntity getFaktuurById(final long id) {
		return getSingleResult(
				em.createNamedQuery("FaktuurEntity.findById", FaktuurEntity.class).setParameter("id", id));
	}

	public static FaktuurEntity getFaktuurByFaktuurNummer(final String faktuurNummer) {
		return getSingleResult(em.createNamedQuery("FaktuurEntity.findByFaktuurNummer", FaktuurEntity.class)
				.setParameter("faktuurNummer", faktuurNummer));
	}

	public static List<FaktuurEntity> getAllFakturenByRelatieCode(final String relatieCode) {
		RelatieEntity relatieEntity = getRelatieByRelatieCode(relatieCode);
		return em.createNamedQuery("FaktuurEntity.findAllByRelatieSortByDatum", FaktuurEntity.class)
				.setParameter("relatie", relatieEntity).getResultList();
	}

	/*
	 * BijdrageFrequentie
	 */
	public static List<BijdrageFrequentieEntity> getAllBijdrageFrequenties() {
		return em.createNamedQuery("BijdrageFrequentieEntity.findAll", BijdrageFrequentieEntity.class).getResultList();
	}

	public static BijdrageFrequentieEntity getBijdrageFrequentieById(final long id) {
		return getSingleResult(em.createNamedQuery("BijdrageFrequentieEntity.findById", BijdrageFrequentieEntity.class)
				.setParameter("id", id));
	}

	public static BijdrageFrequentieEntity getBijdrageFrequentieByFrequentieCode(final int bijdrageFrequentieCode) {
		return getSingleResult(
				em.createNamedQuery("BijdrageFrequentieEntity.findByFrequentieCode", BijdrageFrequentieEntity.class)
						.setParameter("bijdrageFrequentieCode", bijdrageFrequentieCode));
	}

	/*
	 * BijdrageRente
	 */
	public static List<BijdrageRenteEntity> getAllBijdrageRentes() {
		return em.createNamedQuery("BijdrageRenteEntity.findAll", BijdrageRenteEntity.class).getResultList();
	}

	public static BijdrageRenteEntity getBijdrageRenteById(final long id) {
		return getSingleResult(
				em.createNamedQuery("BijdrageRenteEntity.findById", BijdrageRenteEntity.class).setParameter("id", id));
	}

	public static BijdrageRenteEntity getBijdrageRenteByRentePercentageAndNaVervaldatum(
			final BigDecimal bijdrageRentePercentage, final int bijdrageRenteNaVervaldatum) {
		return getSingleResult(em
				.createNamedQuery("BijdrageRenteEntity.findByRentePercentageAndNaVervaldatum",
						BijdrageRenteEntity.class)
				.setParameter("bijdrageRentePercentage", bijdrageRentePercentage)
				.setParameter("bijdrageRenteNaVervaldatum", bijdrageRenteNaVervaldatum));
	}

	/*
	 * BijdrageSchema
	 */
	public static List<BijdrageSchemaEntity> getAllBijdrageSchemas() {
		return em.createNamedQuery("BijdrageSchemaEntity.findAll", BijdrageSchemaEntity.class).getResultList();
	}

	public static BijdrageSchemaEntity getBijdrageSchemaById(final long id) {
		return getSingleResult(em.createNamedQuery("BijdrageSchemaEntity.findById", BijdrageSchemaEntity.class)
				.setParameter("id", id));
	}

	public static BijdrageSchemaEntity getBijdrageSchemaByBijdrageSchemaNaam(final String bijdrageSchemaNaam) {
		return getSingleResult(
				em.createNamedQuery("BijdrageSchemaEntity.findByBijdrageSchemaNaam", BijdrageSchemaEntity.class)
						.setParameter("bijdrageSchemaNaam", bijdrageSchemaNaam));
	}

	/*
	 * Bijdrage
	 */
	public static List<BijdrageEntity> getAllBijdragen() {
		return em.createNamedQuery("BijdrageEntity.findAll", BijdrageEntity.class).getResultList();
	}

	public static List<BijdrageEntity> getAllBijdragenSortByDatum() {
		return em.createNamedQuery("BijdrageEntity.findAllOrderByDatum", BijdrageEntity.class).getResultList();
	}

	public static BijdrageEntity getBijdrageById(final long id) {
		return getSingleResult(
				em.createNamedQuery("BijdrageEntity.findById", BijdrageEntity.class).setParameter("id", id));
	}

	public static List<BijdrageEntity> getAllBijdragenByBijdrageSchema(final String bijdrageSchemaNaam) {
		BijdrageSchemaEntity bijdrageSchemaEntity = getBijdrageSchemaByBijdrageSchemaNaam(bijdrageSchemaNaam);
		return em.createNamedQuery("BijdrageEntity.findAllByBijdrageSchema", BijdrageEntity.class)
				.setParameter("bijdrageSchema", bijdrageSchemaEntity).getResultList();
	}
}
