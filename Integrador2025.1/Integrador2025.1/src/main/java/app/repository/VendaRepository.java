package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Console;
import app.entity.Jogo;
import app.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	public List<Venda>findByJogo(Jogo jogo);
	
	public List<Venda>findByJogo_Console(Console console);
}
