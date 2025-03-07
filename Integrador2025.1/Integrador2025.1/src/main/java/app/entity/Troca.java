package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Troca {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 
	    @ManyToMany
	    private List<Jogo> jogosUsuarioX;

	    @ManyToMany
	    private List<Jogo> jogosUsuarioY;
	    
	    @JsonIgnoreProperties("trocasUsuarioX")
	    @ManyToOne
	    private Usuario usuarioX;

	    @JsonIgnoreProperties("trocasUsuarioY")
	    @ManyToOne
	    private Usuario usuarioY;
}
