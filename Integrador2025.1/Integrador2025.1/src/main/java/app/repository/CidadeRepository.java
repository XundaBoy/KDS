package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

	public List<Cidade> findByNomeStartingWith(String nome);
	
	public List<Cidade> findByNomeIgnoreCase(String nome);
}

