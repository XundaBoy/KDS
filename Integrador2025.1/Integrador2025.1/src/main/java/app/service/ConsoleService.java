package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.entity.Console;
import app.repository.ConsoleRepository;

public class ConsoleService {

	@Autowired
	private ConsoleRepository consoleRepository;
	
	public String save(Console console) {
		this.consoleRepository.save(console);
		return "Jogo salvo com sucesso";
	}
	
	public String update(Console console, Long id) {
		console.setId(id);
		this.consoleRepository.save(console);
		return "Jogo atualizado com sucesso";
	}
	
	public List<Console> findAll(){
		return consoleRepository.findAll();
	}
	
	public Console findById(Long id){
		Console console = new Console();
		console = consoleRepository.findById(id).get();
		return console;
	}
	
	public String delete(Long id) {
		consoleRepository.deleteById(id);
		return "Console deletado com sucesso";
	}
}
