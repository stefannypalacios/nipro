package sv.com.nipro.interfaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.com.nipro.interfaz.entities.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>, TokenRepositoryCustom {
}