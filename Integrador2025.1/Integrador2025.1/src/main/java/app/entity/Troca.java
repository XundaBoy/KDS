package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
	    @JoinTable(
	        name = "troca_jogos_usuario_x",
	        joinColumns = @JoinColumn(name = "troca_id"),
	        inverseJoinColumns = @JoinColumn(name = "jogo_id")
	    )
	    private List<Jogo> jogosUsuarioX;

	   
	 @ManyToMany
	    @JoinTable(
	        name = "troca_jogos_usuario_y",
	        joinColumns = @JoinColumn(name = "troca_id"),
	        inverseJoinColumns = @JoinColumn(name = "jogo_id")
	    )
	    private List<Jogo> jogosUsuarioY;
	    
	    @JsonBackReference
	    @ManyToOne
	    private Usuario usuarioX;

	    @JsonBackReference
	    @ManyToOne
	    private Usuario usuarioY;
}