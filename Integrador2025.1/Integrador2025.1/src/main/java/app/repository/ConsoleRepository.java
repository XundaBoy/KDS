package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Cidade;
import app.entity.Console;

public interface ConsoleRepository extends JpaRepository<Console, Long>{
	public List<Console> findByNomeStartingWith(String nome);

}
