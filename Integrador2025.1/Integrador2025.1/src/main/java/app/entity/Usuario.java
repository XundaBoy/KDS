package app.entity;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @NotBlank(message = "O nome do usuario eh obrgatorio")
    @Pattern(regexp = "^(\\S+\\s+\\S+.*)$", message = "O nome deve conter pelo menos nome e sobrenome separados por espaço.")
    private String nome;

    private String telefone;

    @CPF(message = "O CPF deve ser valido")
    @NotBlank
    private String cpf;

    @Email(message = "O Email deve ser valido")
    private String email;
   
    @NotBlank (message = "Senha obrigatoria")
    private String senha;

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

   /*@JsonManagedReference
    @OneToMany(mappedBy = "usuarioX")
    private List<Troca> trocasUsuarioX;

    @JsonManagedReference
    @OneToMany(mappedBy = "usuarioY")
    private List<Troca> trocasUsuarioY;

    @JsonIgnoreProperties("usuarioVendedor")
    @OneToMany(mappedBy = "usuarioVendedor")
    private List<Venda> vendasUsuario;

    @JsonIgnoreProperties("usuarioComprador")
    @OneToMany(mappedBy = "usuarioComprador")
    private List<Venda> comprasUsuario;*/
}
