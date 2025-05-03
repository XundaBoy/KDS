package app.service;

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
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.entity.Console;
import app.entity.Jogo;
import app.entity.Troca;
import app.entity.Usuario;
import app.repository.JogoRepository;
import app.repository.TrocaRepository;
import app.repository.UsuarioRepository;

@SpringBootTest
public class TrocaServiceTest {

    @Autowired
    private TrocaService trocaService;

    @MockitoBean
    private TrocaRepository trocaRepository;
    
    @MockitoBean
    private JogoRepository jogoRepository;
    
    @MockitoBean
    private UsuarioRepository usuarioRepository;


    @Autowired
    private JogoService jogoService;

    @Autowired
    private UsuarioService usuarioService;

    private Jogo jogoX;
    private Jogo jogoY;
    private Usuario usuarioX;
    private Usuario usuarioY;

    @BeforeEach
    void setup() {
   
        jogoX = new Jogo();
        jogoX.setId(1L);
        jogoX.setNome("Jogo X");

        jogoY = new Jogo();
        jogoY.setId(2L);
        jogoY.setNome("Jogo Y");

        usuarioX = new Usuario();
        usuarioX.setId(1L);
        usuarioX.setNome("Usuario X");

        usuarioY = new Usuario();
        usuarioY.setId(2L);
        usuarioY.setNome("Usuario Y");

    }



    @Test
    @DisplayName("Cenário 01 - Teste realizarTroca() com dados válidos")
    void cenario01() {
        when(jogoRepository.findById(1L)).thenReturn(Optional.of(jogoX));
        when(jogoRepository.findById(2L)).thenReturn(Optional.of(jogoY));

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioX));
        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(usuarioY));

        String resultado = trocaService.realizarTroca(1L, 2L, 1L, 2L);

        assertEquals("A troca foi realizada: O jogo 'Jogo X' foi trocado para o usuário 'Usuario Y', e o jogo 'Jogo Y' foi trocado para o usuário 'Usuario X'.", resultado);
        verify(trocaRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Cenário 02 - Teste de integração - delete() com ID válido")
    void cenario02() {

        doNothing().when(trocaRepository).deleteById(1L);


        String response = trocaService.delete(1L);


        assertEquals("Troca deletada com sucesso!", response);



    }

    @Test
    @DisplayName("Cenário 03 - Teste de integração - findById() com ID válido")
    void cenario03() {

        when(trocaRepository.findById(1L)).thenReturn(Optional.of(new Troca()));

   
        Optional<Troca> response = trocaService.findById(1L);

      
        assertEquals(true, response.isPresent());
    }

    @Test
    @DisplayName("Cenário 04 - Teste de integração - findAll() com trocas cadastradas")
    void cenario04() {
 
        List<Troca> listaMock = new ArrayList<>();
        listaMock.add(new Troca());


        when(trocaRepository.findAll()).thenReturn(listaMock);


        List<Troca> trocas = trocaService.findAll();

       
        assertEquals(1, trocas.size());
    }

    @Test
    @DisplayName("Cenário 05 - Teste de integração - findByConsole() com console válido")
    void cenario05() {
        Console console = new Console();
        console.setId(1L);
        console.setNome("Console Teste");


        List<Troca> listaMock = new ArrayList<>();
        listaMock.add(new Troca());

        
        when(trocaRepository.findByJogosUsuarioXConsoleOrJogosUsuarioYConsole(console, console)).thenReturn(listaMock);


        List<Troca> trocas = trocaService.findByConsole(console);

      
        assertEquals(1, trocas.size());
    }
}
