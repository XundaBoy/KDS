package app.dto;

public record AnuncioResponseDTO(
        Long id,
        Long usuarioId,
        Long jogoId,
        String jogoNome,
        String descricao,
        String status
) {}
