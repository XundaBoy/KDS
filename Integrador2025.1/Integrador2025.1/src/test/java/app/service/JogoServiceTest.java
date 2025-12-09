package app.service;





import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.entity.Console;
import app.entity.Jogo;
import app.repository.ConsoleRepository;
import app.repository.JogoRepository;


@SpringBootTest
public class JogoServiceTest {

	@Autowired
    private JogoService jogoService;

	@MockitoBean
    private JogoRepository jogoRepository;

	@MockitoBean
    private ConsoleRepository consoleRepository;
    
	Console consoleMock; 

    @BeforeEach
    void setup() {
        

        consoleMock = new Console();
        consoleMock.setId(1L); 
        consoleMock.setNome("PlayStation 5");
        consoleMock.setMarca("Sony");

        Jogo jogo1 = new Jogo();
        jogo1.setNome("Spider-Man");
        jogo1.setConsole(consoleMock);

        Jogo jogo2 = new Jogo();
        jogo2.setNome("God of War Ragnarok");
        jogo2.setConsole(consoleMock);

		List<Jogo> lista = new ArrayList<>();
		
		lista.add(jogo1);
		lista.add(jogo2);

        
        when(jogoRepository.findByConsole(consoleMock)).thenReturn(lista);
    }
    
    
    @Test
    @DisplayName("Cenario 01 - Teste de Integração FindByConsole com mock")
    void deveRetornarJogosDoConsoleMockado() {
        List<Jogo> resultado = jogoService.findByConsole(consoleMock);

        assertEquals(2, resultado.size());
        assertEquals("Spider-Man", resultado.get(0).getNome());
        assertEquals("God of War Ragnarok", resultado.get(1).getNome());
    }
	
	
}
