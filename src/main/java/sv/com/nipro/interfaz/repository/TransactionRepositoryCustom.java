package sv.com.nipro.interfaz.repository;

import java.util.List;

import sv.com.nipro.interfaz.entities.Transaction;

public interface TransactionRepositoryCustom {

	List<Transaction> findByTypeAndMethod(String type, String method);
	List<Transaction> findByMethod(String method);
	List<Transaction> findByType(String type);
}
