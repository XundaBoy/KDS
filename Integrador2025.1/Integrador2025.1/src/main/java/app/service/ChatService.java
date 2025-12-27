package app.service;

import app.dto.ChatMessageResponseDTO;
import app.entity.ChatMessage;
import app.entity.Usuario;
import app.repository.ChatMessageRepository;
import app.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatRepo;
    private final UsuarioRepository usuarioRepo;

    public ChatService(ChatMessageRepository chatRepo, UsuarioRepository usuarioRepo) {
        this.chatRepo = chatRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public ChatMessageResponseDTO enviarMensagem(Long remetenteId, Long destinatarioId, String conteudo){
        var r = usuarioRepo.findById(remetenteId)
                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));
        var d = usuarioRepo.findById(destinatarioId)
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        ChatMessage msg = new ChatMessage();
        msg.setRemetente(r);
        msg.setDestinatario(d);
        msg.setConteudo(conteudo);

        ChatMessage saved = chatRepo.save(msg);
        return toDTO(saved);
    }


    public List<ChatMessageResponseDTO> historico(Long u1, Long u2){
        return chatRepo
                .findByRemetenteIdAndDestinatarioIdOrRemetenteIdAndDestinatarioIdOrderByEnviadaEm(u1, u2, u2, u1)
                .stream().map(this::toDTO).toList();
    }

    public void marcarComoLida(Long id){
        var msg = chatRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));
        msg.setLida(true);
        chatRepo.save(msg);
    }

    
    private ChatMessageResponseDTO toDTO(ChatMessage msg){
        return new ChatMessageResponseDTO(
            msg.getId(),
            msg.getRemetente().getId(),
            msg.getRemetente().getNome(),
            msg.getDestinatario().getId(),
            msg.getDestinatario().getNome(),
            msg.getConteudo(),
            msg.getEnviadaEm(),
            msg.isLida()
        );
    }
}
