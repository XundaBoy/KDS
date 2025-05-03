package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.entity.Usuario;
import app.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

	 @Autowired
	 private UsuarioService usuarioService;
	 
	 @MockitoBean
	 private UsuarioRepository usuarioRepository;
	 
	 Usuario usuario;
	 
	 @BeforeEach
	 void setup() {
	       

	    usuario = new Usuario();
	    usuario.setNome("João Silva");
	    usuario.setCpf("123.456.789-00");
	    usuario.setEmail("joao@email.com");
	    usuario.setSenha("senha123");
	 }
	 
	 
	 @Test
	 @DisplayName("Cenário 01 - Teste Unitario - Deve lançar exceção ao salvar usuário com CPF já cadastrado")

	    void testSaveComCpfDuplicado() {
	        when(usuarioRepository.existsByCpf(usuario.getCpf())).thenReturn(true);

	        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
	            usuarioService.save(usuario);
	        });

	        assertEquals("Cpf ja cadastrado", ex.getMessage());
	    }

	    @Test
	    @DisplayName("Cenário 02 - Deve salvar usuário com status INCOMPLETO quando telefone for nulo")

	    void testSaveComTelefoneNulo() {
	        when(usuarioRepository.existsByCpf(usuario.getCpf())).thenReturn(false);
	        usuario.setTelefone(null);

	        String resultado = usuarioService.save(usuario);

	        assertEquals("INCOMPLETO", usuario.getStatusCadastro());
	        assertEquals("Usuario salvo com sucesso!", resultado);
	       
	    }

	    @Test
	    @DisplayName("Cenário 03 - Deve salvar usuário com status COMPLETO quando telefone for preenchido")

	    void testSaveComTelefonePreenchido() {
	        when(usuarioRepository.existsByCpf(usuario.getCpf())).thenReturn(false);
	        usuario.setTelefone("11999999999");

	        String resultado = usuarioService.save(usuario);

	        assertEquals("COMPLETO", usuario.getStatusCadastro());
	        assertEquals("Usuario salvo com sucesso!", resultado);
	     
	    }
}
