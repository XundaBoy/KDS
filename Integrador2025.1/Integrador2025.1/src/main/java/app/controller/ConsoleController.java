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
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<Console> save(@RequestBody Console console){
		Console salvo = consoleService.save(console);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Console> update(@RequestBody Console console, @PathVariable Long id){
		Console atualizado = consoleService.update(console, id);
		return ResponseEntity.ok(atualizado);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Console>> findAll(){
		return ResponseEntity.ok(consoleService.findAll());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Console> findById(@PathVariable Long id){
		return ResponseEntity.ok(consoleService.findById(id));
	}
		
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		consoleService.delete(id);
		return ResponseEntity.noContent().build(); // 204
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findByNomeStartingWithIgnoreCase")
	public ResponseEntity<List<Console>> findByNomeStartingWith(@RequestParam String nome){
		return ResponseEntity.ok(consoleService.findByNomeStartingWith(nome));
	}
	
	
	// ----------------------- BUSCAR POR MARCA -----------------------
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findByMarcaContainingIgnoreCase")
	public ResponseEntity<List<Console>> findByMarca(@RequestParam String marca){
		return ResponseEntity.ok(consoleService.findByMarca(marca));
	}
	
	
	
}
