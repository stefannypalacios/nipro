package sv.com.nipro.interfaz.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.google.gson.Gson;

import sv.com.nipro.interfaz.entities.Archive;
import sv.com.nipro.interfaz.dto.RequestAcceptMessage;
import sv.com.nipro.interfaz.dto.RequestCheckin;
import sv.com.nipro.interfaz.dto.RequestCheckinSend;
import sv.com.nipro.interfaz.dto.RequestCheckinWS;
import sv.com.nipro.interfaz.dto.RequestCheckout;
import sv.com.nipro.interfaz.dto.RequestSend;
import sv.com.nipro.interfaz.dto.Response;
import sv.com.nipro.interfaz.dto.ResponseAcceptMessageSend;
import sv.com.nipro.interfaz.dto.ResponseCheckin;
import sv.com.nipro.interfaz.dto.ResponseCheckinSend;
import sv.com.nipro.interfaz.dto.ResponseCheckoutSend;
import sv.com.nipro.interfaz.entities.Token;
import sv.com.nipro.interfaz.entities.Transaction;
import sv.com.nipro.interfaz.entities.User;
import sv.com.nipro.interfaz.repository.TokenRepository;
import sv.com.nipro.interfaz.repository.TransactionRepository;
import sv.com.nipro.interfaz.repository.UserRepository;
import sv.com.nipro.interfaz.utils.ConnectionAPI;
import sv.com.nipro.interfaz.utils.Constans;
import sv.com.nipro.interfaz.utils.PasswordUtils;
import sv.com.nipro.interfaz.utils.TokenGenerator;

@RestController
@CrossOrigin("*")
public class InterfazController extends BaseBean {

	@Autowired
	private UserRepository repository;
	@Autowired
	private TokenRepository tokenRepository;
	@Autowired
	private TransactionRepository trRepository;

