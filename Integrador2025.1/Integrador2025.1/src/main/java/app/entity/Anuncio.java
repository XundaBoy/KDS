package app.entity;

import java.time.LocalDateTime;

import app.entity.enums.StatusAnuncio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Usuário que está anunciando (dono do jogo)
    @ManyToOne(optional = false)
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;


    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusAnuncio status = StatusAnuncio.ATIVO;

    private LocalDateTime criadoEm;
    
    @PrePersist
    public void prePersist(){
        this.criadoEm = LocalDateTime.now();
    }
}
