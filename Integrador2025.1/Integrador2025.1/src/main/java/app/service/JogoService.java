  package app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Console;
import app.entity.EstadoJogo;
import app.entity.Jogo;
import app.repository.JogoRepository;

@Service
public class JogoService {

	@Autowired
	private JogoRepository jogoRepository;
	
	public String save (Jogo jogo) {
		//Regra de negócios 001
		try {
			if (jogo.getValor() < 0) {
	            throw new IllegalArgumentException("O valor do jogo deve ser maior ou igual a zero.");
	            
	        }
			jogoRepository.save(jogo);
			return "Jogo salvo com sucesso!";
		} catch (Exception e) {
			
		}
		return "Erro ao cadastrar";
		
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
	
	public List<Jogo>findByNomeContaining(String nome){
		return this.jogoRepository.findByNomeContaining(nome);
	}
	
	public List<Jogo>findByConsole(Console console){
		return this.jogoRepository.findByConsole(console);
				
	}
	
	public List<Jogo>findByEstadoNovoOuRecondicionado( ){
		return this.jogoRepository.findByEstadoNovoOuRecondicionado();
				
	}
	
}
