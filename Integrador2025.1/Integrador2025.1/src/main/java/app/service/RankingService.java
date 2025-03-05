package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cidade;
import app.entity.Ranking;
import app.repository.RankingRepository;

@Service
public class RankingService {

	@Autowired
	private RankingRepository rankingRepository;
	
	public String save ( Ranking ranking) {
		rankingRepository.save(ranking);
		return "Ranking salvo com sucesso!";
	}
	public String update (Ranking ranking, Long id) {
		ranking.setId(id);
		this.rankingRepository.save(ranking);
		return "Ranking atualizado com sucesso";
	}
	
	public String delete(Long id) {
		rankingRepository.deleteById(id);
		return"Raking deletado com sucesso!";
	}
	
	public List<Ranking> findAll(){
		List<Ranking> lista = new ArrayList<>();
		lista = this.rankingRepository.findAll();
		return lista;
	}
	
	public Ranking findById(Long id) {
		return rankingRepository.findById(id).orElse(null);
	}
	
	public List<Ranking> findByNomeStartingWith(String nome){
		return this.rankingRepository.findByNomeStartingWith(nome);
	}
	
	public List<Cidade> findByNomeIgnoreCase(String nome){
		return this.rankingRepository.findByNomeIgnoreCase(nome);
	}
}
