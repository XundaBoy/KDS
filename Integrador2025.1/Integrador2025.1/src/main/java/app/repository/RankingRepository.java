package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Cidade;
import app.entity.Ranking;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
	public List<Ranking> findByNomeStartingWithIgnoreCase(String nome);
	
	public List<Cidade> findByNomeIgnoreCase(String nome);
}
