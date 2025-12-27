package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Cidade;
import app.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade save(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }
	
	public Cidade update(Cidade cidade, Long id) {
        Cidade existente = findById(id);
        existente.setNome(cidade.getNome());
        return cidadeRepository.save(existente);
    }
	
	  public void delete(Long id) {
	        if(!cidadeRepository.existsById(id)){
	            throw new RuntimeException("Cidade não encontrada");
	        }
	        cidadeRepository.deleteById(id);
	    }
	
	public List<Cidade> findAll(){
		List<Cidade> lista = new ArrayList<>();
		lista = this.cidadeRepository.findAll();
		return lista;
	}
	
	public Cidade findById(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
    }
	
	public List<Cidade> findByNomeStartingWith(String nome){
		return this.cidadeRepository.findByNomeStartingWithIgnoreCase(nome);
	}
	
	public List<Cidade> findByNomeIgnoreCase(String nome){
		return this.cidadeRepository.findByNomeIgnoreCase(nome);
	}
	
}
