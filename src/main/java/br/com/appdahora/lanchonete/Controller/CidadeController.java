package br.com.appdahora.lanchonete.Controller;

import br.com.appdahora.lanchonete.Model.Cidade;
import br.com.appdahora.lanchonete.Model.Estado;
import br.com.appdahora.lanchonete.Repository.CidadeRepository;
import br.com.appdahora.lanchonete.Service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cidades")
@RestController
public class CidadeController {
    @Autowired
    private CadastroCidadeService cadastroCidadeService;
    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public Cidade findById(@PathVariable Long cidadeId){
        return cidadeRepository.findById(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Cidade adicionar(@RequestBody Cidade cidade){

        return cadastroCidadeService.salvar(cidade);
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId){

        cadastroCidadeService.remover(cidadeId);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade nao encontrada")
    public static class EntidadeNaoEncontradaException extends RuntimeException{
        private static final long serialVersionUID = 1L;
        public EntidadeNaoEncontradaException(String mensagem){
            super(mensagem);
        }
    }
}
