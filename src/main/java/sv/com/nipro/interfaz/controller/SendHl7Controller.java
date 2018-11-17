package sv.com.nipro.interfaz.controller;

import java.io.BufferedWriter;
import java.io.File;
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
import sv.com.nipro.interfaz.repository.ArchiveRepository;
import sv.com.nipro.interfaz.repository.Archivehl7Repository;
import sv.com.nipro.interfaz.repository.ElementRepository;
import sv.com.nipro.interfaz.utils.Constans;
import sv.com.nipro.interfaz.utils.XMLProcessor;

@ManagedBean(name = "SendHl7Controller")
@Controller
public class SendHl7Controller extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SendHl7Controller.class);

	@Autowired
	private ArchiveRepository archiveRpst;
	@Autowired
	private Archivehl7Repository archiveHl7Rpst;
	@Autowired
	private ElementRepository elementRpst;
	@Autowired
	private SessionController session;

	private List<Archive> lstArchives = new ArrayList<Archive>();
	private List<Archivehl7> lstArchivesHl7 = new ArrayList<Archivehl7>();
	private List<Element> lstElements = new ArrayList<Element>();

	@PostConstruct
	public void init() {
		fillList();
	}

	private void fillList() {
		lstArchives = archiveRpst.findByStatus("PENDING");
		lstArchivesHl7 = archiveHl7Rpst.findByStatus("PENDING");
		lstElements = elementRpst.findAll();
	}

	private void readArchive() {
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

						archiveHl7Rpst.save(archive);

						// Creating archive
						Path currentRelativePath = Paths.get("");
						File fileHl7 = new File(currentRelativePath.toAbsolutePath().toString() + archive.getName());
						
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

		Archivehl7 archivehl7 = archiveHl7Rpst.findByResultid(resultId);
		if (archivehl7 != null && archivehl7.getArchivehl7id() != null) {
			return true;
		}
		return false;
	}

}
