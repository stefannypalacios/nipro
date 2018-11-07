package sv.com.nipro.interfaz.repository;

import java.util.List;

import sv.com.nipro.interfaz.entities.Archivehl7;

public interface Archivehl7RepositoryCustom {
	
	Archivehl7 findByResultid(String resultid);
	
	List<Archivehl7> findByStatus(String status);

}
