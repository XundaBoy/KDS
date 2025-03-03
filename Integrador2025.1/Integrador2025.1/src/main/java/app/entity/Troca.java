package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


	    @ManyToOne
	    private Jogo jogoUsuarioX;

	    @ManyToOne
	    private Jogo jogoUsuarioY;

	    @ManyToOne
	    private Usuario usuarioX;

	    @ManyToOne
	    private Usuario usuarioY;
}
