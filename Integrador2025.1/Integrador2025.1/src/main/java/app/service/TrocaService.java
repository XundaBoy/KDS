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


    // ==========================================================
    // 📌 SOLICITAR TROCA
    // ==========================================================
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

        // 🔥 Bloqueios importantes
        validarJogosDisponiveis(jogoX, jogoY);
        validarDono(usuarioAId, jogoX);
        validarDono(dto.usuarioBId(), jogoY);

        // 🔐 Jogar bloqueia os jogos para troca
        jogoX.setDisponivel(false);
        jogoY.setDisponivel(false);
        jogoRepository.save(jogoX);
        jogoRepository.save(jogoY);

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


    // ==========================================================
    // 🚫 CANCELAR TROCA
    // ==========================================================
    @Transactional
    public void cancelarTroca(Long trocaId, Long usuarioId) {
        Troca troca = trocaRepository.findById(trocaId)
                .orElseThrow(() -> new IllegalArgumentException("Troca não encontrada"));

        validarParticipante(troca, usuarioId);

        if (troca.getStatus() == StatusTroca.CONCLUIDA) {
            throw new IllegalStateException("Não é possível cancelar uma troca já concluída.");
        }

        if (troca.getStatus() == StatusTroca.CANCELADA) {
            throw new IllegalStateException("Esta troca já foi cancelada anteriormente.");
        }

        // 🔓 Jogo volta a ficar disponível
        troca.getJogoX().setDisponivel(true);
        troca.getJogoY().setDisponivel(true);

        jogoRepository.save(troca.getJogoX());
        jogoRepository.save(troca.getJogoY());

        troca.setStatus(StatusTroca.CANCELADA);
        trocaRepository.save(troca);
    }


    // ==========================================================
    // ✔️ CONFIRMAR TROCA
    // ==========================================================
    @Transactional
    public TrocaResponseDTO confirmarTroca(Long trocaId, Long usuarioId) {

        Troca troca = trocaRepository.findById(trocaId)
                .orElseThrow(() -> new IllegalArgumentException("Troca não encontrada"));

        validarParticipante(troca, usuarioId);

        if (troca.getStatus() == StatusTroca.CANCELADA) {
            throw new IllegalStateException("Troca cancelada, não pode confirmar.");
        }
        if (troca.getStatus() == StatusTroca.CONCLUIDA) {
            throw new IllegalStateException("Troca já concluída anteriormente.");
        }

        if (usuarioId.equals(troca.getUsuarioA().getId()))
            troca.setConfirmadaPorUsuarioA(true);
        else
            troca.setConfirmadaPorUsuarioB(true);

        // 🚀 Ambos confirmaram → concluir troca
        if (troca.isConfirmadaPorUsuarioA() && troca.isConfirmadaPorUsuarioB()) {

            // 🏁 Transferência de propriedade
            Usuario antigoDonoX = troca.getUsuarioA();
            Usuario antigoDonoY = troca.getUsuarioB();

            Jogo jogoX = troca.getJogoX();
            Jogo jogoY = troca.getJogoY();

            jogoX.setUsuario(antigoDonoY);
            jogoY.setUsuario(antigoDonoX);

            // 🔒 Após a troca, os jogos NÃO ficam automaticamente disponíveis
            jogoX.setDisponivel(false);
            jogoY.setDisponivel(false);

            jogoRepository.save(jogoX);
            jogoRepository.save(jogoY);

            troca.setStatus(StatusTroca.CONCLUIDA);
            troca.setConcluidaEm(LocalDateTime.now());
        } else {
            troca.setStatus(StatusTroca.ACEITA);
        }

        return toDTO(trocaRepository.save(troca));
    }


    // ==========================================================
    // ⚙️ VALIDAÇÕES
    // ==========================================================
    private void validarJogosDisponiveis(Jogo... jogos) {
        if (Stream.of(jogos).anyMatch(j -> !j.isDisponivel()))
            throw new IllegalStateException("Todos os jogos devem estar disponíveis para iniciar uma troca.");
    }

    private void validarDono(Long userId, Jogo jogo) {
        if (!jogo.getUsuario().getId().equals(userId))
            throw new IllegalArgumentException("Um dos jogos não pertence ao usuário informado.");
    }

    private void validarParticipante(Troca troca, Long usuarioId) {
        boolean participante = usuarioId.equals(troca.getUsuarioA().getId()) ||
                usuarioId.equals(troca.getUsuarioB().getId());
        if (!participante)
            throw new IllegalArgumentException("Usuário não participa desta troca");
    }


    // ==========================================================
    // 📌 DTO e BUSCAS
    // ==========================================================
    public TrocaResponseDTO toDTO(Troca troca) {
        return new TrocaResponseDTO(
                troca.getId(),
                troca.getUsuarioA().getId(),
                troca.getUsuarioB().getId(),
                troca.getJogoX().getId(),
                troca.getJogoY().getId(),
                troca.getStatus()
        );
    }

    public List<Troca> findAll() { return trocaRepository.findAll(); }

    public Troca findById(Long id) {
        return trocaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Troca não encontrada"));
    }
}
