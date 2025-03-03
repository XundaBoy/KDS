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
import app.entity.Usuario;
import app.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	//teste pull
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody Usuario usuario){
		try {
			String mensagem = this.usuarioService.save(usuario);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
		}
	
	@PostMapping("/update")
	public ResponseEntity<String> update (@RequestBody Usuario usuario, @PathVariable Long id){
		
		try {
			String mensagem = this.usuarioService.update(usuario, id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
	
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> usuarios = usuarioService.findAll();
		  return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<Optional<Usuario>> findById(@RequestParam Long id){
		try {
			Optional<Usuario> usuarios = usuarioService.findById(id);
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
try {
			
			String mensagem = this.usuarioService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK );
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST );

		}
}
	
	 @GetMapping("/findByNomeStartingWith")
	    public ResponseEntity<List<Usuario>> findByNomeStartingWith(@RequestParam String nome) {
	        try {
	            List<Usuario> usuarios = this.usuarioService.findByNomeStartingWith(nome);

	            if (usuarios.isEmpty()) {
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }

	            return new ResponseEntity<>(usuarios, HttpStatus.OK);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
