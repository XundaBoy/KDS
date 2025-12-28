package app.controller;

import app.dto.AnuncioCreateDTO;
import app.dto.AnuncioResponseDTO;
import app.dto.AnuncioUpdateDTO;
import app.entity.Anuncio;
import app.service.AnuncioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/anuncio")
@CrossOrigin("*")
public class AnuncioController {

    @Autowired
    private AnuncioService service;


    // ---------------- CREATE ----------------
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/save")
    public ResponseEntity<AnuncioResponseDTO> save(@RequestBody AnuncioCreateDTO dto){
        var anuncio = service.criar(dto.usuarioId(), dto.jogoId(), dto.descricao());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.toDTO(anuncio));
    }



    // ---------------- UPDATE ----------------
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<AnuncioResponseDTO> update(
            @PathVariable Long id,
            @RequestBody AnuncioUpdateDTO dto){
        var anuncio = service.atualizar(id, dto.descricao());
        return ResponseEntity.ok(service.toDTO(anuncio));
    }



    // ---------------- DELETE (INATIVAR) ----------------
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.remover(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<AnuncioResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAllDTO());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<AnuncioResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findByIdDTO(id));
    }

    @GetMapping("/findByUsuario/{usuarioId}")
    public ResponseEntity<List<AnuncioResponseDTO>> findByUsuario(@PathVariable Long usuarioId){
        return ResponseEntity.ok(service.findByUsuarioDTO(usuarioId));
    }

    

}
