package sv.com.nipro.interfaz.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import sv.com.nipro.interfaz.repository.TokenRepository;

@ManagedBean(name = "BaseBean")
public class BaseBean {
	@Autowired
	public TokenRepository tokenService;

	public BaseBean() {	}
	
	public boolean isTokenActive(String token){
		
		return false;
	}
}
