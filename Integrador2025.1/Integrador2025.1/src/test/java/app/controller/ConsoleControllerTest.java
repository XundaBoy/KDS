package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
		
		console01.setNome("PS4");
		console01.setMarca("SONY");
		
		Console console02 = new Console();
		
		console02.setNome("Wii");
		console02.setMarca("Nintendo");
		
		lista.add(console01);
		lista.add(console02);
		
		
		
		when(consoleRepository.findAll()).thenReturn(lista);
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
	
	
	
}
