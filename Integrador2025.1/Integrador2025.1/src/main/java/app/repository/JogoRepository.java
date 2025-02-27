package app.repository;

import java.io.Console;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.EstadoJogo;
import app.entity.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	public List<Jogo> findByNomeContaining(String nome);
	
	public List<Jogo> findByConsole(Console console);
	
	 
	 
}
