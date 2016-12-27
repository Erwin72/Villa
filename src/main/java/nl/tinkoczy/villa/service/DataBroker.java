package nl.tinkoczy.villa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.config.ApplicationConfiguration;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.domain.BoekingEntity;
import nl.tinkoczy.villa.domain.BoekstukEntity;
import nl.tinkoczy.villa.domain.PostEntity;
import nl.tinkoczy.villa.domain.RekeningEntity;
import nl.tinkoczy.villa.domain.RelatieEntity;
import nl.tinkoczy.villa.domain.RelatiePersoonEntity;
import nl.tinkoczy.villa.domain.RubriekEntity;

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

	/*
	 * Rubriek
	 */
	public static List<RubriekEntity> getAllRubrieken() {
		return em.createNamedQuery("RubriekEntity.findAll", RubriekEntity.class).getResultList();
	}

	public static RubriekEntity getRubriekById(final int id) {
		return em.createNamedQuery("RubriekEntity.findById", RubriekEntity.class).setParameter("id", id)
				.getSingleResult();
	}

	public static RubriekEntity getRubriekByRubriekNummer(final Integer rubriekNummer) {
		return em.createNamedQuery("RubriekEntity.findByRubriekNummer", RubriekEntity.class)
				.setParameter("rubriekNummer", rubriekNummer).getSingleResult();
	}

	/*
	 * Post
	 */
	public static List<PostEntity> getAllPosten() {
		return em.createNamedQuery("PostEntity.findAll", PostEntity.class).getResultList();
	}

	public static PostEntity getPostById(final int id) {
		return em.createNamedQuery("PostEntity.findById", PostEntity.class).setParameter("id", id).getSingleResult();
	}

	public static PostEntity getPostByPostNummer(final Integer postNummer) {
		return em.createNamedQuery("PostEntity.findByPostNummer", PostEntity.class)
				.setParameter("postNummer", postNummer).getSingleResult();
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

	public static RelatieEntity getRelatieById(final int id) {
		return em.createNamedQuery("RelatieEntity.findById", RelatieEntity.class).setParameter("id", id)
				.getSingleResult();
	}

	public static RelatieEntity getRelatieByRelatieCode(final String relatieCode) {
		return em.createNamedQuery("RelatieEntity.findByRelatieCode", RelatieEntity.class)
				.setParameter("relatieCode", relatieCode).getSingleResult();
	}

	/*
	 * RelatiePersoon
	 */
	public static List<RelatiePersoonEntity> getAllRelatiePersonen() {
		return em.createNamedQuery("RelatiePersoonEntity.findAll", RelatiePersoonEntity.class).getResultList();
	}

	public static RelatiePersoonEntity getRelatiePersoonById(final int id) {
		return em.createNamedQuery("RelatiePersoonEntity.findById", RelatiePersoonEntity.class).setParameter("id", id)
				.getSingleResult();
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

	public static RekeningEntity getRekeningById(final int id) {
		return em.createNamedQuery("RekeningEntity.findById", RekeningEntity.class).setParameter("id", id)
				.getSingleResult();
	}

	public static RekeningEntity getRekeningByRekeningNaam(final String rekeningNaam) {
		return em.createNamedQuery("RekeningEntity.findByRekeningNaam", RekeningEntity.class)
				.setParameter("rekeningNaam", rekeningNaam).getSingleResult();
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

	public static BoekstukEntity getBoekstukById(final int id) {
		return em.createNamedQuery("BoekstukEntity.findById", BoekstukEntity.class).setParameter("id", id)
				.getSingleResult();
	}

	public static BoekstukEntity getBoekstukByVolgnummer(final int boekstukVolgnummer) {
		return em.createNamedQuery("BoekstukEntity.findByVolgnummer", BoekstukEntity.class)
				.setParameter("boekstukVolgnummer", boekstukVolgnummer).getSingleResult();
	}

	public static BoekstukEntity getBoekstukByAfschriftnummer(final int boekstukAfschriftnummer) {
		return em.createNamedQuery("BoekstukEntity.findByAfschriftnummer", BoekstukEntity.class)
				.setParameter("boekstukAfschriftnummer", boekstukAfschriftnummer).getSingleResult();
	}

	public static List<BoekstukEntity> getAllRelatiePersonenByRekeningId(final int rekeningId) {
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

	public static BoekingEntity getBoekingById(final int id) {
		return em.createNamedQuery("BoekingEntity.findById", BoekingEntity.class).setParameter("id", id)
				.getSingleResult();
	}

	public static List<BoekingEntity> getAllBoekingenByBoekstukVolgnummer(final int boekstukVolgnummer) {
		BoekstukEntity boekstukEntity = getBoekstukByVolgnummer(boekstukVolgnummer);
		return em.createNamedQuery("BoekingEntity.findAllByBoekstuk", BoekingEntity.class)
				.setParameter("boekstuk", boekstukEntity).getResultList();
	}

}
