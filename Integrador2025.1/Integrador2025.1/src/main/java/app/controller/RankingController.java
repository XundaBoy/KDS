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
import app.entity.Ranking;
import app.service.RankingService;

@RestController
@RequestMapping("api/ranking")
public class RankingController {
	
	@Autowired
	private RankingService rankingService;
	
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Ranking ranking){
		
		try {
			String mensagem = this.rankingService.save(ranking);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> update (@RequestBody Ranking ranking, @PathVariable Long id){
		
		try {
			String mensagem = this.rankingService.update(ranking, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCidade(@PathVariable Long id){
		try {
			
			String mensagem = this.rankingService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
		
	}
	
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Ranking>>findAll(){
		List<Ranking> cidades = rankingService.findAll();
		return new ResponseEntity<>(cidades, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Ranking> getCidadeById(@PathVariable long id){
		try {
			Ranking rankings= this.rankingService.findById(id);
			return new ResponseEntity<>(rankings, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);// TODO: handle exception
		}
	}
	
	@GetMapping("/findByNomeStartingWith")
	public ResponseEntity<List<Ranking>> findByNomeStartingWith(@RequestParam String nome){
		
		try {
			List<Ranking> rankings = this.rankingService.findByNomeStartingWith(nome);
			
			if(rankings.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(rankings, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
	@GetMapping("/findByNomeIgnoreCase")
	public ResponseEntity<List<Cidade>> findByNomeIgnoreCase(@RequestParam String nome){
		
		try {
			List<Cidade> cidades = this.rankingService.findByNomeIgnoreCase(nome);
			
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
