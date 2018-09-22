package sv.com.nipro.interfaz.controller;

import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sv.com.nipro.interfaz.entities.Token;
import sv.com.nipro.interfaz.entities.User;

@Scope("singleton")
@ManagedBean(name = "SessionController")
@Controller
public class SessionController {
	
	private User userInSession;
	private Token token;
	
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	
	public User getUserInSession() {
		return userInSession;
	}
	
	public void setUserInSession(User userInSession) {
		this.userInSession = userInSession;
	}

}
