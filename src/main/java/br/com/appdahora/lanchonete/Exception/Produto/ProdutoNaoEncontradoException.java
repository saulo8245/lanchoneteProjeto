package br.com.appdahora.lanchonete.Exception.Produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade nao encontrada")
public class ProdutoNaoEncontradoException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ProdutoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
