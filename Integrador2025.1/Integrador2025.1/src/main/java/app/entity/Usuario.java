package app.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Usuario implements UserDetails{
	
	//private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message = "O Email deve ser valido")
	private String email;
	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "connected", nullable = false)
	
	
	private Boolean connected = false;
	private String password;
	private String role;


    @NotBlank(message = "O nome do usuario eh obrgatorio")
    @Pattern(regexp = "^(\\S+\\s+\\S+.*)$", message = "O nome deve conter pelo menos nome e sobrenome separados por espaço.")
    private String nome;

    private String telefone;

    @CPF(message = "O CPF deve ser valido")
    @NotBlank
    private String cpf;
   
  
    private String statusCadastro;

    @JsonIgnoreProperties("usuarios")
    @ManyToOne
    private Cidade cidade;

    @JsonIgnoreProperties("usuarios")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ranking ranking;


    @JsonIgnoreProperties("usuario")
    @OneToMany(mappedBy = "usuario")
    private List<Jogo> jogos;

   
	
    
    @JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(this.role));
	    return authorities;
	}
	
	@OneToMany(mappedBy = "usuarioX")
	private List<Troca> trocasComoX;
	
	@OneToMany(mappedBy = "usuarioY")
	private List<Troca> trocasComoY;

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	



}