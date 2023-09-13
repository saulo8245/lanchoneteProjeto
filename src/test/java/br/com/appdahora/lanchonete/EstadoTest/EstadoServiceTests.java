package br.com.appdahora.lanchonete.EstadoTest;

import br.com.appdahora.lanchonete.Model.Estado;
import br.com.appdahora.lanchonete.Service.CadastroCidadeService;
import br.com.appdahora.lanchonete.Service.CadastroEstadoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EstadoServiceTests {
    @Test
    public void
    deveAtribuirId_quandoCadastroEstado_ComDadosCorretos(){

        Estado novoEstado = new Estado();
        novoEstado.setNome("Alagoas");
        novoEstado.setSigla("AL");

        CadastroEstadoService estadoService = null;
        novoEstado = estadoService.salvar(novoEstado);        assertThat(novoEstado).isNotNull();
        assertThat(novoEstado.getId()).isNotNull();
    }

}
