package com.pragma.tecnologia.domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tecnologia {
    private Long id;
    private String nombre;
    private String descripcion;
}
