package br.com.appdahora.lanchonete.Service;

import br.com.appdahora.lanchonete.Model.Cozinha;
import br.com.appdahora.lanchonete.Repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void deleteById(Long cozinhaId){
        cozinhaRepository.deleteById(cozinhaId);
    }
}
