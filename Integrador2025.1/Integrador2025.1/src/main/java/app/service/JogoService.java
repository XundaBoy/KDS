  package app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Console;

import app.entity.Jogo;
import app.repository.JogoRepository;

@Service
public class JogoService {

	@Autowired
	private JogoRepository jogoRepository;
	
	public String save(Jogo jogo) {
	    try {
	        if (jogo.getValor() < 0) {
	            throw new IllegalArgumentException("O valor do jogo deve ser maior ou igual a zero.");
	        }
	        jogoRepository.save(jogo);
	        return "Jogo salvo com sucesso!";
	    } catch (Exception e) {
	        e.printStackTrace();  
	        return "Erro ao cadastrar: " + e.getMessage();  
	    }
	}
	
	public String update (Jogo jogo, Long id) {
		jogo.setId(id);
		this.jogoRepository.save(jogo);
		return "Jogo atualizado com sucesso";
	}
	
	public String delete (Long id) {
		jogoRepository.deleteById(id);
		return"Jogo deletado com sucesso!";
	}
	
	public List<Jogo> findAll(){
		List<Jogo> lista = new ArrayList<>();
		lista = this.jogoRepository.findAll();
		return lista;
	}
	
	public Optional<Jogo> findById(Long id){
		return this.jogoRepository.findById(id);
	}
	
	public List<Jogo>findByNomeStartingWithIgnoreCase(String nome){
		return this.jogoRepository.findByNomeStartingWithIgnoreCase(nome);
	}
	
	public List<Jogo>findByConsole(Console console){
		return this.jogoRepository.findByConsole(console);
				
	}
	

	
}
