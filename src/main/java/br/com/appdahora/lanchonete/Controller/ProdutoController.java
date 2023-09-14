package br.com.appdahora.lanchonete.Controller;

import br.com.appdahora.lanchonete.Exception.Produto.ProdutoNaoEncontradoException;
import br.com.appdahora.lanchonete.Model.Produto;
import br.com.appdahora.lanchonete.Repository.ProdutoRepository;
import br.com.appdahora.lanchonete.Service.CadastroProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private CadastroProdutoService cadastroProdutoService;
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> buscar(){
        return produtoRepository.findAll();
    }
    @GetMapping("/{produtoId}")
    public Produto buscarPorId(@PathVariable Long produtoId){
        return produtoRepository.findById(produtoId)
                .orElseThrow(()
                        -> new ProdutoNaoEncontradoException
                        ("Produto não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto adicionar(@RequestBody Produto produto){
        return cadastroProdutoService.salvar(produto);
    }

    @DeleteMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long produtoId){
        cadastroProdutoService.remover(produtoId);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long produtoId,@RequestBody Produto produto){
        Optional<Produto> produtoAtual = produtoRepository.findById(produtoId);
        if(produtoAtual.isPresent()){
            BeanUtils.copyProperties(produto, produtoAtual.get(),"id");
            Produto produtoSalvo = cadastroProdutoService.salvar(produtoAtual.get());
            return ResponseEntity.ok(produtoSalvo);
        }
        return ResponseEntity.notFound().build();
    }


}
