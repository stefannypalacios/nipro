package sv.com.nipro.interfaz.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import sv.com.nipro.interfaz.utils.ConnectionAPI;
import sv.com.nipro.interfaz.utils.Constans;
import sv.com.nipro.interfaz.utils.TokenGenerator;

@RestController
@CrossOrigin("*")
public class InterfazController extends BaseBean {
	
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
			if (isTokenActive(interfaz.getToken(), 2)) { // si token es valido, mensaje y md5 mensaje
				
				if (matchingMessage(interfaz.getChecksum(), interfaz.getMessage())) {
					//mensaje válido
					response.setMessage("OK");
					response.setStatus(true);
					
					String[] hl7 = interfaz.getMessage().split(System.getProperty("line.separator"));
					String msh = hl7[0];
					String orc = hl7[3];
					
					//datos necesarios
					String idSolicitud = orc.split("|")[2];
					String suministranteAll = msh.split("|")[5];
					String suministrante = suministranteAll.split("^")[1];
					String idSuministrante = suministranteAll.split("^")[0];
					
					//Generar HL7
					String hl7toSend = "";
					
					//Envío HL7
					String messege = new ConnectionAPI().soapMessage("");
					new ConnectionAPI().soapURLConnection(messege, "");
					
					
					//Creando archivo
					File fileHl7 = new File("/solicitud_" + idSolicitud + ".txt");
					BufferedWriter bw;
					if(!fileHl7.exists()) {
						bw = new BufferedWriter(new FileWriter(fileHl7));
						bw.write(interfaz.getMessage());
					}
					
				}else {
					// cadena no válida
					response.setMessage("Cadena no válida"); // concatenar cadena
					response.setStatus(false);
				}				
				
			} else {
				//Token no válido
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
