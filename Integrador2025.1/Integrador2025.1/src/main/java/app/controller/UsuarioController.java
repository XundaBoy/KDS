package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import app.entity.Usuario;
import app.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/save")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        Usuario salvo = usuarioService.save(usuario);
        return ResponseEntity.status(201).body(salvo);
    }



    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Long id){
    	Usuario atualizado = usuarioService.update(id, usuario);
        return ResponseEntity.ok(atualizado);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findAll")
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.ok(usuarioService.findAll());
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findById(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/findByNomeStartingWithIgnoreCase")
    public ResponseEntity<List<Usuario>> findByNomeStartingWithIgnoreCase(@RequestParam String nome) {
        return ResponseEntity.ok(usuarioService.findByNomeStartingWithIgnoreCase(nome));
    }

}
