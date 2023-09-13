package br.com.appdahora.lanchonete.Service;

import br.com.appdahora.lanchonete.Model.Produto;
import br.com.appdahora.lanchonete.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void remover(Long produtoId) {
            produtoRepository.deleteById(produtoId);

    }
}
