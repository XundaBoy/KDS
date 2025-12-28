package app.dto;

public record AnuncioCreateDTO(
        Long usuarioId,
        Long jogoId,
        String descricao
) {}
