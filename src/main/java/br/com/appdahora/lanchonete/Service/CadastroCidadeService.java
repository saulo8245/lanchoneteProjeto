package br.com.appdahora.lanchonete.Service;

import br.com.appdahora.lanchonete.Controller.EstadoController;
import br.com.appdahora.lanchonete.Model.Cidade;
import br.com.appdahora.lanchonete.Repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    public void remover(Long cidadeId){
        try{
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoController.EntidadeNaoEncontradaException(String.format("Nao existe um cadastro"+ "de estado com o codigo %d", cidadeId));
        }
    }
}
