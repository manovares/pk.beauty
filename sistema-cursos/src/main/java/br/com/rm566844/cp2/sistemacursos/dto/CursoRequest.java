package br.com.rm566844.cp2.sistemacursos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CursoRequest(
        @NotBlank(message = "titulo é obrigatório")
        @Size(max = 150, message = "titulo deve ter até 150 caracteres")
        String titulo,

        String descricao
) {
}
