package sv.com.nipro.interfaz.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sv.com.nipro.interfaz.entities.Token;
import sv.com.nipro.interfaz.entities.User;
import sv.com.nipro.interfaz.repository.TokenRepository;
import sv.com.nipro.interfaz.repository.UserRepository;
import sv.com.nipro.interfaz.utils.Constans;
import sv.com.nipro.interfaz.utils.MessageUtil;
import sv.com.nipro.interfaz.utils.PasswordUtils;
import sv.com.nipro.interfaz.utils.TokenGenerator;

@ViewScoped
@ManagedBean(name = "userController")
@Controller
public class UserController implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(UserController.class);
	
	private List<User> users;
	private String user;
	private String password;
	
	@Autowired
	public UserRepository userService;
	
	@Autowired
	public TokenRepository tokenService;

	@PostConstruct
	public void init() {
		getUserLst();
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
				
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				logger.info(context.getRequestContextPath());
				FacesContext.getCurrentInstance().getExternalContext().redirect("prueba");
				//context.redirect(context.getRequestContextPath() + "download-page.jsf?product=" + product + "&version=" + version);
				
			}else{
				MessageUtil.addErrorMessage("Usuario o contraseña incorrectos");
			}			
			
		} catch (Exception e) {
			logger.error(e, e);
		}
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