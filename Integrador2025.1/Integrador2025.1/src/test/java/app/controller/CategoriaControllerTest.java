package app.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.entity.Categoria;
import app.repository.CategoriaRepository;
import app.service.CategoriaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoriaControllerTest {

    @Autowired
    private CategoriaController categoriaController;

    @MockitoBean
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaService categoriaService;

    @BeforeEach
    public void setup() {
        List<Categoria> categorias = new ArrayList<>();
        
        Categoria categoria1 = new Categoria();
        categoria1.setId(1L);
        categoria1.setNome("Aventura");
        
        Categoria categoria2 = new Categoria();
        categoria2.setId(2L);
        categoria2.setNome("RPG");
        
        categorias.add(categoria1);
        categorias.add(categoria2);

        // Mock do repositório
        when(categoriaRepository.findAll()).thenReturn(categorias);
        when(categoriaRepository.save(categoria1)).thenReturn(categoria1);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria1));
        when(categoriaRepository.findById(999L)).thenReturn(Optional.empty());
        doNothing().when(categoriaRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Cenário 01 - Teste de integração - findAll()")
    void cenario01() {
        ResponseEntity<List<Categoria>> response = categoriaController.findAll();
        
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 02 - Teste de integração - findById() (sucesso)")
    void cenario02() {
        ResponseEntity<Categoria> response = categoriaController.getCidadeById(1L);
        
        assertEquals(1L, response.getBody().getId());
        assertEquals("Aventura", response.getBody().getNome());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 03 - Teste de integração - findById() (categoria não encontrada)")
    void cenario03() {
        
        when(categoriaRepository.findById(999L)).thenReturn(Optional.empty());

        
        ResponseEntity<Categoria> response = categoriaController.getCidadeById(999L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
    }


    @Test
    @DisplayName("Cenário 04 - Teste de integração - save()")
    void cenario04() {
        Categoria categoria = new Categoria();
        categoria.setNome("Estratégia");
        
        ResponseEntity<String> response = categoriaController.save(categoria);
        
        assertEquals("Categoria salva com sucesso!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 05 - Teste de integração - update()")
    void cenario05() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNome("RPG Avançado");
        
        ResponseEntity<String> response = categoriaController.update(categoria, 1L);
        
        assertEquals("Categoria atualizada com sucesso!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário 06 - Teste de integração - delete()")
    void cenario06() {
        ResponseEntity<String> response = categoriaController.deleteCidade(1L);
        
        assertEquals("Categoria deletada com sucesso!", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
