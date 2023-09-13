package br.com.appdahora.lanchonete.Repository;

import br.com.appdahora.lanchonete.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
