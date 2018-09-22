package sv.com.nipro.interfaz.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sv.com.nipro.interfaz.entities.Token;

@Repository
@Transactional(readOnly = true)
public class TokenRepositoryImpl implements TokenRepositoryCustom{
private static final Logger logger = Logger.getLogger(TokenRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public Token findByToken(String token) {
		Query query = null;
		Token obj = null;
		
		try {
			query = entityManager.createNamedQuery("Token.findByToken", Token.class);
			query.setParameter("token", token);
			
			obj = (Token) query.getSingleResult();
			
		} catch (NoResultException nre) {
			logger.error(nre);
		}catch (Exception e) {
			logger.error(e, e);
		}		
		// Token.findByToken
		return obj;
	}

}
