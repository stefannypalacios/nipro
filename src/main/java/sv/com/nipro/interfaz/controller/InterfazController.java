package sv.com.nipro.interfaz.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sv.com.nipro.interfaz.dto.RequestAcceptMessage;
import sv.com.nipro.interfaz.dto.RequestCheckin;
import sv.com.nipro.interfaz.dto.RequestCheckout;
import sv.com.nipro.interfaz.dto.Response;
import sv.com.nipro.interfaz.dto.ResponseCheckin;
import sv.com.nipro.interfaz.entities.User;
import sv.com.nipro.interfaz.repository.UserRepository;
import sv.com.nipro.interfaz.repository.UserRepositoryCustom;
import sv.com.nipro.interfaz.repository.UserRepositoryImpl;
import sv.com.nipro.interfaz.utils.Constans;
import sv.com.nipro.interfaz.utils.TokenGenerator;

@RestController
@CrossOrigin("*")
public class InterfazController {
	
	@Autowired
	private UserRepository repository;

	@RequestMapping(value = "/checkin", method = RequestMethod.POST)
	public ResponseEntity checkin(@RequestBody RequestCheckin interfaz) {
		System.out.println("checkin - RequestBody " + interfaz);

		ResponseCheckin response = new ResponseCheckin();
		try {
			User user = getUser(interfaz.getAppUser(), interfaz.getPassword());
			if (user != null && user.getUserid() != null && user.getUserid() != 0) {
				response.setMessage("");
				response.setStatus(true);
				response.setToken(TokenGenerator.generateToken(interfaz.getAppUser()));
			} else {
				response.setMessage("Credenciales no validas");
				response.setStatus(false);
				response.setToken(null);
			}
		} catch (Exception ex) {
			Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("checkin().response " + response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/acceptMessage", method = RequestMethod.POST)
	public ResponseEntity acceptMessage(@RequestBody RequestAcceptMessage interfaz) {
		System.out.println("checkin - RequestBody " + interfaz);

		Response response = new Response();
		try {
			if (true) { // si token es valido, mensaje y md5 mensaje
				response.setMessage("OK");
				response.setStatus(true);
			} else if (true) { // cadena no valido
				response.setMessage("Cadena no válida"); // concatenar cadena
				response.setStatus(false);
			} else if (true) { // token no valido
				response.setMessage("Permiso denegado");
				response.setStatus(false);
			} else if (true) { // Checksum no valido
				response.setMessage("Cadena no válida");
				response.setStatus(false);
			}
		} catch (Exception ex) {
			Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("checkin().response " + response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ResponseEntity checkout(@RequestBody RequestCheckout interfaz) {
		System.out.println("checkin - RequestBody " + interfaz);

		Response response = new Response();
		try {
			if (true) { // si token es valido
				response.setMessage("Sesión de usuario finalizada con exito.");
				response.setStatus(true);
			} else {
				response.setMessage("Authentication Failed");
				response.setStatus(false);
			}
		} catch (Exception ex) {
			Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("checkin().response " + response);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public List<User> user() {
		return repository.findAll();
	}

	public User getUser(String user, String pass) {
		User user2 = null;
		try {
			user2 = repository.findByTypeAndUserAndPwd(Constans.REST_USER, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user2;
	}

}
