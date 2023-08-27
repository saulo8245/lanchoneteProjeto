package br.com.appdahora.lanchonete.Repository;

import br.com.appdahora.lanchonete.Model.Cidade;
import br.com.appdahora.lanchonete.Model.Estado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository {
    List<Cidade> findAll();
    Cidade findById(Long id);
    Cidade save(Cidade cidade);
    void deleteById(Long id);

}
