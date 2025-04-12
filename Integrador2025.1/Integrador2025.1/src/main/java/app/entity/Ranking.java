package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Ranking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    @NotBlank(message = "Nome do ranking obrigatório")
 	private String nome;
    
    @OneToMany(mappedBy = "ranking", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("ranking")
    private List<Usuario> usuarios;
}
