package sv.com.nipro.interfaz.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.com.nipro.interfaz.entities.User;
import sv.com.nipro.interfaz.repository.UserRepository;

@ManagedBean(name = "userBean")
@ViewScoped
@Component
public class UserController {
	private List<User> users;
	@Autowired
	public UserRepository userService;

	@PostConstruct
	public void init() {
		getUser();
	}

	public void getUser() {
		users = userService.findAll();
		System.out.println("user.. "+users);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}