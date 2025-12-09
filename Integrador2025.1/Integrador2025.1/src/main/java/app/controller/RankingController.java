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


import app.entity.Ranking;
import app.service.RankingService;

@RestController
@RequestMapping("api/ranking")
public class RankingController {
	
	@Autowired
	private RankingService rankingService;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Ranking ranking){
	
			String mensagem = this.rankingService.save(ranking);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Ranking ranking, @PathVariable Long id){
		

			String mensagem = this.rankingService.update(ranking, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCidade(@PathVariable Long id){

			String mensagem = this.rankingService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

		
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Ranking>>findAll(){
		List<Ranking> rankings = rankingService.findAll();
		return new ResponseEntity<>(rankings, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Ranking> getCidadeById(@PathVariable long id){
	
			Ranking rankings= this.rankingService.findById(id);
			return new ResponseEntity<>(rankings, HttpStatus.OK);

	}
	
	
	
	@GetMapping("/findByNomeStartingWithIgnoreCase")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Ranking>> findByNomeStartingWithIgnoreCase(@RequestParam String nome){
			List<Ranking> ranking = this.rankingService.findByNomeStartingWithIgnoreCase(nome);	
			return new ResponseEntity<>(ranking, HttpStatus.OK);

	}
	
	
}
