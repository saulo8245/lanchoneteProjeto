package br.com.appdahora.lanchonete.Controller;

import br.com.appdahora.lanchonete.Model.Cozinha;
import br.com.appdahora.lanchonete.Repository.CozinhaRepository;
import br.com.appdahora.lanchonete.Service.CadastroCozinhaService;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> buscarPorTodos(){
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public Optional<Cozinha> buscarPorId(@PathVariable Long cozinhaId){
        //INCOMPLETA FALTANDO A LAMBDA
        return  cozinhaRepository.findById(cozinhaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cadastroCozinhaService.salvar(cozinha);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId){
        cadastroCozinhaService.deleteById(cozinhaId);
    }

    @PutMapping("{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);
        if (cozinhaAtual.isPresent()){
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaSalva);
        }
        return ResponseEntity.notFound().build();
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade nao encontrada")
    public static class CozinhaNaoEncontradoException extends RuntimeException{
        private static final long serialVersionUID = 1L;
        public CozinhaNaoEncontradoException(String mensagem){
            super(mensagem);
        }
    }
}
