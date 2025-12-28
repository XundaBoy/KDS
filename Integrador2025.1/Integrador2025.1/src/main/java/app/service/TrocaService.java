package app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.SolicitarTrocaDTO;
import app.dto.TrocaResponseDTO;
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

@Autowired
private JogoRepository jogoRepository;

@Transactional
public TrocaResponseDTO solicitarTroca(Long usuarioAId, SolicitarTrocaDTO dto) {
	
	if (usuarioAId.equals(dto.usuarioBId())) {
	    throw new IllegalArgumentException("Você não pode trocar consigo mesmo.");
	}

    Usuario usuarioA = usuarioRepository.findById(usuarioAId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário A não encontrado"));

    Usuario usuarioB = usuarioRepository.findById(dto.usuarioBId())
            .orElseThrow(() -> new IllegalArgumentException("Usuário B não encontrado"));

    Jogo jogoX = jogoRepository.findById(dto.jogoXId())
            .orElseThrow(() -> new IllegalArgumentException("Jogo X não encontrado"));

    Jogo jogoY = jogoRepository.findById(dto.jogoYId())
            .orElseThrow(() -> new IllegalArgumentException("Jogo Y não encontrado"));
    
    
    List<StatusTroca> ativos = List.of(StatusTroca.ACEITA, StatusTroca.SOLICITADA);

    boolean jogosEmTroca = trocaRepository
            .existsByJogoXOrJogoYAndStatusIn(jogoX, jogoY, ativos);

    if (jogosEmTroca) {
        throw new IllegalStateException("Um dos jogos já está em uma troca ativa.");
    }
    

    validarJogosDisponiveis(jogoX, jogoY);

    Troca troca = new Troca();
    troca.setUsuarioA(usuarioA);
    troca.setUsuarioB(usuarioB);
    troca.setJogoX(jogoX);
    troca.setJogoY(jogoY);
    troca.setStatus(StatusTroca.SOLICITADA);
    troca.setCriadaEm(LocalDateTime.now());

    Troca salva = trocaRepository.save(troca);

    return toDTO(salva);
}
	
		@Transactional
		public void cancelarTroca(Long trocaId, Long usuarioId) {
		    Troca troca = trocaRepository.findById(trocaId)
		            .orElseThrow(() -> new IllegalArgumentException("Troca não encontrada"));
		
		    validarParticipante(troca, usuarioId);
		    
		    if(troca.getStatus() == StatusTroca.CONCLUIDA){
		        throw new IllegalStateException("Não é possível cancelar uma troca já concluída.");
		    }

		    if(troca.getStatus() == StatusTroca.CANCELADA){
		        throw new IllegalStateException("Esta troca já foi cancelada anteriormente.");
		    }
		
		    troca.setStatus(StatusTroca.CANCELADA);
		    trocaRepository.save(troca);
		}
	
	 @Transactional
	    public TrocaResponseDTO confirmarTroca(Long trocaId, Long usuarioId) {

	        Troca troca = trocaRepository.findById(trocaId)
	                .orElseThrow(() -> new IllegalArgumentException("Troca não encontrada"));
	        
	        validarParticipante(troca, usuarioId);
	        
	        if (troca.getStatus() == StatusTroca.CANCELADA) {
	        	   throw new IllegalStateException("Troca cancelada, não pode confirmar.");
	        	}


	        if (usuarioId.equals(troca.getUsuarioA().getId())) {
	            troca.setConfirmadaPorUsuarioA(true);
	        } else if (usuarioId.equals(troca.getUsuarioB().getId())) {
	            troca.setConfirmadaPorUsuarioB(true);
	        }

	        // 🚀 Se ambos confirmaram, a troca está concluída
	        if (troca.isConfirmadaPorUsuarioA() && troca.isConfirmadaPorUsuarioB()) {
	            troca.setStatus(StatusTroca.CONCLUIDA);
	            troca.setConcluidaEm(LocalDateTime.now());
	            
	            jogoRepository.delete(troca.getJogoX());
		        jogoRepository.delete(troca.getJogoY());
	        } else {
	            troca.setStatus(StatusTroca.ACEITA);
	        }
	        
	        

	        return toDTO(trocaRepository.save(troca));
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
	
	public TrocaResponseDTO toDTO(Troca troca){
	    return new TrocaResponseDTO(
	            troca.getId(),
	            troca.getUsuarioA().getId(),
	            troca.getUsuarioB().getId(),
	            troca.getJogoX().getId(),
	            troca.getJogoY().getId(),
	            troca.getStatus()
	    );
	}
	
	public List<Troca> findAll() {
	    return trocaRepository.findAll();
	}

	public Troca findById(Long id) {
	    return trocaRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Troca não encontrada"));
	}



}
