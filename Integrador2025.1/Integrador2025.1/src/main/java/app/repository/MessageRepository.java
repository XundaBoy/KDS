package app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Buscar mensagens do chat da troca (chat da troca)
    List<Message> findByTrocaIdOrderByTimestampAsc(Long trocaId);

}
