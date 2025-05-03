package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
import app.repository.ConsoleRepository;

@SpringBootTest
public class ConsoleControllerTest {
	
	@Autowired
	ConsoleController consoleController;
	
	@MockitoBean
	ConsoleRepository consoleRepository;
	
	@BeforeEach
	void setup() {
		List<Console> lista = new ArrayList<>();
		
		Console console01 = new Console();
		
	    console01.setNome("PlayStation"); 
	    console01.setMarca("Sony");
		
		Console console02 = new Console();
		
		console02.setId(2L);
		console02.setNome("Xbox One");
		console02.setMarca("Microsoft");
		
		lista.add(console01);
		lista.add(console02);
		
		
	    when(consoleRepository.findAll()).thenReturn(lista); 
        when(consoleRepository.save(console01)).thenReturn(console01);  // Mock do save
        doNothing().when(consoleRepository).deleteById(2L);
        when(consoleRepository.findByNomeStartingWithIgnoreCase("Play")).thenReturn(List.of(console01));
        when(consoleRepository.findByMarcaContainingIgnoreCase("Sony")).thenReturn(List.of(console01));
	}
	
	
	@Test
	@DisplayName("Cenario 01 - Teste de integração FindAll com mockito")
	void cenario01() {

		
		ResponseEntity<List<Console>> retorno = this.consoleController.findAll();
		assertEquals(2, retorno.getBody().size());
	}
	
	@Test
	@DisplayName("Cenario 02 - Teste de integração FindAll com mockito")
	void cenario02() {
		
		
		
		ResponseEntity<List<Console>> retorno = this.consoleController.findAll();
		assertEquals(HttpStatus.OK, retorno.getStatusCode());
	}
	
	
	@Test
    @DisplayName("Cenário 03 - Teste de integração - save()")
    void cenario03() {
        Console console = new Console();
        console.setNome("PlayStation 5");
        console.setMarca("Sony");

        ResponseEntity<String> response = consoleController.save(console);

        assertEquals("Console salvo com sucesso", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
	
	
	@Test
    @DisplayName("Cenário 04 - Teste de integração - findById()")
    void cenario04() {
		Console consoleMock = new Console();
	    consoleMock.setId(1L);
	    consoleMock.setNome("PS4");
	    consoleMock.setMarca("SONY");

	    when(consoleRepository.findById(1L)).thenReturn(Optional.of(consoleMock));

        ResponseEntity<Console> response = consoleController.findById(1L);

        assertEquals(consoleMock, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
	
	@Test
	@DisplayName("Cenário 05 - Teste de integração - delete() - Console não encontrado")
	void cenario05() {
	   
	    when(consoleRepository.findById(2L)).thenReturn(Optional.empty());
	    
	    ResponseEntity<String> response = consoleController.delete(2L);

	    
	    assertEquals("Console não encontrado", response.getBody());
	    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	 
	 
	  @Test
	    @DisplayName("Cenário 06 - Teste de integração - findByNomeStartingWith()")
	    void cenario06() {
	        List<Console> consoles = new ArrayList<>();
	        Console console = new Console();
	        console.setNome("PlayStation");
	        consoles.add(console);

	        ResponseEntity<List<Console>> response = consoleController.findByNomeStartingWith("Play");

	        assertEquals(1, response.getBody().size());
	        assertEquals("PlayStation", response.getBody().get(0).getNome());
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	    }
	  
	  
	  @Test
	    @DisplayName("Cenário 07 - Teste de integração - findByMarca()")
	    void cenario07() {
	        List<Console> consoles = new ArrayList<>();
	        Console console = new Console();
	        console.setMarca("Sony");
	        consoles.add(console);

	        ResponseEntity<List<Console>> response = consoleController.findByMarca("Sony");

	        assertEquals(1, response.getBody().size());
	        assertEquals("Sony", response.getBody().get(0).getMarca());
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	    }
	
	
	
}
