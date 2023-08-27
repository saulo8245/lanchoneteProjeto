package br.com.appdahora.lanchonete.Controller;

import br.com.appdahora.lanchonete.Model.Estado;
import br.com.appdahora.lanchonete.Repository.EstadoRepository;
import br.com.appdahora.lanchonete.Service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadoService cadastroEstadoService;
    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> findAll(){
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Estado findById(@PathVariable Long estadoId){
        return estadoRepository.findById(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

   public Estado adicionar(@RequestBody Estado estado){
        return cadastroEstadoService.salvar(estado);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId){
        cadastroEstadoService.remover(estadoId);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade nao encontrada")
    public static class EntidadeNaoEncontradaException extends RuntimeException{
        private static final long serialVersionUID = 1L;
        public EntidadeNaoEncontradaException(String mensagem){
            super(mensagem);
        }
    }

}
