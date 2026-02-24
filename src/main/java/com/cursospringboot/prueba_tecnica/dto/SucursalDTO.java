package com.cursospringboot.prueba_tecnica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalDTO {

    private Long id;

    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String nombre;
    private String direccion;
}
