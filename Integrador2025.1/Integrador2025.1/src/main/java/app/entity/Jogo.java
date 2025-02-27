package app.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome do jogo obrigatório")
    private String nome;

    @NotBlank(message = "Console do jogo obrigatório")
    private String console;

    @Enumerated(EnumType.STRING)
    private EstadoJogo estado; 

    private Float valor; 

    private boolean venda;
    private boolean troca;

    @ManyToOne
    private Usuario usuario;

   
    
}

	
