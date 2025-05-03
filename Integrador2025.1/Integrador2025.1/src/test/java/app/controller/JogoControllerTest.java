package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.entity.Jogo;
import app.entity.Usuario;
import app.entity.Console;
import app.repository.JogoRepository;
import app.service.JogoService;

@SpringBootTest
public class JogoControllerTest {

    @Autowired
    private JogoController jogoController;

    @MockitoBean
    private JogoRepository jogoRepository; 
    
    @MockitoBean
    private JogoService jogoService; 

    Console consoleMock;
    Jogo jogo;
    Usuario usuarioMock;


    @BeforeEach
    void setup() {
        
        consoleMock = new Console();
        consoleMock.setId(1L);
        consoleMock.setNome("PlayStation 5");
        consoleMock.setMarca("Sony");

       
        jogo = new Jogo();
        jogo.setNome("God of War");
        jogo.setEstadoJogo("Novo");
        jogo.setValor(250.00f);
        jogo.setConsole(consoleMock);
        
        usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setNome("John Doe");
        usuarioMock.setEmail("johndoe@example.com");
        usuarioMock.setCpf("123.456.789-00");
        usuarioMock.setSenha("senha123");

       
        when(jogoRepository.save(jogo)).thenReturn(jogo); 

       
        when(jogoService.save(jogo)).thenReturn("Jogo salvo com sucesso");
    }

    @Test
    @DisplayName("Cenario 01 - Teste de integração Save com mockito no repository")
    void cenario01() {
      
        ResponseEntity<String> retorno = jogoController.save(jogo);

        
        assertEquals("Jogo salvo com sucesso", retorno.getBody());
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }
}

