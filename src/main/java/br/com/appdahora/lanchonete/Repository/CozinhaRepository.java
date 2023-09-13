package br.com.appdahora.lanchonete.Repository;

import br.com.appdahora.lanchonete.Model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}
