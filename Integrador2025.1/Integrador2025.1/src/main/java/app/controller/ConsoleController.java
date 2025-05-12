package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Cidade;
import app.entity.Console;
import app.service.ConsoleService;

@RestController
@RequestMapping("api/console")
@CrossOrigin("*")
public class ConsoleController {
	
	@Autowired
	private ConsoleService consoleService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Console console){
		
			String mensagem = this.consoleService.save(console);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Console console, @PathVariable Long id){
		
			String mensagem = this.consoleService.update(console, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Console>> findAll(){
		
		List<Console> consoles = consoleService.findAll();
			return new ResponseEntity<>(consoles, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Console> findById(@PathVariable Long id){
	
			Console console = new Console();
			console = this.consoleService.findById(id);
			return new ResponseEntity<>(console, HttpStatus.OK);
		
	}
		
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
	    try {
	       
	        String mensagem = consoleService.delete(id);
	        return new ResponseEntity<>(mensagem, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        
	        return new ResponseEntity<>("Console não encontrado", HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        
	        return new ResponseEntity<>("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
	@GetMapping("/findByNomeStartingWithIgnoreCase")
	public ResponseEntity<List<Console>> findByNomeStartingWith(@RequestParam String nome){
		
			List<Console> consoles = this.consoleService.findByNomeStartingWith(nome);	
			return new ResponseEntity<>(consoles, HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
	@GetMapping("/findByMarcaContainingIgnoreCase")
	public ResponseEntity<List<Console>> findByMarca(@PathVariable String marca){
		
			List<Console> consoles = this.consoleService.findByMarca(marca);
			return new ResponseEntity<>(consoles, HttpStatus.OK);
	
	}
	
	
	
}
