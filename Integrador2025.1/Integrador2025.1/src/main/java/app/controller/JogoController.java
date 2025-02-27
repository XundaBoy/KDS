package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Jogo;
import app.service.JogoService;

@RestController
@RequestMapping("api/jogo")
public class JogoController {
	
		@Autowired
		JogoService jogoService;
		
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
		
		public ResponseEntity<String> update (@RequestBody Jogo jogo){
			try {
				String mensagem = this.jogoService.update(jogo);
				return new ResponseEntity<>(mensagem, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
}
