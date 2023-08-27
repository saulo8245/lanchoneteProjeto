package br.com.appdahora.lanchonete.Repository;

import br.com.appdahora.lanchonete.Model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteRepositoryImpl implements ClienteRepository{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cliente> listar() {
        return manager.createQuery("from Cliente",
                Cliente.class).getResultList();
    }

    @Override
    public Cliente buscar(Long id) {

        return manager.find(Cliente.class, id);
    }

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {
        System.out.println("cliente: "+cliente.getId());
        return manager.merge(cliente);
    }

    @Override
    @Transactional
    public void remover(Cliente cliente) {
        System.out.println("cliente: "+cliente.getId());
        cliente = buscar(cliente.getId());
        manager.remove(cliente);

    }


}
