package com.cursospringboot.prueba_tecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El nombre del producto debe ser obligatorio")
    private String nombre;

    @NotBlank(message = "La categoria del producto debe ser obligatoria")
    private String categoria;

    @NotNull(message = "El precio del producto es obligatorio")
    @Positive(message = "El precio del producto debe ser mayor a 0")
    private Double precio;

    private int cantidad;
}
