package app.entity;



import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome do  usuario eh obrgatorio")
	@Pattern(regexp = "^(\\S+\\s+\\S+.*)$")
	private String nome;
	
	@NotBlank(message = "O  telefone deve ser preenchido")
	private String telefone;
	
	@CPF(message = "O CPF deve ser valido")
	@NotBlank 
	private String cpf;
	
	@Email(message = "O Email deve ser valido")
	private String email;
	
	
}
