package app.controller;

import app.dto.ChatMessageResponseDTO;
import app.dto.EnviarMensagemDTO;
import app.entity.ChatMessage;
import app.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chat")
@CrossOrigin("*")
public class ChatController {

    private final ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @PostMapping("/enviar")
    public ResponseEntity<ChatMessageResponseDTO> enviar(@RequestBody EnviarMensagemDTO dto){
        return ResponseEntity.ok(
            service.enviarMensagem(dto.remetente(), dto.destinatario(), dto.conteudo())
        );
    }


  
    @GetMapping("/historico")
    public ResponseEntity<List<ChatMessageResponseDTO>> historico(
            @RequestParam Long usuario1,
            @RequestParam Long usuario2
    ){
        return ResponseEntity.ok(service.historico(usuario1, usuario2));
    }

  
    @PutMapping("/marcarComoLida/{id}")
    public ResponseEntity<Void> marcarComoLida(@PathVariable Long id){
        service.marcarComoLida(id);
        return ResponseEntity.noContent().build();
    }
}
