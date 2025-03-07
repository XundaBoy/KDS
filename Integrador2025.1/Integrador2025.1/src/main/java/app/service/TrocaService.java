package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.Console;
import app.entity.EstadoJogo;
import app.entity.Jogo;
import app.entity.Troca;
import app.entity.Usuario;
import app.repository.TrocaRepository;

@Service
public class TrocaService {

    @Autowired
    private TrocaRepository trocaRepository;

    @Autowired
    private JogoService jogoService;

    @Autowired
    private UsuarioService usuarioService;

    public String realizarTroca(Long jogoXId, Long jogoYId, Long usuarioXId, Long usuarioYId) {
        // Lógica para realizar a troca entre os jogos
        // Buscando os jogos e os usuários com os serviços

        Jogo jogoX = jogoService.findById(jogoXId).orElseThrow(() -> new RuntimeException("Jogo do usuário X não encontrado"));
        Jogo jogoY = jogoService.findById(jogoYId).orElseThrow(() -> new RuntimeException("Jogo do usuário Y não encontrado"));

        Usuario usuarioX = usuarioService.findById(usuarioXId).orElseThrow(() -> new RuntimeException("Usuário X não encontrado"));
        Usuario usuarioY = usuarioService.findById(usuarioYId).orElseThrow(() -> new RuntimeException("Usuario Y não encontrado"));

        // Criando a troca
        Troca troca = new Troca();
        troca.setJogosUsuarioX((List<Jogo>) jogoX);
        troca.setJogosUsuarioY((List<Jogo>) jogoY);
        troca.setUsuarioX(usuarioX);
        troca.setUsuarioY(usuarioY);

        // Salvando a troca no banco de dados
         trocaRepository.save(troca);
         return String.format("A troca foi realizada: O jogo '%s' foi trocado para o usuário '%s', e o jogo '%s' foi trocado para o usuário '%s'.", 
                 jogoX.getNome(), usuarioY.getNome(), jogoY.getNome(), usuarioX.getNome());
    }
    public String delete (long id) {
		trocaRepository.deleteById(id);
		return "Usuario deletado com sucesso!";
	}
	
	public  Optional<Troca> findById(Long id) {
		return this.trocaRepository.findById(id);
	}
	
	
	public List<Troca> findAll(){
		List<Troca> lista = new ArrayList<>();
		lista = this.trocaRepository.findAll();
		return lista;
	}
	
	public List<Troca> findByConsole(Console console) {
        return trocaRepository.findByJogoUsuarioXConsoleOrJogoUsuarioYConsole(console, console);
    }
	public List<Troca> findByEstadoJogo(EstadoJogo estado) {
        return trocaRepository.findByJogoUsuarioXEstadoOrJogoUsuarioYEstado(estado, estado);
    }
}
