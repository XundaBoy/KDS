package app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do jogo obrigatório")
    private String nome;

    @NotBlank(message = "Estado do jogo obrigatório")
    private String estadoJogo;

    @NotNull
    @DecimalMin("0.0")
    private Float valor;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("jogos")
    private Usuario usuario;

    
    @Column(nullable = false)
    private boolean disponivel = true;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"jogos","console"})
    private Console console;

    @JsonIgnore
    @OneToOne(mappedBy = "jogoX")
    private Troca trocaComoX;

    @JsonIgnore
    @OneToOne(mappedBy = "jogoY")
    private Troca trocaComoY;

    public boolean estaEmTroca(){
        return trocaComoX != null || trocaComoY != null;
    }
}
