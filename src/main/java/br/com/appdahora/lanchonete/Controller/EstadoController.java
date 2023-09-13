package br.com.appdahora.lanchonete.Controller;


import br.com.appdahora.lanchonete.Model.Estado;
import br.com.appdahora.lanchonete.Repository.EstadoRepository;
import br.com.appdahora.lanchonete.Service.CadastroEstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
   public Estado adicionar(@RequestBody @Valid Estado estado){
        return cadastroEstadoService.salvar(estado);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId){
        cadastroEstadoService.remover(estadoId);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        Estado estadoAtual = estadoRepository.findById(estadoId);
        if (estadoAtual != null){
            BeanUtils.copyProperties(estado,estadoAtual,"id");
            Estado estadoSalva = cadastroEstadoService.salvar(estadoAtual);
            return ResponseEntity.ok(estadoSalva);
        }
        return ResponseEntity.notFound().build();
    }


    //Classes EXCEPTION
    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade nao encontrada")
    public static class EntidadeNaoEncontradaException extends RuntimeException{
        private static final long serialVersionUID = 1L;
        public EntidadeNaoEncontradaException(String mensagem){
            super(mensagem);
        }
    }

    //Classes EXCEPTION
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entidade em uso")
    public static class EntidadeEmUsoException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public EntidadeEmUsoException(String mensagem) {
            super(mensagem);
        }
    }
    //FIM das Classes EXCEPTION
}
