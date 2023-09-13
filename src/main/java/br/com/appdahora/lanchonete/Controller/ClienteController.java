package br.com.appdahora.lanchonete.Controller;
import br.com.appdahora.lanchonete.Model.Cliente;
import br.com.appdahora.lanchonete.Repository.ClienteRepository;
import br.com.appdahora.lanchonete.Repository.ClienteRepositoryImpl;
import br.com.appdahora.lanchonete.Service.CadastroClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private CadastroClienteService cadastroClienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public Cliente findById(@PathVariable Long clienteId){
        return clienteRepository.findById(clienteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente){

        return cadastroClienteService.salvar(cliente);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long clienteId){
        cadastroClienteService.remover(clienteId);
    }


    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
        Cliente clienteAtual = clienteRepository.findById(clienteId);
        if (clienteAtual != null){
            BeanUtils.copyProperties(cliente,clienteAtual,"id");
            Cliente clienteSalva = cadastroClienteService.salvar(clienteAtual);
            return ResponseEntity.ok(clienteSalva);
        }
        return ResponseEntity.notFound().build();
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade nao encontrada")
    public static class EntidadeNaoEncontradaException extends RuntimeException{
        private static final long serialVersionUID = 1L;
        public EntidadeNaoEncontradaException(String mensagem){
            super(mensagem);
        }
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entidade em uso")
    public static class EntidadeEmUsoException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public EntidadeEmUsoException(String mensagem) {
            super(mensagem);
        }
    }
}
