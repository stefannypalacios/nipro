package sv.com.nipro.interfaz.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sv.com.nipro.interfaz.dto.Hl7DTO;
import sv.com.nipro.interfaz.entities.Archive;
import sv.com.nipro.interfaz.entities.Archivehl7;
import sv.com.nipro.interfaz.entities.Element;
import sv.com.nipro.interfaz.entities.Transaction;
import sv.com.nipro.interfaz.repository.ArchiveRepository;
import sv.com.nipro.interfaz.repository.Archivehl7Repository;
import sv.com.nipro.interfaz.repository.ElementRepository;
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
	
	
	@PostConstruct
	public void init() {
		logger.info("******************TransactionController init*********************");
		fillTransactionLst();
		fillArchives();
		fillArchivesHl7();
		
		lstElements = elementRpty.findAll();
		
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
			  
			  br = new BufferedReader(new FileReader(solicitud));
			  String[] dataSolicitud = new String[1];
			  while ((st = br.readLine()) != null) {
				  dataSolicitud = st.split("_z");
			  }
			  
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
			
			
		} catch (Exception e) {
			logger.error(e,e);
			MessageUtil.addErrorMessage("ERROR:", "Hubo un problema al procesar el archivo");			
		}
		
		
	}
	
	public void readArchive() {
		List<Hl7DTO> lstHl7Dto = new ArrayList<Hl7DTO>();
		XMLProcessor xml = new XMLProcessor();
		lstHl7Dto = xml.processXML(Constans.FILE_PATH, lstElements);

		if (lstHl7Dto != null) {
			for (Hl7DTO dto : lstHl7Dto) {
				try {
					
					if (!validateArchive(dto.getResultId())) {
						Archivehl7 archive = new Archivehl7();
						archive.setResultid(dto.getResultId());
						archive.setName("result_" + dto.getResultId() + ".txt");
						archive.setStatus("PENDING");
						archive.setCreatedAt(new Timestamp(System.currentTimeMillis()));

						archiveHl7Rpty.save(archive);

						// Creating archive
						Path currentRelativePath = Paths.get("");
						File fileHl7 = new File(currentRelativePath.toAbsolutePath().toString() + "/hl7/" + archive.getName());
						
						BufferedWriter bw;
						
						bw = new BufferedWriter(new FileWriter(fileHl7));
						bw.write(dto.getHL7());
						bw.close();
					}				
					
				} catch (Exception e) {
					logger.error(e, e);
				}
			}

		}

	}
	
	public Boolean validateArchive(String resultId) {

		Archivehl7 archivehl7 = archiveHl7Rpty.findByResultid(resultId);
		if (archivehl7 != null && archivehl7.getArchivehl7id() != null) {
			return true;
		}
		return false;
	}
	
	
	public Archivehl7 getHl7Selected() {
		return hl7Selected;
	}
	
	public void setHl7Selected(Archivehl7 hl7Selected) {
		this.hl7Selected = hl7Selected;
	}
	
	public void sendSolicitude(Archive arc) {
		selectedArchive = arc;
		logger.info(arc.toString());
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

}
