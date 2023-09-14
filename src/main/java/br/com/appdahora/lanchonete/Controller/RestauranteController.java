package br.com.appdahora.lanchonete.Controller;

import br.com.appdahora.lanchonete.Exception.RestauranteNaoEncontradoException;
import br.com.appdahora.lanchonete.Model.Restaurante;
import br.com.appdahora.lanchonete.Repository.RestauranteRepository;
import br.com.appdahora.lanchonete.Service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> buscar(){
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscarPorId(@PathVariable Long restauranteId){
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(()
                        -> new RestauranteNaoEncontradoException("Produto não encontrado"));}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante){
        return cadastroRestauranteService.salvar(restaurante);
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId){
        cadastroRestauranteService.remover(restauranteId);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
        if(restauranteAtual.isPresent()){
            BeanUtils.copyProperties(restaurante, restauranteAtual.get(),"id");
            Restaurante restauranteSalvo = cadastroRestauranteService.salvar(restauranteAtual.get());
            return ResponseEntity.ok(restauranteSalvo);
        }
        return ResponseEntity.notFound().build();
    }

   /* @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,@RequestBody Map<String, Object> campos) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
        if (restauranteAtual.isPresent()) {
            merge(campos, restauranteAtual);
            return atualizar(restauranteId, restauranteAtual);
        }
        return ResponseEntity.notFound().build();
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem,Restaurante.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade)-> {
        Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);

            field.setAccessible(true);
            Object novoValor =ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field,restauranteDestino, novoValor);
        });

    }

}

    */

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
        if (restauranteAtual.isPresent()) {
            merge(campos, restauranteAtual.get()); // Chamar .get() para obter o objeto Restaurante
            Restaurante restauranteSalvo = cadastroRestauranteService.salvar(restauranteAtual.get()); // Salvar o restaurante atualizado
            return ResponseEntity.ok(restauranteSalvo); // Retornar a resposta com o restaurante atualizado
        }
        return ResponseEntity.notFound().build();
    }


    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem,Restaurante.class);
        dadosOrigem.forEach((nomePropriedade, valorPropriedade)-> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);

            field.setAccessible(true);
            Object novoValor =ReflectionUtils.getField(field, restauranteOrigem);
            ReflectionUtils.setField(field,restauranteDestino, novoValor);
        });
    }
}

