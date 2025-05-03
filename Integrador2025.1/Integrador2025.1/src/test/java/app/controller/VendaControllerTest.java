package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.entity.Console;
import app.entity.Jogo;
import app.entity.Usuario;
import app.entity.Venda;
import app.service.VendaService;
import app.repository.ConsoleRepository;
import app.repository.JogoRepository;
import app.repository.VendaRepository;

@SpringBootTest
public class VendaControllerTest {

    @Autowired
    private VendaController vendaController;

    @MockitoBean
    private VendaService vendaService;

    @MockitoBean
    private VendaRepository vendaRepository;

    @MockitoBean
    private ConsoleRepository consoleRepository;

    @MockitoBean
    private JogoRepository jogoRepository;

    private Venda venda;
    private Console console;
    private Jogo jogo;

  
    @BeforeEach
    void setup() {
        console = new Console();
        console.setId(1L);
        console.setNome("PS5");
        console.setMarca("Sony");

        jogo = new Jogo();
        jogo.setId(1L);
        jogo.setNome("FIFA 22");
        jogo.setConsole(console);

        venda = new Venda();
        venda.setId(1L);
        venda.setJogo(jogo);

        // Criando instâncias de Usuario
        Usuario usuarioVendedor = new Usuario();
        usuarioVendedor.setId(1L);
        Usuario usuarioComprador = new Usuario();
        usuarioComprador.setId(2L);

        // Associando os usuários à venda
        venda.setUsuarioVendedor(usuarioVendedor);
        venda.setUsuarioComprador(usuarioComprador);

        // Simulando comportamentos dos métodos do serviço
        when(vendaService.realizarVenda(1L, 1L, 2L)).thenReturn("Venda realizada com sucesso!");
        when(vendaService.findAll()).thenReturn(List.of(venda));
        when(vendaService.findById(1L)).thenReturn(Optional.of(venda));
        when(vendaService.findByJogo(jogo)).thenReturn(List.of(venda));
        when(vendaService.findByJogo_Console(console)).thenReturn(List.of(venda));

        // Substitua o doNothing() por when para retornar o valor esperado
        when(vendaService.delete(1L)).thenReturn("Venda deletada com sucesso!");
    }



    @Test
    @DisplayName("Cenário 01 - Teste de integração - Realizar venda")
    void cenario01() {
        ResponseEntity<String> response = vendaController.realizarVenda(1L, 1L, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Venda realizada com sucesso!", response.getBody());
    }

    @Test
    @DisplayName("Cenário 02 - Teste de integração - findAll() com vendas cadastradas")
    void cenario02() {
        ResponseEntity<List<Venda>> response = vendaController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("FIFA 22", response.getBody().get(0).getJogo().getNome());
    }

    @Test
    @DisplayName("Cenário 03 - Teste de integração - findById() com ID válido")
    void cenario03() {
        ResponseEntity<Optional<Venda>> response = vendaController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("FIFA 22", response.getBody().get().getJogo().getNome());
    }

    @Test
    @DisplayName("Cenário 04 - Teste de integração - delete() com ID válido")
    void cenario04() {
        ResponseEntity<String> response = vendaController.deleteVenda(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Venda deletada com sucesso!", response.getBody());
    }

    @Test
    @DisplayName("Cenário 05 - Teste de integração - findByJogo() com jogo válido")
    void cenario05() {
        ResponseEntity<List<Venda>> response = vendaController.findByJogo(jogo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("FIFA 22", response.getBody().get(0).getJogo().getNome());
    }

    @Test
    @DisplayName("Cenário 06 - Teste de integração - findByConsole() com console válido")
    void cenario06() {
        ResponseEntity<List<Venda>> response = vendaController.findByJogo_Console(console);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("FIFA 22", response.getBody().get(0).getJogo().getNome());
    }
}
