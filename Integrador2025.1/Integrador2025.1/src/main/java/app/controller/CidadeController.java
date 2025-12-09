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
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Cidade cidade){
		
		String mensagem = this.cidadeService.save(cidade);
		return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Cidade cidade, @PathVariable Long id){
			
			String mensagem = this.cidadeService.update(cidade, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCidade(@PathVariable Long id){
				
			String mensagem = this.cidadeService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Cidade>>findAll(){
		List<Cidade> cidades = cidadeService.findAll();
		return new ResponseEntity<>(cidades, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Cidade> getCidadeById(@PathVariable long id){
		
			Cidade cidade= this.cidadeService.findById(id);
			return new ResponseEntity<>(cidade, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/findByNomeStartingWithIgnoreCase")
	public ResponseEntity<List<Cidade>> findByNomeStartingWith(@RequestParam String nome){
		
		
			List<Cidade> cidades = this.cidadeService.findByNomeStartingWith(nome);
		
			return new ResponseEntity<>(cidades, HttpStatus.OK);
			
	}
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/findByNomeIgnoreCase")
	public ResponseEntity<List<Cidade>> findByNomeIgnoreCase(@RequestParam String nome){
		
			List<Cidade> cidades = this.cidadeService.findByNomeIgnoreCase(nome);
			return new ResponseEntity<>(cidades, HttpStatus.OK);
		
	}
	
}
