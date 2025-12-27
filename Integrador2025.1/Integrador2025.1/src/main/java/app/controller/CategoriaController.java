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
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
		Categoria salva = categoriaService.save(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(salva);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Categoria> update(@RequestBody Categoria categoria, @PathVariable Long id){
		Categoria atualizada = categoriaService.update(categoria, id);
		return ResponseEntity.ok(atualizada);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build(); // Status 204
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Categoria>> findAll(){
		return ResponseEntity.ok(categoriaService.findAll());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id){
		return ResponseEntity.ok(categoriaService.findById(id));
	}

}
