package app.entity;



import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario{
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Email
	    private String email;

	    @Column(unique = true)
	    private String username;

	    @JsonIgnore
	    private String password;
	    private String role;
	    private Boolean connected = false;

	    @NotBlank
	    private String nome;

	    private String telefone;

	    @CPF @NotBlank
	    private String cpf;

	    @ManyToOne
	    @JsonIgnoreProperties({"usuarios"})
	    private Cidade cidade;

	    @ManyToOne
	    @JsonIgnoreProperties({"usuarios"})
	    private Ranking ranking;

	    @JsonIgnoreProperties("usuario")
	    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	    private List<Jogo> jogos;

	    @JsonIgnore
	    @OneToMany(mappedBy = "usuarioA")
	    private List<Troca> trocasComoA;

	    @JsonIgnore
	    @OneToMany(mappedBy = "usuarioB")
	    private List<Troca> trocasComoB;

		



}