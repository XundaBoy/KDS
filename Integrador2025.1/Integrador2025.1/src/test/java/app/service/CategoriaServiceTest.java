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

import app.entity.Categoria;
import app.repository.CategoriaRepository;


@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @MockitoBean  
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    void setup() {
        categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("Categoria 1");

 
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        doNothing().when(categoriaRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Cenário 01 - Teste de integração - save() com dados válidos")
    void cenario01() {
       
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        String response = categoriaService.save(categoria);

        assertEquals("Categoria salva com sucesso!", response);
    }

    @Test
    @DisplayName("Cenário 02 - Teste de integração - update() com dados válidos")
    void cenario02() {
  
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        String response = categoriaService.update(categoria, 1L);

        assertEquals("Categoria atualizada com sucesso!", response);
    }

    @Test
    @DisplayName("Cenário 03 - Teste de integração - delete() com ID válido")
    void cenario03() {
  
        doNothing().when(categoriaRepository).deleteById(1L);

        String response = categoriaService.delete(1L);

        assertEquals("Categoria deletada com sucesso!", response);
    }

    @Test
    @DisplayName("Cenário 04 - Teste de integração - findAll() com categorias cadastradas")
    void cenario04() {
     
        List<Categoria> listaMock = new ArrayList<>();
        listaMock.add(categoria);

        when(categoriaRepository.findAll()).thenReturn(listaMock);

        List<Categoria> categorias = categoriaService.findAll();

        assertEquals(1, categorias.size());
        assertEquals("Categoria 1", categorias.get(0).getNome());
    }

    @Test
    @DisplayName("Cenário 05 - Teste de integração - findById() com ID válido")
    void cenario05() {
    
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria result = categoriaService.findById(1L);

        assertEquals("Categoria 1", result.getNome());
    }

    @Test
    @DisplayName("Cenário 06 - Teste de integração - findById() com ID não encontrado")
    void cenario06() {

        when(categoriaRepository.findById(999L)).thenReturn(Optional.empty());

        Categoria result = categoriaService.findById(999L);

        assertEquals(null, result);
    }
}
