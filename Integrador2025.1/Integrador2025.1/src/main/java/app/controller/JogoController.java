package app.controller;

import java.util.List;
import java.util.Optional;

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

import app.entity.Jogo;
import app.service.JogoService;

@RestController
@RequestMapping("api/jogo")
public class JogoController {
	
		@Autowired
		private JogoService jogoService;
		
		@PostMapping("/save")
		public ResponseEntity<String> save (@RequestBody Jogo jogo){
			try {
				String mensagem = this.jogoService.save(jogo);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
				
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		}
		
		@PostMapping("/update")
		public ResponseEntity<String> update (@RequestBody Jogo jogo, @PathVariable Long id){
			try {
				String mensagem = this.jogoService.update(jogo, id);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
		
		@DeleteMapping("/delete")
		public ResponseEntity<String> delete (@RequestParam Long id){
			try {
				String mensagem = this.jogoService.delete(id);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
				
		}
		
		@GetMapping("/findAll")
		public ResponseEntity<List<Jogo>> findAll(){
			try {
				List<Jogo> jogos = jogoService.findAll();
				return new ResponseEntity<>(jogos, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} 
		}
		
		@GetMapping("/findById")
		public ResponseEntity<Optional<Jogo>> findById(@RequestParam Long id){
			try {
				Optional<Jogo> jogos = jogoService.findById(id);
				return new ResponseEntity<>(jogos, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
		
		@GetMapping("/findByNomeContaining")
		public ResponseEntity<List<Jogo>> findByNomeContaining(@RequestParam String nome){
			try {
				List<Jogo> jogos = jogoService.findByNomeContaining(nome);
				return new ResponseEntity<>(jogos, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
}
