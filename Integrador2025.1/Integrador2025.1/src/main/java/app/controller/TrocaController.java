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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Console;

import app.entity.Troca;
import app.service.TrocaService;

@RestController
@RequestMapping("api/troca")
@CrossOrigin("*")
public class TrocaController {

    @Autowired
    private TrocaService trocaService;

    // Endpoint para realizar a troca
    @PostMapping("/realizar/{jogoXId}/{jogoYId}/{usuarioXId}/{usuarioYId}")
    public ResponseEntity<String> realizarTroca(
            @PathVariable Long jogoXId,
            @PathVariable Long jogoYId,
            @PathVariable Long usuarioXId,
            @PathVariable Long usuarioYId) {

     
            String response = trocaService.realizarTroca(jogoXId, jogoYId, usuarioXId, usuarioYId);
            return new ResponseEntity<>(response, HttpStatus.OK); // Retorna sucesso
  
    }
    @GetMapping("/findAll")
	public ResponseEntity<List<Troca>> findAll(){
		List<Troca> trocas = trocaService.findAll();
		  return new ResponseEntity<>(trocas, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Optional<Troca>> findById(@PathVariable Long id){
	
			Optional<Troca> trocas = trocaService.findById(id);
			return new ResponseEntity<>(trocas, HttpStatus.OK);

	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTroca(@PathVariable Long id) {

			String mensagem = this.trocaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK );

}
	
	@GetMapping("/findByConsole")
	public ResponseEntity<List<Troca>> findByConsole(@RequestParam Console console){
	
			List<Troca> trocas = trocaService.findByConsole(console);
			return new ResponseEntity<>(trocas,HttpStatus.OK);
	
	}
	
	
}
