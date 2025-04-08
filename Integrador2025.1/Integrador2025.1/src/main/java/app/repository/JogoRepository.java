package app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.entity.Console;
import app.entity.EstadoJogo;
import app.entity.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
	
	
	public List<Jogo> findByNomeContainingIgnoreCase(String nome);
	
	public List<Jogo> findByConsole(Console console);
	
	@Query("SELECT j FROM Jogo j WHERE j.estado IN ('NOVO', 'RECONDICIONADO')")
    public List<Jogo> findByEstadoNovoOuRecondicionado( );
	 
}
