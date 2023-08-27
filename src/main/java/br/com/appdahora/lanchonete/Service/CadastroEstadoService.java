package br.com.appdahora.lanchonete.Service;

import br.com.appdahora.lanchonete.Controller.EstadoController;
import br.com.appdahora.lanchonete.Model.Estado;
import br.com.appdahora.lanchonete.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {
    @Autowired
    private EstadoRepository estadoRepository;
    public Estado salvar(Estado estado){
        //Colocar a sigla sempre em caixa alta
        estado.setSigla(estado.getSigla().toUpperCase());
        return estadoRepository.save(estado);
    }
    public void remover(Long estadoId){
        try{
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoController.EntidadeNaoEncontradaException(String.format("Nao existe um cadastro"+ "de estado com o codigo %d", estadoId));
        } catch (DataIntegrityViolationException e){
            throw new EstadoController.EntidadeEmUsoException(String.format("O estado com o código %d não pode ser removido porque está em uso", estadoId));
        }


    }
}
