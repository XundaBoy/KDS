package app.dto;

import app.entity.enums.StatusTroca;

public record TrocaResponseDTO(Long id,
        Long usuarioAId,
        Long usuarioBId,
        Long jogoXId,
        Long jogoYId,
        StatusTroca status
        ) {

}
