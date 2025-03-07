package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Venda {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @JsonIgnoreProperties("vendasUsuario")
	    @ManyToOne
	    @JoinColumn(name = "usuario_vendedor_id", nullable = false)
	    private Usuario usuarioVendedor;

	    @JsonIgnoreProperties("comprasUsuario")
	    @ManyToOne
	    @JoinColumn(name = "usuario_comprador_id", nullable = false)
	    private Usuario usuarioComprador;

	    @ManyToOne
	    @JsonIgnoreProperties("vendas")
	    @JoinColumn(name = "jogo_id", nullable = false)
	    private Jogo jogo;
}
