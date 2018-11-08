package sv.com.nipro.interfaz.repository;


import sv.com.nipro.interfaz.entities.Parameter;

public interface ParameterRepositoryCustom {
	
	Parameter findByCode(String code);

}
