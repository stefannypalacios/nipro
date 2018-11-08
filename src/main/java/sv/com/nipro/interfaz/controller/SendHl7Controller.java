package sv.com.nipro.interfaz.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sv.com.nipro.interfaz.entities.Archive;
import sv.com.nipro.interfaz.entities.Archivehl7;
import sv.com.nipro.interfaz.repository.ArchiveRepository;
import sv.com.nipro.interfaz.repository.Archivehl7Repository;

@ManagedBean(name = "SendHl7Controller")
@Controller
public class SendHl7Controller extends BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ArchiveRepository archiveRpst;
	@Autowired
	private Archivehl7Repository archiveHl7Rpst;
	@Autowired
	private SessionController session;
	
	private List<Archive> lstArchives = new ArrayList<Archive>();
	private List<Archivehl7> lstArchivesHl7 = new ArrayList<Archivehl7>();
	
	@PostConstruct
	public void init() {
		fillList();
	}
	
	private void fillList() {
		lstArchives = archiveRpst.findByStatus("PENDING");
		lstArchivesHl7 = archiveHl7Rpst.findByStatus("PENDING");
	}
	
	private void readArchive() {
		
	}

}
