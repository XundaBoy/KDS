package app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Console;
import app.repository.ConsoleRepository;
@Service
public class ConsoleService {

	@Autowired
	private ConsoleRepository consoleRepository;
	
	public Console save(Console console) {
        return consoleRepository.save(console);
    }
	
	public Console update(Console console, Long id) {
        Console existente = consoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Console não encontrado"));
        
        existente.setNome(console.getNome());
        existente.setMarca(console.getMarca());
        
        return consoleRepository.save(existente);
    }
	
	public List<Console> findAll(){
		return consoleRepository.findAll();
	}
	
	public Console findById(Long id){
		Console console = new Console();
		console = consoleRepository.findById(id).get();
		return console;
	}
	
	public void delete(Long id) {
        if(!consoleRepository.existsById(id)) {
            throw new RuntimeException("Console não encontrado");
        }
        consoleRepository.deleteById(id);
    }
	
	public List<Console> findByNomeStartingWith(String nome){
		return this.consoleRepository.findByNomeStartingWithIgnoreCase(nome);
	}
	
	public List<Console> findByMarca(String marca){
		return this.consoleRepository.findByMarcaContainingIgnoreCase(marca);
	}
	
	
}
