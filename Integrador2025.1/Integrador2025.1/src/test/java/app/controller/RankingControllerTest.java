package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import app.entity.Ranking;
import app.repository.RankingRepository;

@SpringBootTest
public class RankingControllerTest {

    @Autowired
    private RankingController rankingController;



    @MockitoBean
    private RankingRepository rankingRepository;

    private Ranking ranking;

    @BeforeEach
    void setup() {
    	var authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        var authentication = new UsernamePasswordAuthenticationToken("admin", null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    	
        ranking = new Ranking();
        ranking.setId(1L);
        ranking.setNome("Top 10 Games");

      
        when(rankingRepository.findAll()).thenReturn(List.of(ranking));
    }

    @Test
    @DisplayName("Cenário 01 - Teste de integração - findAll() com rankings cadastrados")
    void cenario01() {
        ResponseEntity<List<Ranking>> response = rankingController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Top 10 Games", response.getBody().get(0).getNome());
    }

   
}
