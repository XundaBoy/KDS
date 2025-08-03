package app.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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



    @NotBlank(message = "Estado do jogo obrigatório")
    private String estadoJogo;

    @NotNull
    @DecimalMin("0.0") // ou o valor mínimo que você desejar
    private Float valor;


    @JsonIgnoreProperties("jogos")
    @ManyToOne
    @NotNull
    private Usuario usuario;
    //aaaaaaaa
    @JsonIgnoreProperties({"jogos", "console"})  // Evitar recursão ao serializar o console
    @ManyToOne
    @NotNull
    private Console console;
    
    
    
    @OneToOne(mappedBy = "jogoX")
    private Troca trocaComoX;
    
    @OneToOne(mappedBy = "jogoY")
    private Troca trocaComoY;
    
    

}

	
