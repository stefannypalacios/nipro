package sv.com.nipro.interfaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.com.nipro.interfaz.entities.Archivehl7;

@Repository
public interface Archivehl7Repository extends JpaRepository<Archivehl7, Integer>, Archivehl7RepositoryCustom{

}
