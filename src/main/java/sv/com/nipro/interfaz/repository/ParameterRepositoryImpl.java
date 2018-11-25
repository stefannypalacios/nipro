package sv.com.nipro.interfaz.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sv.com.nipro.interfaz.entities.Archive;
import sv.com.nipro.interfaz.entities.Parameter;

@Repository
@Transactional(readOnly = true)
public class ParameterRepositoryImpl implements ParameterRepositoryCustom {

	private static final Logger logger = Logger.getLogger(ParameterRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager em;
	
	@Override
	public Parameter findByCode(String code) {
		Query query = null;
		Parameter item = new Parameter();

		try {
			query = em.createNamedQuery("Parameter.findByCode", Parameter.class);
			query.setParameter("code", code);
			item = (Parameter) query.getSingleResult();
			
		}catch (NoResultException nre) {
			logger.error(nre);	
		} catch (Exception e) {
			logger.error(e, e);
		}
		
		return item;
	}

}
