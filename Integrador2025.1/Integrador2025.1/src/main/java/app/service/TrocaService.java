package app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Jogo;
import app.entity.Troca;
import app.entity.Usuario;
import app.entity.enums.StatusTroca;
import app.repository.JogoRepository;
import app.repository.TrocaRepository;
import app.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class TrocaService {

@Autowired
private TrocaRepository trocaRepository;

@Autowired
private UsuarioRepository usuarioRepository;

private JogoRepository jogoRepository;

	@Transactional
	public Troca solicitarTroca(
			Long usuarioAId,
			Long usuarioBId,
			Long jogoXId,
			Long jogoYId) 
	{
		Usuario usuarioA = usuarioRepository.findById(usuarioAId)
				.orElseThrow(() -> new IllegalArgumentException("Usuario A não encontrado"));
		
		Usuario usuarioB = usuarioRepository.findById(usuarioBId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário B não encontrado"));

        Jogo jogoX = jogoRepository.findById(jogoXId)
                .orElseThrow(() -> new IllegalArgumentException("Jogo X não encontrado"));

        Jogo jogoY = jogoRepository.findById(jogoYId)
                .orElseThrow(() -> new IllegalArgumentException("Jogo Y não encontrado"));
        
        validarJogosDisponiveis(jogoX, jogoY);
        
        Troca troca = new Troca();
        troca.setUsuarioA(usuarioA);
        troca.setUsuarioB(usuarioB);
        troca.setJogoX(jogoX);
        troca.setJogoY(jogoY);
        troca.setStatus(StatusTroca.SOLICITADA);

        return trocaRepository.save(troca);
       
	}
	
	@Transactional
	public void cancelarTroca(Long trocaId, Long usuarioId) {
		Troca troca = trocaRepository.findById(trocaId) 
				.orElseThrow(() -> new IllegalArgumentException("Troca não encontrada")); 
				
		validarParticipante(troca, usuarioId); 
		troca.setStatus(StatusTroca.CANCELADA);
	}
	
	public void concluirTroca(Troca troca) {
		troca.setStatus(StatusTroca.CONCLUIDA);
		troca.setConcluidaEm(LocalDateTime.now());
	}
	
	
	private void validarJogosDisponiveis(Jogo jogoX, Jogo jogoY) {
		if(Stream.of(jogoX, jogoY)
			.anyMatch(Jogo::estaEmTroca)){
				throw new IllegalStateException("Um dos jogos ja esta em troca");
			}
	}

	private void validarParticipante(Troca troca, Long usuarioId) {
		boolean participante = 
				troca.getUsuarioA().getId().equals(usuarioId) ||
                troca.getUsuarioB().getId().equals(usuarioId);
		
		if (!participante) {
            throw new IllegalArgumentException("Usuário não participa desta troca");
        }
	}

}
