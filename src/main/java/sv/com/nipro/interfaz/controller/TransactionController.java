package sv.com.nipro.interfaz.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import sv.com.nipro.api.Endpoints;
import sv.com.nipro.api.request.CheckoutRequest;
import sv.com.nipro.api.request.MessageRequest;
import sv.com.nipro.api.request.UsuarioRequest;
import sv.com.nipro.api.response.CheckoutResponse;
import sv.com.nipro.api.response.MessageResponse;
import sv.com.nipro.api.response.UsuarioResponse;
import sv.com.nipro.interfaz.dto.Hl7DTO;
import sv.com.nipro.interfaz.dto.ResponseAcceptMessageSend;
import sv.com.nipro.interfaz.entities.Archive;
import sv.com.nipro.interfaz.entities.Archivehl7;
import sv.com.nipro.interfaz.entities.Element;
import sv.com.nipro.interfaz.entities.Transaction;
import sv.com.nipro.interfaz.repository.ArchiveRepository;
import sv.com.nipro.interfaz.repository.Archivehl7Repository;
import sv.com.nipro.interfaz.repository.ElementRepository;
import sv.com.nipro.interfaz.repository.ParameterRepository;
import sv.com.nipro.interfaz.repository.TransactionRepository;
import sv.com.nipro.interfaz.utils.Constans;
import sv.com.nipro.interfaz.utils.MessageUtil;
import sv.com.nipro.interfaz.utils.XMLProcessor;


