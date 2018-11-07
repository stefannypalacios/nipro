package sv.com.nipro.interfaz.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sv.com.nipro.interfaz.entities.Archive;

@Repository
@Transactional(readOnly = true)
public class ArchiveRepositoryImpl implements ArchiveRepositoryCustom {
	private static final Logger logger = Logger.getLogger(ArchiveRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager em;
	

	@Override
	public List<Archive> findByStatus(String status) {
		Query query = null;
		List<Archive> lstItems = new ArrayList<Archive>();

		try {
			query = em.createNamedQuery("Archive.findByStatus", Archive.class);
			query.setParameter("status", status);
			lstItems = (List<Archive>) query.getResultList();
			
		}catch (NoResultException nre) {
			logger.error(nre);	
		} catch (Exception e) {
			logger.error(e, e);
		}
		
		return lstItems;
	}

	@Override
	public List<Archive> findByType(String type) {
		Query query = null;
		List<Archive> lstItems = new ArrayList<Archive>();

		try {
			query = em.createNamedQuery("Archive.findByType", Archive.class);
			query.setParameter("type", type);
			lstItems = (List<Archive>) query.getResultList();
			
		}catch (NoResultException nre) {
			logger.error(nre);	
		} catch (Exception e) {
			logger.error(e, e);
		}
		
		return lstItems;
	}

}
