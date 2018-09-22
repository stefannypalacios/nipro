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

import sv.com.nipro.interfaz.entities.Transaction;

@Repository
@Transactional(readOnly = true)
public class TransactionRepositoryImpl implements TransactionRepositoryCustom {
	private static final Logger logger = Logger.getLogger(TransactionRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Transaction> findByTypeAndMethod(String type, String method) {
		Query query = null;
		List<Transaction> lstObj = new ArrayList<Transaction>();
		
		try {
			query = entityManager.createNamedQuery("Transaction.findByTypeAndMethod", Transaction.class);
			query.setParameter("type", type);
			query.setParameter("method", method);
			
			lstObj = (List<Transaction>) query.getResultList();
			
		} catch (NoResultException nre) {
			logger.error(nre);
		}catch (Exception e) {
			logger.error(e, e);
		}		
		
		return lstObj;
	}

	@Override
	public List<Transaction> findByMethod(String method) {
		Query query = null;
		List<Transaction> lstObj = new ArrayList<Transaction>();
		
		try {
			query = entityManager.createNamedQuery("Transaction.findByMethod", Transaction.class);
			query.setParameter("method", method);
			
			lstObj = (List<Transaction>) query.getResultList();
			
		} catch (NoResultException nre) {
			logger.error(nre);
		}catch (Exception e) {
			logger.error(e, e);
		}		
		
		return lstObj;
	}

	@Override
	public List<Transaction> findByType(String type) {
		Query query = null;
		List<Transaction> lstObj = new ArrayList<Transaction>();
		
		try {
			query = entityManager.createNamedQuery("Transaction.findByType", Transaction.class);
			query.setParameter("type", type);
			
			lstObj = (List<Transaction>) query.getResultList();
			
		} catch (NoResultException nre) {
			logger.error(nre);
		}catch (Exception e) {
			logger.error(e, e);
		}		
		
		return lstObj;
	}

}
