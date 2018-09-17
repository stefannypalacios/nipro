package sv.com.nipro.interfaz.repository;

import sv.com.nipro.interfaz.entities.User;

public interface UserRepositoryCustom {
	
	User findByTypeAndUserAndPwd(String type, String user, String password);

}
