package com.pragma.tecnologia.infrastructure.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos para la gestión de Tecnologías")
public class TecnologiaRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres")
    @Schema(
            description = "Nombre de la tecnología",
            example = "Java",
            maxLength = 50,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 90, message = "La descripción no debe ser mayor a 90 caracteres")
    @Schema(
            description = "Breve descripción de la tecnología",
            example = "Lenguaje de programación orientado a objetos",
            maxLength = 90,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String descripcion;
}
