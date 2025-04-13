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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Jogo;
import app.entity.Usuario;
import app.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin("*")
public class UsuarioController {
//aaaaaaaaaaaaaaaaa
	@Autowired
	private UsuarioService usuarioService;
	//teste pull
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Usuario usuario){
		
			String mensagem = this.usuarioService.save(usuario);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
			
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update (@RequestBody Usuario usuario, @PathVariable Long id){
		
			String mensagem = this.usuarioService.update(usuario, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);

	}
	
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> usuarios = usuarioService.findAll();
		  return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Optional<Usuario>> findById(@PathVariable Long id){
		
			Optional<Usuario> usuarios = usuarioService.findById(id);
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
	
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {

			String mensagem = this.usuarioService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK );
			

}
	
	 @GetMapping("/findByNomeStartingWithIgnoreCase")
	    public ResponseEntity<List<Usuario>> findByNomeStartingWithIgnoreCase(@RequestParam String nome) {
	       
	            List<Usuario> usuarios = this.usuarioService.findByNomeStartingWithIgnoreCase(nome);
	            return new ResponseEntity<>(usuarios, HttpStatus.OK);
	 }	    
}



