package nl.tinkoczy.villa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tinkoczy.villa.config.ApplicationConfiguration;
import nl.tinkoczy.villa.config.ConfigFacade;
import nl.tinkoczy.villa.domain.PostEntity;
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
}
