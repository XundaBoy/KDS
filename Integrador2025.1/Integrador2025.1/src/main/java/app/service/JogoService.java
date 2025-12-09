package app.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import app.entity.Console;
import app.entity.Jogo;
import app.entity.Usuario;
import app.repository.ConsoleRepository;
import app.repository.JogoRepository;
import app.repository.UsuarioRepository;


@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConsoleRepository consoleRepository;

    public String save(Jogo jogo) {
        try {
            if (jogo.getValor() < 0) {
                throw new IllegalArgumentException("O valor do jogo deve ser maior ou igual a zero.");
            }

            // Buscar o usuário real pelo ID
            if (jogo.getUsuario() == null || jogo.getUsuario().getId() == null) {
                throw new IllegalArgumentException("Usuário é obrigatório");
            }

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(jogo.getUsuario().getId());
            if (!usuarioOpt.isPresent()) {
                throw new IllegalArgumentException("Usuário não encontrado");
            }

            jogo.setUsuario(usuarioOpt.get());

            // Buscar o console real pelo ID
            if (jogo.getConsole() == null || jogo.getConsole().getId() == null) {
                throw new IllegalArgumentException("Console é obrigatório");
            }

            Optional<Console> consoleOpt = consoleRepository.findById(jogo.getConsole().getId());
            if (!consoleOpt.isPresent()) {
                throw new IllegalArgumentException("Console não encontrado");
            }

            jogo.setConsole(consoleOpt.get());
            
            //Salvar arquivo no banco
           

            jogoRepository.save(jogo);
            return "Jogo salvo com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar: " + e.getMessage();
        }
    }

    public String update(Jogo jogo, Long id) {
        jogo.setId(id);
        return save(jogo); 
    }

    public String delete(Long id) {
        jogoRepository.deleteById(id);
        return "Jogo deletado com sucesso!";
    }

    public Page<Jogo> findAll(int numPaginaAtual){ 
    	int totalPorPagina = 2; 
    	PageRequest pageRequest = PageRequest.of(numPaginaAtual-1, totalPorPagina); 
    	return this.jogoRepository.findAll(pageRequest); 
    	} 

    public List<Jogo> findAllAll(){
		return jogoRepository.findAll();
	}
    
    public Optional<Jogo> findById(Long id) {
        return this.jogoRepository.findById(id);
    }

    public List<Jogo> findByNomeStartingWithIgnoreCase(String nome) {
        return this.jogoRepository.findByNomeStartingWithIgnoreCase(nome);
    }

    public List<Jogo> findByConsole(Console console) {
        return this.jogoRepository.findByConsole(console);
    }
}
