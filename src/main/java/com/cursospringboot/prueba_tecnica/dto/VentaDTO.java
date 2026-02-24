package com.cursospringboot.prueba_tecnica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {

    // Datos de la venta
    private Long id;
    private LocalDate fecha;

    @NotBlank(message = "El estado de venta es obligatorio")
    private String estado;

    // Datos de la sucursal
    @NotNull(message = "El cliente es obligatorio")
    private Long idSucursal;

    // Lista de detalles
    private List<DetalleVentaDTO> detalle;

    // Total de la venta
    private Double total;
}
