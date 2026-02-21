package com.cursospringboot.prueba_tecnica.dto;

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
    private String estado;

    // Datos de la sucursal
    private Long idSucursal;

    // Lista de detalles
    private List<DetalleVentaDTO> detalle;

    // Total de la venta
    private Double total;
}
