package app.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Console;
import app.entity.Jogo;
import app.entity.Venda;
import app.repository.ConsoleRepository;
import app.repository.JogoRepository;
import app.service.VendaService;

@RestController
@RequestMapping("api/venda")
@CrossOrigin("*")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    
   

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/realizar/{jogoId}/{usuarioVendedorId}/{usuarioCompradorId}")
    public ResponseEntity<String> realizarVenda(
            @PathVariable Long jogoId,
            @PathVariable Long usuarioVendedorId,
            @PathVariable Long usuarioCompradorId) {

     
    
            String response = vendaService.realizarVenda(jogoId, usuarioVendedorId, usuarioCompradorId);
            return new ResponseEntity<>(response, HttpStatus.OK);
    
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/findAll")
	public ResponseEntity<List<Venda>> findAll(){
		List<Venda> vendas = vendaService.findAll();
		  return new ResponseEntity<>(vendas, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/findById")
	public ResponseEntity<Optional<Venda>> findById(@RequestParam Long id){
		
			Optional<Venda> vendas = vendaService.findById(id);
			return new ResponseEntity<>(vendas, HttpStatus.OK);
	
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVenda(@PathVariable Long id) {

			String mensagem = this.vendaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK );
	
}
  
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/findByConsole")
	public ResponseEntity<List<Venda>> findByJogo_Console(@RequestParam Console console){
	
			List<Venda> vendas = vendaService.findByJogo_Console(console);
			return new ResponseEntity<>(vendas,HttpStatus.OK);
	
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/findByJogo")
	public ResponseEntity<List<Venda>> findByJogo(@RequestParam Jogo jogo){
	
			List<Venda> vendas = vendaService.findByJogo(jogo);
			return new ResponseEntity<>(vendas, HttpStatus.OK);
	
	}
    
}
