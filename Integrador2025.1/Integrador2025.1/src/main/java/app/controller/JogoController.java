package app.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

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

import app.entity.Console;

import app.entity.Jogo;
import app.entity.Ranking;
import app.service.JogoService;

@RestController
@RequestMapping("api/jogo")
@CrossOrigin("*")
public class JogoController {
	
		@Autowired
		private JogoService jogoService;
		
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@PostMapping("/save")
		public ResponseEntity<Jogo> save(@RequestBody Jogo jogo){
			Jogo salvo = jogoService.save(jogo);
			return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
		}
		
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@PutMapping("/update/{id}")
		public ResponseEntity<Jogo> update(@RequestBody Jogo jogo, @PathVariable Long id){
			Jogo atualizado = jogoService.update(jogo, id);
			return ResponseEntity.ok(atualizado);
		}
		
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id){
			jogoService.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@GetMapping("/findAll/{pagina}")
		public ResponseEntity<Page<Jogo>> findAll(@PathVariable int pagina){
			return ResponseEntity.ok(jogoService.findAll(pagina));
		}
		
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@GetMapping("/findAllAll")
		public ResponseEntity<List<Jogo>> findAllAll(){
			return ResponseEntity.ok(jogoService.findAllAll());
		}
	
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@GetMapping("/findById/{id}")
		public ResponseEntity<Jogo> findById(@PathVariable Long id){
			return ResponseEntity.ok(jogoService.findById(id));
		}
		
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@GetMapping("/findByNomeStartingWithIgnoreCase")
		public ResponseEntity<List<Jogo>> findByNomeContaining(@RequestParam String nome){
			return ResponseEntity.ok(jogoService.findByNomeStartingWithIgnoreCase(nome));
		}
		
		
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@GetMapping("/findByConsole")
		public ResponseEntity<List<Jogo>> findByConsole(@RequestParam Long consoleId) {
		    List<Jogo> jogos = jogoService.findByConsole(consoleId);
		    return ResponseEntity.ok(jogos);
		}

		
		
}
