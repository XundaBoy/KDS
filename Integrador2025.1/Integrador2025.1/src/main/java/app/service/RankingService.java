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
	
	public Ranking save(Ranking ranking) {
        return rankingRepository.save(ranking);
    }
	
	public Ranking update(Ranking dados, Long id) {
        Ranking existente = findById(id);

        existente.setNome(dados.getNome());

        return rankingRepository.save(existente);
    }
	
	public void delete(Long id) {
        if(!rankingRepository.existsById(id)) {
            throw new RuntimeException("Ranking não encontrado");
        }
        rankingRepository.deleteById(id);
    }
	
	public List<Ranking> findAll(){
        return rankingRepository.findAll();
    }
	
	public Ranking findById(Long id) {
        return rankingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ranking não encontrado"));
    }
	
	
	
	public List<Ranking> findByNomeStartingWithIgnoreCase(String nome){
	
		return this.rankingRepository.findByNomeStartingWithIgnoreCase(nome);
	}
}
