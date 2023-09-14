package br.com.appdahora.lanchonete.Repository;

import br.com.appdahora.lanchonete.Model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository  extends JpaRepository<Restaurante, Long> {
}
