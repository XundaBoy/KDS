package app.dto;

import jakarta.validation.constraints.NotNull;

public record SolicitarTrocaDTO( 
	@NotNull
    Long usuarioBId,

    @NotNull
    Long jogoXId,

    @NotNull
    Long jogoYId
){}
