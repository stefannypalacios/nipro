package sv.com.nipro.interfaz.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sv.com.nipro.interfaz.entities.Token;
import sv.com.nipro.interfaz.entities.User;
import sv.com.nipro.interfaz.repository.TokenRepository;
import sv.com.nipro.interfaz.repository.UserRepository;
import sv.com.nipro.interfaz.utils.Constans;
import sv.com.nipro.interfaz.utils.MessageUtil;
import sv.com.nipro.interfaz.utils.PasswordUtils;
import sv.com.nipro.interfaz.utils.TokenGenerator;

@Scope("singleton")
@ManagedBean(name = "userController")
@Controller
public class UserController extends BaseBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	private List<User> users;
	private String user;
	private String password;
	
	@Autowired
	private UserRepository userService;
	
	@Autowired
	private TokenRepository tokenService;
	
	@Autowired
	private SessionController beanSession;

	@PostConstruct
	public void init() {
		//getUserLst();
	}
	
	public void login() {
		try {			
			User u = userService.findByTypeAndUserAndPwd(Constans.USER_TYPE_WEB, user,
					PasswordUtils.generateSecurePassword(password, Constans.PDW_SALT));
						
			if (u != null){
				logger.info(u.toString());
				Token tk = new Token();
				tk.setUserid(u);
				tk.setToken(TokenGenerator.generateToken(u.getUsername()));
				tk.setLastUsage(new Timestamp(System.currentTimeMillis()));
				tk.setStatus(true);
				tk.setCreatedAt(new Timestamp(System.currentTimeMillis()));
				
				tokenService.save(tk);
				
				beanSession.setToken(tk);
				beanSession.setUserInSession(u);
				
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				logger.info(context.getRequestContextPath());
				FacesContext.getCurrentInstance().getExternalContext().redirect("main");
				
			}else{
				MessageUtil.addErrorMessage("Usuario o contraseña incorrectos");
			}			
			
		} catch (Exception e) {
			logger.error(e, e);
		}
	}
	
	public void loguot() {
		beanSession.setToken(null);
		beanSession.setUserInSession(null);
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/");
		} catch (IOException e) {
			logger.error(e, e);
			e.printStackTrace();
		}
	}
	
	public void prueba(){
		isTokenActive("prueba:MTEwNDIxMDAwMjQzNDIxMzEwNDI0MTIxMjEyMw==", 30);
		logger.info("************************");
		logger.info(matchingMessage("d077f244def8a70e5ea758bd8352fcd8", "cat"));
	}

	public void getUserLst() {
		users = userService.findAll();
		System.out.println("user.. "+users);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}