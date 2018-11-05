package sv.com.nipro.interfaz.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import sv.com.nipro.interfaz.entities.User;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositoryCustom{
	private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public User findByTypeAndUserAndPwd(String type, String user, String password) {
		System.out.println("Parametros:: "+type +" - "+user +" - "+password);
		Query query = null;
		User obj = null;
		
		try {
			query = entityManager.createNamedQuery("User.findByTypeAndUserAndPwd", User.class);
			query.setParameter("type", type);
			query.setParameter("username", user);
			query.setParameter("password", password);
			
			obj = (User) query.getSingleResult();
			
		} catch (NoResultException nre) {
			logger.error(nre);
		}catch (Exception e) {
			logger.error(e, e);
		}		
		
		return obj;
	}
}
