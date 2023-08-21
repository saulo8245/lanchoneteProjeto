package br.com.appdahora.lanchonete.Repository;

import br.com.appdahora.lanchonete.Model.Cliente;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> listar();
    Cliente buscar(Long id);
    Cliente salvar(Cliente cliente);
    void remover(Cliente cliente);
}
