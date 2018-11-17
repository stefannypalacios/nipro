package sv.com.nipro.interfaz.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sv.com.nipro.interfaz.entities.Archive;
import sv.com.nipro.interfaz.entities.Transaction;
import sv.com.nipro.interfaz.repository.ArchiveRepository;
import sv.com.nipro.interfaz.repository.TransactionRepository;


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
	private SessionController session;
	
	private List<Transaction> lstTransaction;
	private List<Archive> lstArchives;
	private Transaction selectedTransaction;
	private String type = "";
	private String method = "";
	
	@PostConstruct
	public void init() {
		logger.info("******************TransactionController init*********************");
		fillTransactionLst();
		fillArchives();
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
	
	public void prueba(Transaction tr){
		logger.info("***********prueba***********");
		if (tr != null){
			logger.info(tr.toString());
		}
		logger.info("**********************");
	}
	
	public void sendSolicitude(Archive arc) {
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
