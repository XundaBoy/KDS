package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Console;

import app.entity.Troca;

public interface TrocaRepository extends JpaRepository<Troca, Long>{

	public List<Troca> findByJogosUsuarioXConsoleOrJogosUsuarioYConsole(Console console, Console console2);
	

	
	
}
