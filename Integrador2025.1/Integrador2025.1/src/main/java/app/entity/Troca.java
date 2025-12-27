package app.entity;



import java.time.LocalDateTime;

import app.entity.enums.StatusTroca;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
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
	
	@ManyToOne(optional = false)
	private Usuario usuarioA;
	
	@ManyToOne(optional = false)
	private Usuario usuarioB;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "jogo_y_id", unique = true)
	private Jogo jogoX;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "jogo_x_id", unique = true)
	private Jogo jogoY;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusTroca status;
	
	// Confirmações

    @Column(nullable = false)
    private boolean confirmadaPorUsuarioA = false;
	

    @Column(nullable = false)
    private boolean confirmadaPorUsuarioB = false;
	
	@Column(nullable = false)
	private LocalDateTime criadaEm;
	
	private LocalDateTime concluidaEm;
	
	public void prePersist() {
		this.criadaEm = LocalDateTime.now();
		this.status = StatusTroca.SOLICITADA;
	}
	
}
