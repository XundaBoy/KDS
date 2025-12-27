package app.dto;

public record EnviarMensagemDTO(
        Long remetente,
        Long destinatario,
        String conteudo
) {}
