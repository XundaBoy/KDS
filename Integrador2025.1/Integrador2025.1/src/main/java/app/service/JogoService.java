package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Jogo;
import app.repository.JogoRepository;

@Service
public class JogoService {

	@Autowired
	private JogoRepository jogoRepository;
	
	public String save (Jogo jogo) {
		jogoRepository.save(jogo);
		return "Jogo salvo com sucesso!";
	}
	
	public String delete (Long id) {
		jogoRepository.deleteById(id);
		return"Jogo deletado com sucesso!";
	}
	
	public List<Jogo> findAll(){
		List<Jogo> lista = new ArrayList<>();
		
		return lista;
	}
	
	public List<Jogo>findByNomeContaining(String nome){
		return this.jogoRepository.findByNomeContaining(nome);
	}
	
	public List<Jogo>findByConsole(String console)	{
		return this.jogoRepository.findByConsole(console);
		
	}
}
