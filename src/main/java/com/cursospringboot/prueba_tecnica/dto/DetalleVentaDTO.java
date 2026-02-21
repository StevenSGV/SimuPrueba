package com.cursospringboot.prueba_tecnica.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long id;
    private String nombreProducto;
    private Integer cantidadProducto;
    private Double precioUnitario;
    private Double subTotal;
}
