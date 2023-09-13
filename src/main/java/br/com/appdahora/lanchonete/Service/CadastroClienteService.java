package br.com.appdahora.lanchonete.Service;

import br.com.appdahora.lanchonete.Controller.ClienteController;
import br.com.appdahora.lanchonete.Model.Cliente;
import br.com.appdahora.lanchonete.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente salvar(Cliente cliente){

        return clienteRepository.save(cliente);
    }
    public void remover(Long estadoId){
        try{
            clienteRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new ClienteController.EntidadeNaoEncontradaException(String.format("Nao existe um cadastro"+ "de estado com o codigo %d", estadoId));
        } catch (DataIntegrityViolationException e){
            throw new ClienteController.EntidadeEmUsoException(String.format("O estado com o código %d não pode ser removido porque está em uso", estadoId));
        }


    }

}