	@RequestMapping(value = "/checkin", method = RequestMethod.POST)
	public ResponseEntity checkin(@RequestBody RequestCheckin interfaz) {
		System.out.println("checkin - RequestBody " + interfaz);

		Token tk = new Token();
		Transaction tr = new Transaction();

		ResponseCheckin response = new ResponseCheckin();
		try {
			User user = getUser(interfaz.getAppUser(),
					PasswordUtils.generateSecurePassword(interfaz.getPassword(), Constans.PDW_SALT));
			if (user != null && user.getUserid() != null && user.getUserid() != 0) {
				response.setMensaje(" ");
				response.setEstado(true);
				response.setToken(TokenGenerator.generateToken(interfaz.getAppUser()));

				tk.setUserid(user);
				tk.setToken(response.getToken());
				tk.setLastUsage(new Timestamp(System.currentTimeMillis()));
				tk.setStatus(true);
				tk.setCreatedAt(new Timestamp(System.currentTimeMillis()));

				tokenRepository.save(tk);
			} else {
				response.setMensaje("Credenciales no validas");
				response.setEstado(false);
				response.setToken(null);
			}

			tr.setMethod("checkin");
			tr.setType("INPUT");
			tr.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			tr.setMessage(response.getMensaje());
			trRepository.save(tr);

		} catch (Exception ex) {
			Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("checkin().response " + response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/acceptMessage", method = RequestMethod.POST)
	public ResponseEntity acceptMessage(@RequestBody RequestAcceptMessage interfaz) {
		System.out.println("acceptMessage - RequestBody " + interfaz);

		Transaction tr = new Transaction();
		Response response = new Response();
		try {
			if (isTokenActive(interfaz.getToken(), 2)) { // si token es valido,
															// mensaje y md5
															// mensaje

				if (matchingMessage(interfaz.getChecksum(), interfaz.getMensaje())) {
					// mensaje válido
					response.setMensaje("OK");
					response.setEstado(true);

					String[] hl7 = interfaz.getMensaje().split("_z");
					
					for (String string : hl7) {
						System.out.println("****************************");
						System.out.println(string);
					}
					
					String msh = hl7[0];
					String orc = hl7[3];

					// datos necesarios
					String idSolicitud = orc.split("\\|")[2];
					String nameAll = orc.split("\\|")[12];
					// String suministranteAll = msh.split("\\|")[5];
					// String suministrante = suministranteAll.split("\\^")[1];
					// String idSuministrante =
					// suministranteAll.split("\\^")[0];

					// Generar HL7
					String hl7toSend = "";

					// Envío HL7

					// String idSolicitud = "XXXX";

					// Creando archivo
					Path currentRelativePath = Paths.get("");
					System.out.println(currentRelativePath.toAbsolutePath().toString());

					File fileHl7 = new File(currentRelativePath.toAbsolutePath().toString() + "/solicitudes/solicitud_"
							+ idSolicitud + ".txt");
					BufferedWriter bw;
					// if (!fileHl7.exists()) {
					bw = new BufferedWriter(new FileWriter(fileHl7));
					bw.write(interfaz.getMensaje());
					bw.close();

					Archive a = new Archive();
					a.setType("SOLICITUD");
					a.setCreatedAt(new Timestamp(System.currentTimeMillis()));
					a.setName(fileHl7.getName());
					a.setPatientName(nameAll.split("\\^")[2] + " " + nameAll.split("\\^")[1]);
					a.setStatus("PENDING");
					a.setTransactionid(tr);
					a.setSolicitudid(idSolicitud);

					List<Archive> lstA = new ArrayList<Archive>();
					lstA.add(a);

					tr.setArchiveList(lstA);
					// }

				} else {
					// cadena no válida
					response.setMensaje("Cadena no válida"); // concatenar
																// cadena
					response.setEstado(false);
				}

			} else {
				// Token no válido
				response.setMensaje("Permiso denegado");
				response.setEstado(false);
			}

			tr.setMethod("acceptMessage");
			tr.setType("INPUT");
			tr.setTokenid(tokenRepository.findByToken(interfaz.getToken()));
			tr.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			tr.setMessage(response.getMensaje());
			trRepository.save(tr);

		} catch (Exception ex) {
			Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("checkin().response " + response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ResponseEntity checkout(@RequestBody RequestCheckout interfaz) {
		System.out.println("checkout - RequestBody " + interfaz);
		Transaction tr = new Transaction();

		Response response = new Response();
		try {
			if (inactiveToken(interfaz.getToken())) { // si token es valido
				response.setMensaje("Sesión de usuario finalizada con exito.");
				response.setEstado(true);
			} else {
				response.setMensaje("Authentication Failed");
				response.setEstado(false);
			}

			tr.setMethod("checkout");
			tr.setType("INPUT");
			tr.setTokenid(tokenRepository.findByToken(interfaz.getToken()));
			tr.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			tr.setMessage(response.getMensaje());
			trRepository.save(tr);

		} catch (Exception ex) {
			Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("checkin().response " + response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkin_send", method = RequestMethod.POST)
	public ResponseEntity checkinSend(@RequestBody RequestCheckinSend interfaz) {
		System.out.println("checkinSend - RequestBody " + interfaz);

		Token tk = new Token();
		Transaction tr = new Transaction();

		
		ResponseCheckinSend response = new ResponseCheckinSend();
		try {

			if (interfaz.getStatus()) {
				response.setAppUser("");
				response.setPassword("");
			} else {
				return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("checkin().response " + response);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	
	
	public void connection(){
		try {
			RequestCheckinWS checkin = new RequestCheckinWS();
			Gson gson = new Gson();
			checkin.setUser("eautomatizadohematologia");
			checkin.setPassword("34ut0m4t1z4d0");		
						
			String resp = new ConnectionAPI().soapURLConnection(gson.toJson(checkin), Constans.NAME_SERVICE_CHECKIN);
			System.out.println("hhhhhhhhhhhhhhh: "+resp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new InterfazController().connection();
	}
	
	@RequestMapping(value = "/acceptMessage_send", method = RequestMethod.POST)
	public ResponseEntity acceptMessageSend(@RequestBody RequestSend interfaz) {
		System.out.println("acceptMessage - RequestBody " + interfaz);

		Transaction tr = new Transaction();
		System.out.println(interfaz.getMessage());
		ResponseAcceptMessageSend response = new ResponseAcceptMessageSend();
		try {
			
			if (interfaz.getStatus()) {
				response.setChecksum("");
				response.setMessage("");
				response.setToken("");
			} else {
				return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
		}
		System.out.println("checkin().response " + response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkout_send", method = RequestMethod.POST)
	public ResponseEntity checkoutSend(@RequestBody RequestSend interfaz) {
		System.out.println("checkout - RequestBody " + interfaz);
		Transaction tr = new Transaction();

		ResponseCheckoutSend response = new ResponseCheckoutSend();
		try {
			if (interfaz.getStatus()) {
				response.setToken("");
			} else {
				return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
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
			user2 = repository.findByTypeAndUserAndPwd(Constans.USER_TYPE_REST, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user2;
	}

}
