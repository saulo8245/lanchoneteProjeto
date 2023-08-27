package br.com.appdahora.lanchonete.Repository;

import br.com.appdahora.lanchonete.Model.Cidade;
import br.com.appdahora.lanchonete.Model.Cliente;
import br.com.appdahora.lanchonete.Model.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Cidade> findAll() {
        return manager.createQuery("from Cidade",Cidade.class).getResultList();
    }

    @Override
    public Cidade findById(Long id) {

        return manager.find(Cidade.class, id);
    }

    @Override
    @Transactional
    public Cidade save(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Cidade cidade = findById(id);
        manager.remove(cidade);
    }
}
