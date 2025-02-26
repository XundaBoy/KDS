package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Estado;
import app.entity.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	public List<Jogo> findByNomeContaining(String nome);
	
	public List<Jogo> findByConsole(String console);
	
	 public List<Jogo> findByConsoleAndEstado(String console, Estado estado);
}
