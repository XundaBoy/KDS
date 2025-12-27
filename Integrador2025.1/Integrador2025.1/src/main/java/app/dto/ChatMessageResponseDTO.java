package app.dto;

import java.time.LocalDateTime;

public record ChatMessageResponseDTO(
        Long id,
        Long remetenteId,
        String remetenteNome,
        Long destinatarioId,
        String destinatarioNome,
        String conteudo,
        LocalDateTime enviadaEm,
        boolean lida
) {}
