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
import org.springframework.web.bind.annotation.RestController;

import app.entity.Categoria;
import app.entity.Cidade;
import app.service.CategoriaService;
import app.service.CidadeService;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Categoria categoria){
		
		String mensagem = this.categoriaService.save(categoria);
		return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Categoria categoria, @PathVariable Long id){
			
			String mensagem = this.categoriaService.update(categoria, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCidade(@PathVariable Long id){
				
			String mensagem = this.categoriaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		
	}
	
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Categoria>>findAll(){
		List<Categoria> categorias = categoriaService.findAll();
		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Categoria> getCidadeById(@PathVariable long id){
		
			Categoria categoria= this.categoriaService.findById(id);
			return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

}
