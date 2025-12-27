package app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario remetente;

    @ManyToOne(optional = false)
    private Usuario destinatario;

    @Column(nullable = false)
    private String conteudo;

    private LocalDateTime enviadaEm;

    private boolean lida = false;

    @PrePersist
    protected void onCreate(){
        enviadaEm = LocalDateTime.now();
    }
}
