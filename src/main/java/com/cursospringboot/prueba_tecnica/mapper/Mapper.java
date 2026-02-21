package com.cursospringboot.prueba_tecnica.mapper;

import com.cursospringboot.prueba_tecnica.dto.DetalleVentaDTO;
import com.cursospringboot.prueba_tecnica.dto.ProductoDTO;
import com.cursospringboot.prueba_tecnica.dto.SucursalDTO;
import com.cursospringboot.prueba_tecnica.dto.VentaDTO;
import com.cursospringboot.prueba_tecnica.model.DetalleVenta;
import com.cursospringboot.prueba_tecnica.model.Producto;
import com.cursospringboot.prueba_tecnica.model.Sucursal;
import com.cursospringboot.prueba_tecnica.model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    // Mapeo de producto a productoDTO
    public static ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .cantidad(producto.getCantidad())
                .build();
    }

    // Mapeo de venta a ventaDTO
    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        var detalle = venta.getDetalleVenta().stream().map(detalleVenta -> DetalleVentaDTO.builder()
                .id(detalleVenta.getProducto().getId())
                .nombreProducto(detalleVenta.getProducto().getNombre())
                .cantidadProducto(detalleVenta.getCantidadProducto())
                .precioUnitario(detalleVenta.getPrecioUnitario())
                .subTotal(detalleVenta.getPrecioUnitario() * detalleVenta.getCantidadProducto())
                .build()).collect(Collectors.toList());

        var total = detalle.stream().map(DetalleVentaDTO::getSubTotal).reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estado(venta.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }

    // Mapeo de sucursal a sucursalDTO
    public static SucursalDTO toDTO(Sucursal sucursal) {
        if (sucursal == null) {
            return null;
        }

        return SucursalDTO.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }

}
