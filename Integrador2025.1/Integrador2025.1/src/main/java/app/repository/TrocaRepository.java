package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Jogo;
import app.entity.Troca;
import app.entity.enums.StatusTroca;

public interface TrocaRepository extends JpaRepository<Troca, Long>{

	boolean existsByJogoXOrJogoYAndStatusIn(Jogo jogoX, Jogo jogoY, List<StatusTroca> status);

}
