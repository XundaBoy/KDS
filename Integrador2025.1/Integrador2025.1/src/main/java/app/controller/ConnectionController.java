package app.controller;

import java.awt.PageAttributes.MediaType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Usuario;
import app.exception.UsernameAlreadyUsedException;
import app.repository.UsuarioRepository;
import app.service.UsuarioService;

@RequestMapping("/api/connection")
@RestController
public class ConnectionController {

    @Autowired
    private UsuarioService userService;

    @Autowired
    private UsuarioRepository userDao;

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) {

        try {
            Usuario connectedUser = userService.connect(user);
            template.convertAndSend("/channel/login", connectedUser);
        } catch (UsernameAlreadyUsedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestBody Usuario user) {
    	Usuario disconnectedUser = userService.disconnect(user);
        template.convertAndSend("/channel/logout", disconnectedUser);
    }

    @GetMapping("/listUsers")
    public Iterable<Usuario> findConnectedUsers() {
        return userDao.findAll();
    }

    @GetMapping("/clearAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearAll() {
        userDao.deleteAll();
    }
}
