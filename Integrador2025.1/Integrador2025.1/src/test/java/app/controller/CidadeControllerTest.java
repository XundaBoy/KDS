package app.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.entity.Cidade;
import app.repository.CidadeRepository;
import app.service.CidadeService;

@SpringBootTest
public class CidadeControllerTest {

    @Autowired
    private CidadeController cidadeController;

    @Autowired
    private CidadeService cidadeService;

    @MockitoBean  // Mocking CidadeRepository
    private CidadeRepository cidadeRepository;

    private Cidade cidade;

    @BeforeEach
    void setup() {
        cidade = new Cidade();
        cidade.setId(1L);
        cidade.setNome("Cidade Teste");

        // Mockando o comportamento do Repository
        when(cidadeRepository.save(any(Cidade.class))).thenReturn(cidade);
        when(cidadeRepository.findById(1L)).thenReturn(Optional.of(cidade));
        when(cidadeRepository.findAll()).thenReturn(List.of(cidade));
        when(cidadeRepository.findByNomeStartingWithIgnoreCase("C")).thenReturn(List.of(cidade));
        when(cidadeRepository.findByNomeIgnoreCase("cidade")).thenReturn(List.of(cidade));
        doNothing().when(cidadeRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Cenário 01 - Teste de integração - save() com dados válidos")
    void cenario01() {
        ResponseEntity<String> response = cidadeController.save(cidade);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cidade salva com sucesso!", response.getBody());
    }

    @Test
    @DisplayName("Cenário 02 - Teste de integração - update() com dados válidos")
    void cenario02() {
        ResponseEntity<String> response = cidadeController.update(cidade, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cidade atualizado com sucesso", response.getBody());
    }

    @Test
    @DisplayName("Cenário 03 - Teste de integração - delete() com ID válido")
    void cenario03() {
        ResponseEntity<String> response = cidadeController.deleteCidade(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cidade deletada com sucesso!", response.getBody());
    }

    @Test
    @DisplayName("Cenário 04 - Teste de integração - findAll() com cidades cadastradas")
    void cenario04() {
        ResponseEntity<List<Cidade>> response = cidadeController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Cidade Teste", response.getBody().get(0).getNome());
    }

    @Test
    @DisplayName("Cenário 05 - Teste de integração - findById() com ID válido")
    void cenario05() {
        ResponseEntity<Cidade> response = cidadeController.getCidadeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cidade Teste", response.getBody().getNome());
    }

    @Test
    @DisplayName("Cenário 06 - Teste de integração - findByNomeStartingWith() com nome válido")
    void cenario06() {
        ResponseEntity<List<Cidade>> response = cidadeController.findByNomeStartingWith("C");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Cidade Teste", response.getBody().get(0).getNome());
    }

    @Test
    @DisplayName("Cenário 07 - Teste de integração - findByNomeIgnoreCase() com nome válido")
    void cenario07() {
        ResponseEntity<List<Cidade>> response = cidadeController.findByNomeIgnoreCase("cidade");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Cidade Teste", response.getBody().get(0).getNome());
    }
}
