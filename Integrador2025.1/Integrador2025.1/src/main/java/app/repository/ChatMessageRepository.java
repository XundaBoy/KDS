package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.ChatMessage;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRemetenteIdAndDestinatarioIdOrderByEnviadaEm(Long remetente, Long destinatario);

    List<ChatMessage> findByRemetenteIdAndDestinatarioIdOrRemetenteIdAndDestinatarioIdOrderByEnviadaEm(
        Long remetente, Long destinatario,
        Long destinatario2, Long remetente2
    );
}
