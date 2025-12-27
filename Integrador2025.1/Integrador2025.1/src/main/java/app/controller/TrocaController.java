package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import app.dto.SolicitarTrocaDTO;
import app.dto.TrocaResponseDTO;
import app.entity.Troca;
import app.service.TrocaService;

@RestController
@RequestMapping("api/troca")
@CrossOrigin("*")
public class TrocaController {

    @Autowired
    private TrocaService trocaService;


    // ---------------------------------------------------------
    // 📌 SOLICITAR TROCA
    // usuarioAId vem do path
    // ---------------------------------------------------------
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/solicitar/{usuarioAId}")
    public ResponseEntity<TrocaResponseDTO> solicitarTroca(
            @PathVariable Long usuarioAId,
            @RequestBody SolicitarTrocaDTO dto ){

        TrocaResponseDTO nova = trocaService.solicitarTroca(usuarioAId, dto);
        return ResponseEntity.status(201).body(nova);
    }


    // ---------------------------------------------------------
    // 📌 CONFIRMAR TROCA
    // Cada usuário confirma separadamente
    // ---------------------------------------------------------
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{trocaId}/confirmar/{usuarioId}")
    public ResponseEntity<TrocaResponseDTO> confirmar(
            @PathVariable Long trocaId,
            @PathVariable Long usuarioId){

        TrocaResponseDTO confirmada = trocaService.confirmarTroca(trocaId, usuarioId);
        return ResponseEntity.ok(confirmada);
    }


    // ---------------------------------------------------------
    // 📌 CANCELAR TROCA
    // ---------------------------------------------------------
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{trocaId}/cancelar/{usuarioId}")
    public ResponseEntity<Void> cancelar(
            @PathVariable Long trocaId,
            @PathVariable Long usuarioId){

        trocaService.cancelarTroca(trocaId, usuarioId);
        return ResponseEntity.noContent().build();
    }


    // ---------------------------------------------------------
    // 📌 LISTAR TODAS (só admin)
    // ---------------------------------------------------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<List<Troca>> findAll(){
        return ResponseEntity.ok(trocaService.findAll());
    }


    // ---------------------------------------------------------
    // 📌 BUSCAR POR ID
    // ---------------------------------------------------------
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Troca> findById(@PathVariable Long id){
        return ResponseEntity.ok(trocaService.findById(id));
    }

}
