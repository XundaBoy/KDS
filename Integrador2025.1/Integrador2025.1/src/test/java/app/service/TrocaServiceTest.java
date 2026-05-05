package app.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import app.dto.SolicitarTrocaDTO;
import app.dto.TrocaResponseDTO;
import app.entity.Jogo;
import app.entity.Troca;
import app.entity.Usuario;
import app.entity.enums.StatusTroca;
import app.repository.JogoRepository;
import app.repository.TrocaRepository;
import app.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class TrocaServiceTest {

	@Mock
	private TrocaRepository trocaRepository;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private JogoRepository jogoRepository;
	
	@InjectMocks
	private TrocaService trocaService;
	
	@Test
	void deveSolicitarTroca() {
		Usuario usuarioA = criarUsuario(1L);
		Usuario usuarioB = criarUsuario(2L);
		
		Jogo jogoX = criarJogo(10L, usuarioA, true);
		Jogo jogoY = criarJogo(20L, usuarioB, true);
		
		SolicitarTrocaDTO dto = new SolicitarTrocaDTO(
				usuarioB.getId(),
				jogoX.getId(),
				jogoY.getId()
		);
		
		when(usuarioRepository.findById(usuarioA.getId())).thenReturn(Optional.of(usuarioA));
		when(usuarioRepository.findById(usuarioB.getId())).thenReturn(Optional.of(usuarioB));
		when(jogoRepository.findById(jogoX.getId())).thenReturn(Optional.of(jogoX));
		when(jogoRepository.findById(jogoY.getId())).thenReturn(Optional.of(jogoY));
		
		
        when(trocaRepository.save(any(Troca.class))).thenAnswer(invocation -> invocation.getArgument(0));
        
        TrocaResponseDTO response = trocaService.solicitarTroca(usuarioA.getId(), dto);
        
        assertEquals(usuarioA.getId(), response.usuarioAId());
        assertEquals(usuarioB.getId(), response.usuarioBId());
        assertEquals(jogoX.getId(), response.jogoXId());
        assertEquals(jogoY.getId(), response.jogoYId());
        assertEquals(StatusTroca.SOLICITADA, response.status());

        assertFalse(jogoX.isDisponivel());
        assertFalse(jogoY.isDisponivel());

        verify(jogoRepository).save(jogoX);
        verify(jogoRepository).save(jogoY);
        verify(trocaRepository).save(any(Troca.class));
	}
	
	@Test
	void naoDevePermitirTrocaConsigoMesmo() {
		Long usuarioId = 1L;
		
		SolicitarTrocaDTO dto = new SolicitarTrocaDTO(
				usuarioId,
				10L,
				20L
		);
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			trocaService.solicitarTroca(usuarioId, dto);
		});
		
		assertEquals("Você não pode trocar consigo mesmo.", exception.getMessage());
		
		verifyNoInteractions(usuarioRepository);
		verifyNoInteractions(jogoRepository);
		verifyNoInteractions(trocaRepository);
	}
	
	@Test
	void naoDevePermitirTrocaComJogoIndisponivel() {
		Usuario usuarioA = criarUsuario(1L);
		Usuario usuarioB = criarUsuario(2L);
		
		Jogo jogoX = criarJogo(10L, usuarioA, false); 
		Jogo jogoY = criarJogo(20L, usuarioB, true);
		
		SolicitarTrocaDTO dto = new SolicitarTrocaDTO(
				usuarioB.getId(),
				jogoX.getId(),
				jogoY.getId()
		);
		
		when(usuarioRepository.findById(usuarioA.getId())).thenReturn(Optional.of(usuarioA));
		when(usuarioRepository.findById(usuarioB.getId())).thenReturn(Optional.of(usuarioB));
		when(jogoRepository.findById(jogoX.getId())).thenReturn(Optional.of(jogoX));
		when(jogoRepository.findById(jogoY.getId())).thenReturn(Optional.of(jogoY));
		
		IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
			trocaService.solicitarTroca(usuarioA.getId(), dto);
		});
		
		assertEquals("Todos os jogos devem estar disponíveis para iniciar uma troca.", exception.getMessage());
		
		verify(trocaRepository, never()).save(any(Troca.class));
	}
	
	@Test
	void naoDevePermitirTrocaSeUsuarioNaoForDonoDoJogo() {
		  Usuario usuarioA = criarUsuario(1L);
		    Usuario usuarioB = criarUsuario(2L);
		    Usuario usuarioC = criarUsuario(3L);

		    Jogo jogoX = criarJogo(10L, usuarioC, true);
		    Jogo jogoY = criarJogo(20L, usuarioB, true);

		    SolicitarTrocaDTO dto = new SolicitarTrocaDTO(
		            usuarioB.getId(),
		            jogoX.getId(),
		            jogoY.getId()
		    );
		    
		    when(usuarioRepository.findById(usuarioA.getId())).thenReturn(Optional.of(usuarioA));
		    when(usuarioRepository.findById(usuarioB.getId())).thenReturn(Optional.of(usuarioB));
		    when(jogoRepository.findById(jogoX.getId())).thenReturn(Optional.of(jogoX));
		    when(jogoRepository.findById(jogoY.getId())).thenReturn(Optional.of(jogoY));
		    
		    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
		        trocaService.solicitarTroca(usuarioA.getId(), dto);
		    });
		    
		    assertEquals("Um dos jogos não pertence ao usuário informado.", exception.getMessage());
		    
		    verify(trocaRepository, never()).save(any(Troca.class));
	}
	
	@Test
	void deveCancelarTrocaComSucessoELiberarJogos() {
		 Usuario usuarioA = criarUsuario(1L);
		    Usuario usuarioB = criarUsuario(2L);

		    Jogo jogoX = criarJogo(10L, usuarioA, false);
		    Jogo jogoY = criarJogo(20L, usuarioB, false);

		    Troca troca = criarTroca(100L, usuarioA, usuarioB, jogoX, jogoY, StatusTroca.SOLICITADA);
		    
		    when(trocaRepository.findById(troca.getId())).thenReturn(Optional.of(troca));
		    
		    trocaService.cancelarTroca(troca.getId(), usuarioA.getId());
		    
		    assertEquals(StatusTroca.CANCELADA, troca.getStatus());
		    assertEquals(true, jogoX.isDisponivel());
		    assertEquals(true, jogoY.isDisponivel());
		    
		    verify(jogoRepository).save(jogoX);
		    verify(jogoRepository).save(jogoY);
		    verify(trocaRepository).save(troca);

	}
	
	@Test
	void deveConcluirTrocaQuandoOsDoisUsuariosConfirmam() {
		 Usuario usuarioA = criarUsuario(1L);
		    Usuario usuarioB = criarUsuario(2L);

		    Jogo jogoX = criarJogo(10L, usuarioA, false);
		    Jogo jogoY = criarJogo(20L, usuarioB, false);

		    Troca troca = criarTroca(100L, usuarioA, usuarioB, jogoX, jogoY, StatusTroca.ACEITA);
		    troca.setConfirmadaPorUsuarioA(true);
		    troca.setConfirmadaPorUsuarioB(false);

		    when(trocaRepository.findById(troca.getId())).thenReturn(Optional.of(troca));
		    when(trocaRepository.save(any(Troca.class))).thenAnswer(invocation -> invocation.getArgument(0));
		    

		    TrocaResponseDTO response = trocaService.confirmarTroca(troca.getId(), usuarioB.getId());

		    assertEquals(StatusTroca.CONCLUIDA, troca.getStatus());
		    assertEquals(StatusTroca.CONCLUIDA, response.status());

		    assertEquals(usuarioB, jogoX.getUsuario());
		    assertEquals(usuarioA, jogoY.getUsuario());

		    assertFalse(jogoX.isDisponivel());
		    assertFalse(jogoY.isDisponivel());

		    verify(jogoRepository).save(jogoX);
		    verify(jogoRepository).save(jogoY);
		    verify(trocaRepository).save(troca);
	}
	
	
	private Usuario criarUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		return usuario;
	}
	
	private Jogo criarJogo(Long id, Usuario usuario, boolean disponivel) {
		Jogo jogo = new Jogo();
		jogo.setId(id);
        jogo.setUsuario(usuario);
        jogo.setDisponivel(disponivel);
		return jogo;
	}
	
	private Troca criarTroca(
	        Long id,
	        Usuario usuarioA,
	        Usuario usuarioB,
	        Jogo jogoX,
	        Jogo jogoY,
	        StatusTroca status
	) {
	    Troca troca = new Troca();
	    troca.setId(id);
	    troca.setUsuarioA(usuarioA);
	    troca.setUsuarioB(usuarioB);
	    troca.setJogoX(jogoX);
	    troca.setJogoY(jogoY);
	    troca.setStatus(status);
	    return troca;
	}

	
}
