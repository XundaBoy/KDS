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

    

    
    private String estadoJogo;

   
    private Float valor; 

    @JsonIgnoreProperties("jogos")
    @ManyToOne
    @NotNull
    private Usuario usuario;
    //aaaaaaaa
    @JsonIgnoreProperties("console")
    @ManyToOne
    @NotNull
    private Console console;
    
//<<<<<<< HEAD
    /*@ManyToMany(mappedBy = "jogosUsuarioX")
=======
    @JsonIgnoreProperties("jogosUsuarioX") 
    @ManyToMany(mappedBy = "jogosUsuarioX")
>>>>>>> branch 'master' of https://github.com/XundaBoy/KDS.git
    private List<Troca> trocasUsuarioX;
    
    @JsonIgnoreProperties("jogosUsuarioY")
    @ManyToMany(mappedBy = "jogosUsuarioY")
    private List<Troca> trocasUsuarioY;

    @JsonIgnoreProperties("jogo")
    @OneToMany(mappedBy = "jogo")
    private List<Venda> vendas;
    */
}

	
