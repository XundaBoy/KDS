package app.entity;

import java.time.LocalDateTime;

import app.entity.enums.StatusTroca;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Troca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quem está propondo
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuarioa_id") 
    private Usuario usuarioA;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuariob_id") 
    private Usuario usuarioB;

    // Jogo do usuário A
    @ManyToOne(optional = false)
    @JoinColumn(name = "jogo_x_id")
    private Jogo jogoX;

    // Jogo do usuário B
    @ManyToOne(optional = false)
    @JoinColumn(name = "jogo_y_id")
    private Jogo jogoY;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTroca status = StatusTroca.SOLICITADA;

    @Column(nullable = false)
    private boolean confirmadaPorUsuarioA = false;

    @Column(nullable = false)
    private boolean confirmadaPorUsuarioB = false;

    @Column(nullable = false)
    private LocalDateTime criadaEm;

    private LocalDateTime concluidaEm;

    @PrePersist
    public void prePersist(){
        this.criadaEm = LocalDateTime.now();
        this.status = StatusTroca.SOLICITADA;
    }
}
