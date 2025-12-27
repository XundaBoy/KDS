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
@CrossOrigin("*")
public class RankingController {
	
	@Autowired
	private RankingService rankingService;
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/save")
	public ResponseEntity<Ranking> save(@RequestBody Ranking ranking){
		Ranking salvo = rankingService.save(ranking);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Ranking> update(@RequestBody Ranking ranking, @PathVariable Long id){
		Ranking atualizado = rankingService.update(ranking, id);
		return ResponseEntity.ok(atualizado);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		rankingService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findAll")
	public ResponseEntity<List<Ranking>> findAll(){
		return ResponseEntity.ok(rankingService.findAll());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findById/{id}")
	public ResponseEntity<Ranking> findById(@PathVariable Long id){
		return ResponseEntity.ok(rankingService.findById(id));
	}
	
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findByNomeStartingWithIgnoreCase")
	public ResponseEntity<List<Ranking>> findByNomeStartingWithIgnoreCase(@RequestParam String nome){
		return ResponseEntity.ok(rankingService.findByNomeStartingWithIgnoreCase(nome));
	}
	
	
}
