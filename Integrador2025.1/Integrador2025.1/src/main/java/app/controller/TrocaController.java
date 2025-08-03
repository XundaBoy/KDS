package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Troca;
import app.service.TrocaService;

@RestController
@RequestMapping("api/troca")
@CrossOrigin("*")
public class TrocaController {

	
	@Autowired
	private TrocaService trocaService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
	@PostMapping("/save")
	public ResponseEntity<String>save(@RequestBody Troca troca){
		String mensagem = this.trocaService.save(troca);
		return new ResponseEntity<>(mensagem, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Troca>> findAll(){
		List<Troca> trocas = trocaService.findAll();
		return new ResponseEntity<>(trocas, HttpStatus.OK);	
		}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Troca> findById(@PathVariable Long id){
		Troca troca = new Troca();
		troca = this.trocaService.findById(id);
		return new ResponseEntity<>(troca, HttpStatus.OK);
	}	
	
}
