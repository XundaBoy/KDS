package app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Cidade {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	 
	    @NotBlank(message = "Nome da cidade obrigatório")
	 	private String nome;
}
