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
		
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
		@PostMapping("/save")
		public ResponseEntity<String> save (@RequestBody Jogo jogo){
				String mensagem = this.jogoService.save(jogo);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);	
		}
		
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
		@PutMapping("/update/{id}")
		public ResponseEntity<String> update (@RequestBody Jogo jogo, @PathVariable Long id){
		
				String mensagem = this.jogoService.update(jogo, id);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}
		
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> delete (@PathVariable Long id){
		
				String mensagem = this.jogoService.delete(id);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}
		
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
		@GetMapping("/findAll/{numPaginaAtual}")
		public ResponseEntity<Page<Jogo>> findAll(@PathVariable("numPaginaAtual") int 
				numPaginaAtual){
			
				Page<Jogo> jogos = jogoService.findAll(numPaginaAtual);
				return new ResponseEntity<>(jogos, HttpStatus.OK);

		}
		
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
		@GetMapping("/findAllAll")
		public ResponseEntity<List<Jogo>>findAll(){
			List<Jogo> jogos = jogoService.findAllAll();
			return new ResponseEntity<>(jogos, HttpStatus.OK);
		}
	
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
		@GetMapping("/findById/{id}")
		public ResponseEntity<Optional<Jogo>> findById(@PathVariable Long id){
			
				Optional<Jogo> jogos = jogoService.findById(id);
				return new ResponseEntity<>(jogos, HttpStatus.OK);
		
		}
		
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
		@GetMapping("/findByNomeStartingWithIgnoreCase")
		public ResponseEntity<List<Jogo>> findByNomeContaining(@RequestParam String nome){
		
				List<Jogo> jogos = jogoService.findByNomeStartingWithIgnoreCase(nome);
				return new ResponseEntity<>(jogos, HttpStatus.OK);
			
		}
		
		@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole ('ROLE_USER')")
		@GetMapping("/findByConsole")
		public ResponseEntity<List<Jogo>> findByConsole(@RequestParam Long consoleId) {
		    Console console = new Console();
		    console.setId(consoleId); // constrói o objeto com só o ID
		    List<Jogo> jogos = jogoService.findByConsole(console);
		    return new ResponseEntity<>(jogos, HttpStatus.OK);
		}

		
		
}
