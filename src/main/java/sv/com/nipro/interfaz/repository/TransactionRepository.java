package sv.com.nipro.interfaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.com.nipro.interfaz.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>, TransactionRepositoryCustom {
}