package app.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Troca {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Jogo jogoX;
	@OneToOne
	private Jogo jogoY;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuarioX;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuarioY;
	
	
	
	
	
}
