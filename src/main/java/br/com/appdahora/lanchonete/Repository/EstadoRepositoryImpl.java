package br.com.appdahora.lanchonete.Repository;

import br.com.appdahora.lanchonete.Model.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> findAll() {
        return manager.createQuery("from Estado ",Estado.class).getResultList();
    }

    @Override
    public Estado findById(Long id) {
        return manager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public Estado save(Estado estado) {
        return manager.merge(estado);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Estado estado = findById(id);
        manager.remove(estado);


    }
}
