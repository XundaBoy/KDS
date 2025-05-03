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

import app.entity.Usuario;
import app.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        List<Usuario> lista = new ArrayList<>();

        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("João Silva");
        usuario1.setCpf("123.456.789-00");
        usuario1.setEmail("joao.silva@example.com");
        usuario1.setSenha("12345");
        
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Maria Oliveira");
        usuario2.setCpf("987.654.321-00");
        usuario2.setEmail("maria.oliveira@example.com");
        usuario2.setSenha("54321");

        lista.add(usuario1);
        lista.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(lista);
        when(usuarioRepository.save(usuario1)).thenReturn(usuario1);
        doNothing().when(usuarioRepository).deleteById(2L);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario1));
    }

    @Test
    @DisplayName("Cenário 01 - Teste de integração - findAll()")
    void cenario01() {
        ResponseEntity<List<Usuario>> retorno = this.usuarioController.findAll();
        assertEquals(2, retorno.getBody().size());
        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 02 - Teste de integração - save()")
    void cenario02() {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos Pereira");
        usuario.setCpf("112.233.445-56");
        usuario.setEmail("carlos.pereira@example.com");
        usuario.setSenha("password");

        ResponseEntity<String> response = usuarioController.save(usuario);

        assertEquals("Usuario salvo com sucesso!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 03 - Teste de integração - update()")
    void cenario03() {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos Pereira Atualizado");
        usuario.setCpf("112.233.445-56");
        usuario.setEmail("carlos.atualizado@example.com");
        usuario.setSenha("newpassword");

        ResponseEntity<String> response = usuarioController.update(usuario, 1L);

        assertEquals("Usuario atualizado com sucesso", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 04 - Teste de integração - findById()")
    void cenario04() {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setNome("João Silva");
        usuarioMock.setCpf("123.456.789-00");
        usuarioMock.setEmail("joao.silva@example.com");
        usuarioMock.setSenha("12345");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));

        ResponseEntity<Optional<Usuario>> response = usuarioController.findById(1L);

        assertEquals(usuarioMock, response.getBody().get());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 05 - Teste de integração - delete()")
    void cenario05() {
        ResponseEntity<String> response = usuarioController.deleteUsuario(2L);

        assertEquals("Usuario deletado com sucesso!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 06 - Teste de integração - findByNomeStartingWithIgnoreCase()")
    void cenario06() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setNome("João Silva");
        usuarios.add(usuario);

        
        when(usuarioRepository.findByNomeStartingWithIgnoreCase("João")).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = usuarioController.findByNomeStartingWithIgnoreCase("João");

        assertEquals(1, response.getBody().size());
        assertEquals("João Silva", response.getBody().get(0).getNome());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
