package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
import app.entity.Usuario;
import app.entity.Venda;
import app.repository.VendaRepository;
import app.service.JogoService;
import app.service.UsuarioService;
import app.service.VendaService;

@SpringBootTest
public class VendaServiceTest {

    @Autowired
    private VendaService vendaService;

    @MockitoBean
    private VendaRepository vendaRepository;

    @MockitoBean
    private JogoService jogoService;

    @MockitoBean
    private UsuarioService usuarioService;

    private Venda venda;
    private Jogo jogo;
    private Usuario usuarioVendedor;
    private Usuario usuarioComprador;
    private Console console;

    @BeforeEach
    void setup() {
        console = new Console();
        console.setId(1L);
        console.setNome("PS5");
        console.setMarca("Sony");

      
        usuarioVendedor = new Usuario();
        usuarioVendedor.setId(1L);
        usuarioVendedor.setNome("Vendedor");

 
        usuarioComprador = new Usuario();
        usuarioComprador.setId(2L);
        usuarioComprador.setNome("Comprador");

     
        jogo = new Jogo();
        jogo.setId(1L);
        jogo.setNome("FIFA 22");
        jogo.setConsole(console);
        jogo.setUsuario(usuarioVendedor);  
        jogo.setValor(200.0f);

       
        venda = new Venda();
        venda.setId(1L);
        venda.setJogo(jogo);
        venda.setUsuarioVendedor(usuarioVendedor);
        venda.setUsuarioComprador(usuarioComprador);

   
        when(jogoService.findById(1L)).thenReturn(Optional.of(jogo));
        when(usuarioService.findById(1L)).thenReturn(Optional.of(usuarioVendedor));
        when(usuarioService.findById(2L)).thenReturn(Optional.of(usuarioComprador));
        when(vendaRepository.save(any(Venda.class))).thenReturn(venda);
        when(vendaRepository.findById(1L)).thenReturn(Optional.of(venda));
        when(vendaRepository.findAll()).thenReturn(List.of(venda));
        when(vendaService.findByJogo(jogo)).thenReturn(List.of(venda));
        when(vendaService.findByJogo_Console(console)).thenReturn(List.of(venda));

 
        doNothing().when(vendaRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Cenário 01 - Teste de integração - realizarVenda() com dados válidos")
    void realizarVendaCenario01() {
        String response = vendaService.realizarVenda(1L, 1L, 2L);
        assertEquals("Venda realizada com sucesso: O jogo 'FIFA 22' foi vendido para 'Comprador' por 'Vendedor' no valor de R$200,00.", response);
    }

    @Test
    @DisplayName("Cenário 02 - Teste de integração - findById() com ID válido")
    void findByIdCenario02() {
        Optional<Venda> result = vendaService.findById(1L);
        assertEquals("FIFA 22", result.get().getJogo().getNome());
    }

    @Test
    @DisplayName("Cenário 03 - Teste de integração - findAll() com vendas cadastradas")
    void findAllCenario03() {
        List<Venda> vendas = vendaService.findAll();
        assertEquals(1, vendas.size());
        assertEquals("FIFA 22", vendas.get(0).getJogo().getNome());
    }

    @Test
    @DisplayName("Cenário 04 - Teste de integração - delete() com ID válido")
    void deleteCenario04() {
        String resposta = vendaService.delete(1L);
        assertEquals("Usuario deletado com sucesso!", resposta);
    }
}
