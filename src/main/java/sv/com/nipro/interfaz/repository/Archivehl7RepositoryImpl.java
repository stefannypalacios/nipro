package sv.com.nipro.interfaz.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import sv.com.nipro.interfaz.entities.Archive;
import sv.com.nipro.interfaz.entities.Archivehl7;

public class Archivehl7RepositoryImpl implements Archivehl7RepositoryCustom {
	private static final Logger logger = Logger.getLogger(Archivehl7RepositoryImpl.class);
	
	@PersistenceContext
    EntityManager em;
	
	@Override
	public Archivehl7 findByResultid(String resultid) {
		Query query = null;
		Archivehl7 item = new Archivehl7();
		try {
			query = em.createNamedQuery("Archivehl7.findByResultid", Archive.class);
			query.setParameter("resultid", resultid);
			item = (Archivehl7) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e, e);
		}
		return item;
	}

	@Override
	public List<Archivehl7> findByStatus(String status) {
		Query query = null;
		List<Archivehl7> lstItems = new ArrayList<Archivehl7>();

		try {
			query = em.createNamedQuery("Archivehl7.findByStatus", Archive.class);
			query.setParameter("status", status);
			lstItems = (List<Archivehl7>) query.getResultList();
			
		}catch (NoResultException nre) {
			logger.error(nre);	
		} catch (Exception e) {
			logger.error(e, e);
		}
		
		return lstItems;
	}

}
