package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.entity.Console;
import app.repository.ConsoleRepository;

@SpringBootTest
public class ConsoleServiceTest {
	
	@Autowired
	ConsoleService consoleService;
	
	@MockitoBean
	ConsoleRepository consoleRepository;
	
	@BeforeEach
	void setup() {
		
		
		Console console01 = new Console();
		
		console01.setNome("PS4");
		console01.setMarca("SONY");
		console01.setId(100L);
		
		
		when(consoleRepository.findById(100L)).thenReturn(Optional.of(console01));
	    doNothing().when(consoleRepository).deleteById(100L);

	}
	
	@Test
	@DisplayName("Cenario 01 - Teste de integração - Delete()")
	void cenario01() {
		
		String retorno = this.consoleService.delete(100L);
		assertEquals("Console deletado com sucesso", retorno);


	}
	
}
