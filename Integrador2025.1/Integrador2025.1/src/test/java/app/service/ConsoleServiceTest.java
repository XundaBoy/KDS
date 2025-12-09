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
	
	
	@Test
	@DisplayName("Cenario 02 - Teste de integração - findById()")
	void cenario02() {
	    Console console = consoleService.findById(100L);
	    
	    assertEquals("PS4", console.getNome());
	    assertEquals("SONY", console.getMarca());
	    assertEquals(100L, console.getId());
	}
	
	
	@Test
	@DisplayName("Cenário 03 - Teste de integração - findAll()")
	void cenario03() {
	    List<Console> listaMock = new ArrayList<>();

	    Console console1 = new Console();
	    console1.setId(1L);
	    console1.setNome("Xbox One");
	    console1.setMarca("Microsoft");

	    Console console2 = new Console();
	    console2.setId(2L);
	    console2.setNome("Nintendo Switch");
	    console2.setMarca("Nintendo");

	    listaMock.add(console1);
	    listaMock.add(console2);

	    when(consoleRepository.findAll()).thenReturn(listaMock);

	    List<Console> resultado = consoleService.findAll();

	    assertEquals(2, resultado.size());
	    assertEquals("Xbox One", resultado.get(0).getNome());
	    assertEquals("Nintendo Switch", resultado.get(1).getNome());
	}
	
	@Test
	@DisplayName("Cenário 04 - Teste de integração - update() com dados válidos")
	void cenario04() {
	    Console consoleAtualizado = new Console();
	    consoleAtualizado.setNome("Xbox Series X");
	    consoleAtualizado.setMarca("Microsoft");

	    
	    when(consoleRepository.save(consoleAtualizado)).thenReturn(consoleAtualizado);

	    String resposta = consoleService.update(consoleAtualizado, 200L);

	    assertEquals("Console atualizado com sucesso", resposta);
	    assertEquals(200L, consoleAtualizado.getId());
	    verify(consoleRepository).save(consoleAtualizado);
	}
	
}
