package br.com.appdahora.lanchonete.Service;

import br.com.appdahora.lanchonete.Model.Restaurante;
import br.com.appdahora.lanchonete.Repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante salvar(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }

    public void remover(Long restauranteId){
        restauranteRepository.deleteById(restauranteId);
    }
}
