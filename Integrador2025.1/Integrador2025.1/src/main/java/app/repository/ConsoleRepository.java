package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Console;

public interface ConsoleRepository extends JpaRepository<Console, Long>{
	public List<Console> findByNomeStartingWithIgnoreCase(String nome);
	
	public List<Console> findByMarcaContainingIgnoreCase(String marca);

}
