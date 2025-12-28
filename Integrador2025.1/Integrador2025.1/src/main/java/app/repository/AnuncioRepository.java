package app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import app.entity.Anuncio;
import app.entity.enums.StatusAnuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    List<Anuncio> findByUsuarioId(Long usuarioId);

    List<Anuncio> findByStatus(StatusAnuncio status);

    boolean existsByJogoIdAndStatus(Long jogoId, StatusAnuncio status);
}
