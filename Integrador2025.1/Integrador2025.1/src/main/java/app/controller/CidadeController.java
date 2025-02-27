package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Cidade cidade){
		
		try {
			String mensagem = this.cidadeService.save(cidade);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> update (@RequestBody Cidade cidade, @PathVariable Long id){
		
		try {
			String mensagem = this.cidadeService.update(cidade, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCidade(@PathVariable Long id){
		try {
			
			String mensagem = this.cidadeService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
		
	}
	
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Cidade>>findAll(){
		List<Cidade> cidades = cidadeService.findAll();
		return new ResponseEntity<>(cidades, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Cidade> getCidadeById(@PathVariable long id){
		try {
			Cidade cidade= this.cidadeService.findById(id);
			return new ResponseEntity<>(cidade, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);// TODO: handle exception
		}
	}
	
	@GetMapping("/findByNomeStartingWith")
	public ResponseEntity<List<Cidade>> findByNomeStartingWith(@RequestParam String nome){
		
		try {
			List<Cidade> cidades = this.cidadeService.findByNomeStartingWith(nome);
			
			if(cidades.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(cidades, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
	
}
