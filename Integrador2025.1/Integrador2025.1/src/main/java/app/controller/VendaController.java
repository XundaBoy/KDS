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

import app.entity.Venda;
import app.service.VendaService;

@RestController
@RequestMapping("api/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    // Endpoint para realizar a venda
    @PostMapping("/realizar/{jogoId}/{usuarioVendedorId}/{usuarioCompradorId}")
    public ResponseEntity<String> realizarVenda(
            @PathVariable Long jogoId,
            @PathVariable Long usuarioVendedorId,
            @PathVariable Long usuarioCompradorId) {

        try {
            // Chama o serviço de venda
            String response = vendaService.realizarVenda(jogoId, usuarioVendedorId, usuarioCompradorId);
            return new ResponseEntity<>(response, HttpStatus.OK); // Retorna a mensagem com sucesso
        } catch (RuntimeException e) {
            // Em caso de erro, retorna o erro 400 (Bad Request)
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/findAll")
	public ResponseEntity<List<Venda>> findAll(){
		List<Venda> vendas = vendaService.findAll();
		  return new ResponseEntity<>(vendas, HttpStatus.OK);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<Optional<Venda>> findById(@RequestParam Long id){
		try {
			Optional<Venda> vendas = vendaService.findById(id);
			return new ResponseEntity<>(vendas, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVenda(@PathVariable Long id) {
try {
			
			String mensagem = this.vendaService.delete(id);
			return new ResponseEntity<>(mensagem, HttpStatus.OK );
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST );

		}
}
  
    
}
