package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Usuario;
import jakarta.transaction.Transactional;



public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
	
	public List<app.entity.Usuario> findByNomeStartingWithIgnoreCase(String nome);
	
	public boolean existsByCpf(String cpf);
	
	Usuario findByUsername(String username);

    @Transactional
    Long deleteByUsername(String username);
}
