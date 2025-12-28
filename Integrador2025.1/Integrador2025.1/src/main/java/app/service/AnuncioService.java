package app.service;

import java.util.List;
import org.springframework.stereotype.Service;

import app.dto.AnuncioResponseDTO;
import app.entity.Anuncio;
import app.entity.Jogo;
import app.entity.Usuario;
import app.entity.enums.StatusAnuncio;
import app.repository.AnuncioRepository;
import app.repository.JogoRepository;
import app.repository.UsuarioRepository;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepo;
    private final UsuarioRepository usuarioRepo;
    private final JogoRepository jogoRepo;

    public AnuncioService(AnuncioRepository anuncioRepo, UsuarioRepository usuarioRepo, JogoRepository jogoRepo) {
        this.anuncioRepo = anuncioRepo;
        this.usuarioRepo = usuarioRepo;
        this.jogoRepo = jogoRepo;
    }

    public Anuncio criar(Long usuarioId, Long jogoId, String descricao){

        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Jogo jogo = jogoRepo.findById(jogoId)
                .orElseThrow(() -> new IllegalArgumentException("Jogo não encontrado"));

        // DONO DO JOGO
        if(!jogo.getUsuario().getId().equals(usuarioId)){
            throw new IllegalArgumentException("Você só pode anunciar jogos que são seus.");
        }

        if(anuncioRepo.existsByJogoIdAndStatus(jogoId, StatusAnuncio.ATIVO)){
            throw new IllegalStateException("Este jogo já está anunciado.");
        }

        if(jogo.estaEmTroca()){
            throw new IllegalStateException("Este jogo está em negociação e não pode ser anunciado.");
        }

        Anuncio anuncio = new Anuncio();
        anuncio.setUsuario(usuario);
        anuncio.setJogo(jogo);
        anuncio.setDescricao(descricao);
        anuncio.setStatus(StatusAnuncio.ATIVO);

        return anuncioRepo.save(anuncio);
    }

    public List<Anuncio> findAll(){
        return anuncioRepo.findAll();
    }
    
    public List<AnuncioResponseDTO> findAllDTO(){
        return anuncioRepo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }


    public Anuncio findById(Long id){
        return anuncioRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Anúncio não encontrado"));
    }
    
    public AnuncioResponseDTO findByIdDTO(Long id){
        return toDTO(findById(id));
    }
    
    

    public List<Anuncio> findByUsuario(Long usuarioId){
        return anuncioRepo.findByUsuarioId(usuarioId);
    }
    

	public List<AnuncioResponseDTO> findByUsuarioDTO(Long usuarioId){
	    return anuncioRepo.findByUsuarioId(usuarioId)
	            .stream()
	            .map(this::toDTO)
	            .toList();
	}

    public Anuncio atualizar(Long id, String descricao){
        Anuncio anuncio = findById(id);

        if(anuncio.getStatus() != StatusAnuncio.ATIVO){
            throw new IllegalStateException("Só é possível editar anúncios ativos.");
        }

        anuncio.setDescricao(descricao);
        return anuncioRepo.save(anuncio);
    }

    public void remover(Long id){
        Anuncio anuncio = findById(id);
        anuncio.setStatus(StatusAnuncio.INATIVO);

        // 🔥 liberta o jogo novamente
        Jogo jogo = anuncio.getJogo();
        jogo.setDisponivel(true);
        jogoRepo.save(jogo);

        anuncioRepo.save(anuncio);
    }
    
    
    public AnuncioResponseDTO toDTO(Anuncio anuncio){
        return new AnuncioResponseDTO(
                anuncio.getId(),
                anuncio.getUsuario().getId(),
                anuncio.getJogo().getId(),
                anuncio.getJogo().getNome(),
                anuncio.getDescricao(),
                anuncio.getStatus().name()
        );
    }

}
