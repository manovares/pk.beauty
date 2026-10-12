package br.com.matheustavares.cp2.sistemacursos.dto;

import br.com.matheustavares.cp2.sistemacursos.validation.MultipleOf;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ModuloRequest(
        @NotBlank(message = "titulo é obrigatório")
        @Size(max = 150, message = "titulo deve ter até 150 caracteres")
        String titulo,

        @NotNull(message = "ordem é obrigatória")
        @Positive(message = "ordem deve ser positiva")
        Integer ordem,

        @NotNull(message = "cargaHoraria é obrigatória")
        @Positive(message = "cargaHoraria deve ser positiva")
        @MultipleOf(value = 5, message = "cargaHoraria deve ser múltiplo de 5")
        Integer cargaHoraria
) {
}