@ManagedBean(name = "transactionController")
@Controller
public class TransactionController extends BaseBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(TransactionController.class);
	
	@Autowired
	private TransactionRepository transactionRpty;
	@Autowired
	private ArchiveRepository archiveRpty;
	@Autowired
	private Archivehl7Repository archiveHl7Rpty;
	@Autowired
	private ElementRepository elementRpty;
	@Autowired
	private ParameterRepository parameterRpty;
	@Autowired
	private SessionController session;
	
	private List<Transaction> lstTransaction;
	private List<Archive> lstArchives;
	private Transaction selectedTransaction;
	private String type = "";
	private String method = "";
	private Archivehl7 hl7Selected;
	private List<Archivehl7> lstArchiveHl7;
	private Archive selectedArchive;
	private List<Element> lstElements = new ArrayList<Element>();
	private String tokenRest;
	private Hl7DTO currentHl7;
	private Integer idSelected;
	
	//api
	private Retrofit retrofit;
	private UsuarioRequest usuarioRequest;
	private MessageRequest messageRequest;
	private CheckoutRequest checkoutRequest;
    private UsuarioResponse usuarioResponse;
    private MessageResponse messageResponse;
    private CheckoutResponse checkoutResponse;
    private Endpoints endpointsInterface;
    
    private String BASE_URL;
	private String USER;
	private String PASSWORD;
	//end api
	
	@PostConstruct
	public void init() {
		logger.info("******************TransactionController init*********************");
		
		try {
			fillTransactionLst();
			fillArchives();
			fillArchivesHl7();
			
			lstElements = elementRpty.findAll();
			
			
			BASE_URL = parameterRpty.findByCode("BASE_URL").getValue();
			USER = parameterRpty.findByCode("USER_API").getValue();
			PASSWORD = parameterRpty.findByCode("PASSWORD_API").getValue();			
			
			
			retrofit = new Retrofit.Builder()
	                .baseUrl(BASE_URL)
	                .addConverterFactory(GsonConverterFactory.create())
	                .build();
			
			endpointsInterface = retrofit.create(Endpoints.class); //api
						
						
		} catch (Exception e) {
			logger.error(e, e);
		}	
		
		//lstTransaction.get(0).getArchiveList();
		System.out.println("**TEST***");
		if (session != null && session.getUserInSession() != null) {
			logger.info("***********FUNCIONA**********");
			logger.info(session.getUserInSession().toString());
		}else if (session == null) {
			logger.info("*****Session null*****");
		}
		
	}

	public void fillTransactionLst() {
		try {
			if (type.length() == 0 && method.length() == 0){
				lstTransaction = transactionRpty.findAll();
				logger.info("1");
			} else if (type.length() > 0 && method.length() == 0){
				lstTransaction = transactionRpty.findByType(type);
				logger.info("2");
				logger.info("type : " + type);
				logger.info("method : " + method);
			} else if (method.length() > 0 && type.length() == 0){
				lstTransaction = transactionRpty.findByMethod(method);
				logger.info("3");
				logger.info("type : " + type);
				logger.info("method : " + method);
			} else {
				lstTransaction = transactionRpty.findByTypeAndMethod(type, method);
				logger.info("4");
				logger.info("type : " + type);
				logger.info("method : " + method);
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		
	}
	
	public void fillArchives() {
		lstArchives = archiveRpty.findByStatus("PENDING");
	}
	
	public void fillArchivesHl7() {
		lstArchiveHl7 = archiveHl7Rpty.findByStatus("PENDING");
	}
	
	public void prueba(Transaction tr){
		System.out.println(session.getToken().getToken());
		logger.info("***********prueba***********");
		if (tr != null){
			logger.info(tr.toString());
		}
		logger.info("**********************");
	}
	
	public void sendHl7() {
		showProgress(true);
		Hl7DTO dto = new Hl7DTO();
		
		Path currentRelativePath = Paths.get("");
		try {
			
			File file = new File(currentRelativePath.toString() + "/hl7/" + hl7Selected.getName());
			File solicitud = new File(currentRelativePath.toString() + "/solicitudes/" + selectedArchive.getName());
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			
			String st; 
			int count = 1;
			List<String> lstOBX = new ArrayList<String>();
			  while ((st = br.readLine()) != null) {
				  switch (count) {
				case 1:
					dto.setMSH(st);
					break;
				case 2:
					dto.setORC(st);
					break;
				case 3:
					dto.setOBR(st);
					break;
				default:
					lstOBX.add(st);
					break;
				}
			    count++;
			  }
			  dto.setOBXLlst(lstOBX);
			  br.close(); // ??
			  
			  br = new BufferedReader(new FileReader(solicitud));
			  String[] dataSolicitud = new String[1];
			  while ((st = br.readLine()) != null) {
				  dataSolicitud = st.split("_z");
			  }
			  br.close(); // ??
			  
			  String[] suministrante = dataSolicitud[0].split("\\|")[5].split("\\^"); 
			  
			dto.setMSH(dto.getMSH().replace("{SUMINISTRANTE_ID}", suministrante[0]));
			dto.setMSH(dto.getMSH().replace("{SUMINISTRANTE}", suministrante[1]));
			
			
			dto.setORC(dto.getORC().replace("{COD_EMPLEADO}",
					session.getUserInSession().getEmployeeList().get(0).getCode()));
			dto.setORC(dto.getORC().replace("{EMPLEADO}", session.getUserInSession().getEmployeeList().get(0).getName()
					+ " " + session.getUserInSession().getEmployeeList().get(0).getSurname()));
			
			
			dto.setOBR(dto.getOBR().replace("{COD_EMPLEADO}",
					session.getUserInSession().getEmployeeList().get(0).getCode()));
			dto.setOBR(dto.getOBR().replace("{EMPLEADO}", session.getUserInSession().getEmployeeList().get(0).getName()
					+ " " + session.getUserInSession().getEmployeeList().get(0).getSurname()));
			
			//TODO: Agregar envío
			currentHl7 = dto;
			apiCheckin();
			
		} catch (Exception e) {
			logger.error(e,e);
			MessageUtil.addErrorMessage("ERROR:", "Hubo un problema al procesar el archivo");
			showProgress(false);
			showMessages();
		}
		
		
	}
	
	public void readArchive() {
		showProgress(true);
		List<Hl7DTO> lstHl7Dto = new ArrayList<Hl7DTO>();
		XMLProcessor xml = new XMLProcessor();
		try {
			lstHl7Dto = xml.processXML(Constans.FILE_PATH, lstElements);
			
			if (lstHl7Dto != null) {
				for (Hl7DTO dto : lstHl7Dto) {

					if (!validateArchive(dto.getResultId())) {
						Archivehl7 archive = new Archivehl7();
						archive.setResultid(dto.getResultId());
						archive.setName("result_" + dto.getResultId() + ".txt");
						archive.setStatus("PENDING");
						archive.setCreatedAt(new Timestamp(System.currentTimeMillis()));

						archiveHl7Rpty.save(archive);

						// Creating archive
						Path currentRelativePath = Paths.get("");
						File fileHl7 = new File(
								currentRelativePath.toAbsolutePath().toString() + "/hl7/" + archive.getName());

						BufferedWriter bw;

						bw = new BufferedWriter(new FileWriter(fileHl7));
						bw.write(dto.getMSH());
						bw.newLine();
						bw.write(dto.getORC());
						bw.newLine();
						bw.write(dto.getOBR());
						if (dto.getOBXLlst() != null){
							for (String obx : dto.getOBXLlst()) {
								bw.newLine();
								bw.write(obx);
							}
						}
						bw.flush();
						bw.close();
					}

				}

				fillArchivesHl7();
				MessageUtil.addSuccessMessage("", "Lectura de resultados completa");
				showProgress(false);
				showMessages();				
				
			}

		} catch (Exception e) {
			logger.error(e, e);
			MessageUtil.addErrorMessage("ERROR: ", "Ocurrió un problema en la lectura de los resultados");
			showProgress(false);
			showMessages();
		}

	}
	
	public Boolean validateArchive(String resultId) {

		Archivehl7 archivehl7 = archiveHl7Rpty.findByResultid(resultId.trim());
		if (archivehl7 != null && archivehl7.getArchivehl7id() != null) {
			return true;
		}
		return false;
	}
	
	public void onSelectedArch(){
		if (idSelected != null){
			for (Archivehl7 archivehl7 : lstArchiveHl7) {
				if (archivehl7.getArchivehl7id().equals(idSelected)){
					hl7Selected = archivehl7;
					break;
				}
			}
		}
	}
	
	//Api methods
	private void apiCheckin(){
		
		usuarioRequest = new UsuarioRequest(USER, PASSWORD);
		Call<UsuarioResponse> checkinCallResponse = endpointsInterface.checkin(usuarioRequest);
		
		checkinCallResponse.enqueue(new Callback<UsuarioResponse>() {
			
			@Override
			public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
				usuarioResponse = response.body();
				if (usuarioResponse != null && usuarioResponse.estado){
					tokenRest = usuarioResponse.token;
					apiAcceptMessage();
				}
			}
			
			@Override
			public void onFailure(Call<UsuarioResponse> call, Throwable t) {
				MessageUtil.addErrorMessage("ERROR:", "No se pudo establecer la conexión con el servicio");
				logger.error(t, t);
				showProgress(false);
				showMessages();
			}
		});
	}
	
	
	public void apiAcceptMessage(){
		
		messageRequest = new MessageRequest(tokenRest, currentHl7.getHL7(), getChecksum(currentHl7.getHL7()));
		Call<MessageResponse> messageCallResponse = endpointsInterface.acceptMessage(messageRequest);
		
		messageCallResponse.enqueue(new Callback<MessageResponse>() {

			@Override
			public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
				messageResponse = response.body();
				if (messageResponse != null && messageResponse.Estado){
					MessageUtil.addSuccessMessage("Éxito:", "Resultados enviados correctamente");
				}else{
					MessageUtil.addErrorMessage("ERROR:", messageResponse.Mensaje);
				}
				showMessages();
				showProgress(false);
			}
			
			@Override
			public void onFailure(Call<MessageResponse> call, Throwable t) {
				MessageUtil.addErrorMessage("ERROR:", "No se pudo establecer la conexión con el servicio");
				logger.error(t, t);
				showProgress(false);
				showMessages();
			}
			
		});
		
	}
	
	
	public void apiCheckout(){
		checkoutRequest = new CheckoutRequest(tokenRest);
		Call<CheckoutResponse> checkoutCallResponse = endpointsInterface.checkout(checkoutRequest);
		
		checkoutCallResponse.enqueue(new Callback<CheckoutResponse>() {

			@Override
			public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
				checkoutResponse = response.body();
				if (checkoutResponse.Estado){
					logger.info("Sesión finalizada");
				}
			}
			
			@Override
			public void onFailure(Call<CheckoutResponse> call, Throwable t) {
				MessageUtil.addErrorMessage("ERROR:", "No se pudo establecer la conexión con el servicio");
				showMessages();
				logger.error(t, t);
				
			}
		});
	}
	//End Api methods
	
	
	public Archivehl7 getHl7Selected() {
		return hl7Selected;
	}
	
	public void setHl7Selected(Archivehl7 hl7Selected) {
		this.hl7Selected = hl7Selected;
	}
	
	public void sendSolicitude(Archive arc) {
		selectedArchive = arc;
		logger.info(arc.toString());
		
		PrimeFaces.current().executeScript("PF('sending').show();");
	}
	
	public void destroySession() {
		session.setUserInSession(null);
		session.setToken(null);
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/");
		} catch (IOException e) {
			logger.error(e, e);
			e.printStackTrace();
		}
	}
	
	public List<Archivehl7> getLstArchiveHl7() {
		return lstArchiveHl7;
	}
	
	public void setLstArchiveHl7(List<Archivehl7> lstArchiveHl7) {
		this.lstArchiveHl7 = lstArchiveHl7;
	}
	
	public Archive getSelectedArchive() {
		return selectedArchive;
	}
	
	public void setSelectedArchive(Archive selectedArchive) {
		this.selectedArchive = selectedArchive;
	}
	
	public List<Archive> getLstArchives() {
		return lstArchives;
	}
	
	public void setLstArchives(List<Archive> lstArchives) {
		this.lstArchives = lstArchives;
	}
	
	public List<Transaction> getLstTransaction() {
		return lstTransaction;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public Integer getIdSelected() {
		return idSelected;
	}
	
	public void setIdSelected(Integer idSelected) {
		this.idSelected = idSelected;
	}

}
