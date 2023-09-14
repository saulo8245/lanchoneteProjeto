package br.com.appdahora.lanchonete.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Entidade nao encontrada")
public class RestauranteNaoEncontradoException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public RestauranteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
