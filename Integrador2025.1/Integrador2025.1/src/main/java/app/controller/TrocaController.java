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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Console;
import app.entity.EstadoJogo;
import app.entity.Troca;
import app.service.TrocaService;

@RestController
@RequestMapping("api/troca")
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

        try {
            // Chama o serviço de troca
            String response = trocaService.realizarTroca(jogoXId, jogoYId, usuarioXId, usuarioYId);
            return new ResponseEntity<>(response, HttpStatus.OK); // Retorna sucesso
        } catch (RuntimeException e) {
            // Em caso de erro, retorna o erro 400 (Bad Request)
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findAll")
	public ResponseEntity<List<Troca>> findAll(){
		List<Troca> trocas = trocaService.findAll();
		  return new ResponseEntity<>(trocas, HttpStatus.OK);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<Optional<Troca>> findById(@RequestParam Long id){
		try {
			Optional<Troca> trocas = trocaService.findById(id);
			return new ResponseEntity<>(trocas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTroca(@PathVariable Long id) {
try {
			
			String mensagem = this.trocaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK );
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST );

		}
}
	
	@GetMapping("/findByConsole")
	public ResponseEntity<List<Troca>> findByConsole(@RequestParam Console console){
		try {
			List<Troca> trocas = trocaService.findByConsole(console);
			return new ResponseEntity<>(trocas,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
	@GetMapping("/findByEstadoJogo")
	public ResponseEntity<List<Troca>> findByEstadoJogo(@RequestParam EstadoJogo estado){
		try {
			List<Troca> trocas = trocaService.findByEstadoJogo(estado);
			return new ResponseEntity<>(trocas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);// TODO: handle exception
		}
	}
	
   
}
