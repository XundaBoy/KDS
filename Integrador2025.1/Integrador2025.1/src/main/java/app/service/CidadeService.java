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
	
	public String save (Cidade cidade) {
		cidadeRepository.save(cidade);
		return "Cidade salva com sucesso!";
	}
	public String update (Cidade cidade, Long id) {
		cidade.setId(id);
		this.cidadeRepository.save(cidade);
		return "Cidade atualizado com sucesso";
	}
	
	public String delete(Long id) {
		cidadeRepository.deleteById(id);
		return"Cidade deletada com sucesso!";
	}
	
	public List<Cidade> findAll(){
		List<Cidade> lista = new ArrayList<>();
		lista = this.cidadeRepository.findAll();
		return lista;
	}
	
	public Cidade findById(Long id) {
		return cidadeRepository.findById(id).orElse(null);
	}
	
	public List<Cidade> findByNomeStartingWith(String nome){
		return this.cidadeRepository.findByNomeStartingWith(nome);
	}
	
	public List<Cidade> findByNomeIgnoreCase(String nome){
		return this.cidadeRepository.findByNomeIgnoreCase(nome);
	}
	
}
