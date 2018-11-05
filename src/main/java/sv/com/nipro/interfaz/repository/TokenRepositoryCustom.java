package sv.com.nipro.interfaz.repository;

import sv.com.nipro.interfaz.entities.Token;

public interface TokenRepositoryCustom {
	
	Token findByToken(String token);
	
	Token findByTokenActive(String token, Boolean isActive);

}
