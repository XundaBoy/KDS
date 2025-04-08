package app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Console;
import app.entity.EstadoJogo;
import app.entity.Jogo;
import app.service.JogoService;

@RestController
@RequestMapping("api/jogo")
@CrossOrigin("*")
public class JogoController {
	
		@Autowired
		private JogoService jogoService;
		
		@PostMapping("/save")
		public ResponseEntity<String> save (@RequestBody Jogo jogo){
				String mensagem = this.jogoService.save(jogo);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);	
		}
		
		@PostMapping("/update")
		public ResponseEntity<String> update (@RequestBody Jogo jogo, @PathVariable Long id){
		
				String mensagem = this.jogoService.update(jogo, id);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> delete (@PathVariable Long id){
		
				String mensagem = this.jogoService.delete(id);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
		}
		
		@GetMapping("/findAll")
		public ResponseEntity<List<Jogo>> findAll(){
			
				List<Jogo> jogos = jogoService.findAll();
				return new ResponseEntity<>(jogos, HttpStatus.OK);

		}
		
		@GetMapping("/findById")
		public ResponseEntity<Optional<Jogo>> findById(@RequestParam Long id){
			
				Optional<Jogo> jogos = jogoService.findById(id);
				return new ResponseEntity<>(jogos, HttpStatus.OK);
		
		}
		
		@GetMapping("/findByNomeContaining")
		public ResponseEntity<List<Jogo>> findByNomeContaining(@RequestParam String nome){
		
				List<Jogo> jogos = jogoService.findByNomeContaining(nome);
				return new ResponseEntity<>(jogos, HttpStatus.OK);
			
		}
		@GetMapping("/findByConsole")
		public ResponseEntity<List<Jogo>> findByConsole(@RequestParam Console console){
		
				List<Jogo> consoles = jogoService.findByConsole(console);
				return new ResponseEntity<>(consoles, HttpStatus.OK);
			
		}
		
		@GetMapping("/findByEstadoNovoOuRecondicionado")
		public ResponseEntity<List<Jogo>> findByEstadoNovoOuRecondicionado(){
			
				List<Jogo> consoles = jogoService.findByEstadoNovoOuRecondicionado();
				return new ResponseEntity<>(consoles, HttpStatus.OK);
			
		}
}
