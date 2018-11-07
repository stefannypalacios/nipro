package sv.com.nipro.interfaz.repository;

import java.util.List;

import sv.com.nipro.interfaz.entities.Archive;

public interface ArchiveRepositoryCustom {
	
	List<Archive> findByStatus(String status);
	
	List<Archive> findByType(String type);

}
