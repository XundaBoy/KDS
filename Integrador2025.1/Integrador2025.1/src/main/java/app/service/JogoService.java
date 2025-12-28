package app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Console;
import app.entity.Jogo;
import app.entity.Usuario;
import app.repository.ConsoleRepository;
import app.repository.JogoRepository;
import app.repository.UsuarioRepository;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ConsoleRepository consoleRepository;

    public Jogo save(Jogo jogo) {

        if (jogo.getValor() == null || jogo.getValor() < 0) {
            throw new IllegalArgumentException("O valor do jogo deve ser maior ou igual a zero.");
        }

        if (jogo.getAnunciante() == null || jogo.getAnunciante().getId() == null) {
            throw new IllegalArgumentException("Usuário é obrigatório");
        }

        Usuario usuario = usuarioRepository.findById(jogo.getAnunciante().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        jogo.setAnunciante(usuario);

        if (jogo.getConsole() == null || jogo.getConsole().getId() == null) {
            throw new IllegalArgumentException("Console é obrigatório");
        }

        Console console = consoleRepository.findById(jogo.getConsole().getId())
                .orElseThrow(() -> new RuntimeException("Console não encontrado"));
        jogo.setConsole(console);

        return jogoRepository.save(jogo);
    }

    public Jogo update(Jogo dados, Long id) {
        Jogo existente = findById(id);

        existente.setNome(dados.getNome());
        existente.setEstadoJogo(dados.getEstadoJogo());
        existente.setValor(dados.getValor());

        if (dados.getConsole() != null && dados.getConsole().getId() != null) {
            Console console = consoleRepository.findById(dados.getConsole().getId())
                    .orElseThrow(() -> new RuntimeException("Console não encontrado"));
            existente.setConsole(console);
        }

        return jogoRepository.save(existente);
    }

    public void delete(Long id) {
        if (!jogoRepository.existsById(id)) {
            throw new RuntimeException("Jogo não encontrado");
        }
        jogoRepository.deleteById(id);
    }

    public Page<Jogo> findAll(int numPaginaAtual){ 
        int totalPorPagina = 2; 
        PageRequest pageRequest = PageRequest.of(numPaginaAtual - 1, totalPorPagina); 
        return jogoRepository.findAll(pageRequest); 
    }

    public List<Jogo> findAllAll(){
        return jogoRepository.findAll();
    }

    public Jogo findById(Long id) {
        return jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
    }

    public List<Jogo> findByNomeStartingWithIgnoreCase(String nome) {
        return jogoRepository.findByNomeStartingWithIgnoreCase(nome);
    }

    public List<Jogo> findByConsole(Long consoleId) {
        Console console = consoleRepository.findById(consoleId)
                .orElseThrow(() -> new RuntimeException("Console não encontrado"));
        return jogoRepository.findByConsole(console);
    }

}
