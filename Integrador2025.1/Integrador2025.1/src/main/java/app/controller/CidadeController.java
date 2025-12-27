package app.controller;

import java.util.List;

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
import app.service.CidadeService;

@RestController
@RequestMapping("api/cidade")
@CrossOrigin("*")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<Cidade> save(@RequestBody Cidade cidade){
		Cidade salva = cidadeService.save(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(salva);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Cidade> update (@RequestBody Cidade cidade, @PathVariable Long id){
		Cidade atualizada = cidadeService.update(cidade, id);
		return ResponseEntity.ok(atualizada);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCidade(@PathVariable Long id){
		cidadeService.delete(id);
		return ResponseEntity.noContent().build(); //204
	}
	
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Cidade>> findAll(){
		return ResponseEntity.ok(cidadeService.findAll());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Cidade> findById(@PathVariable Long id){
		return ResponseEntity.ok(cidadeService.findById(id));
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findByNomeStartingWithIgnoreCase")
	public ResponseEntity<List<Cidade>> findByNomeStartingWith(@RequestParam String nome){
		return ResponseEntity.ok(cidadeService.findByNomeStartingWith(nome));
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findByNomeIgnoreCase")
	public ResponseEntity<List<Cidade>> findByNomeIgnoreCase(@RequestParam String nome){
		return ResponseEntity.ok(cidadeService.findByNomeIgnoreCase(nome));
	}
	
}
