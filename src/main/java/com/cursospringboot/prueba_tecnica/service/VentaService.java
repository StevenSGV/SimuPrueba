package com.cursospringboot.prueba_tecnica.service;

import com.cursospringboot.prueba_tecnica.dto.DetalleVentaDTO;
import com.cursospringboot.prueba_tecnica.dto.VentaDTO;
import com.cursospringboot.prueba_tecnica.exception.NotFoundException;
import com.cursospringboot.prueba_tecnica.mapper.Mapper;
import com.cursospringboot.prueba_tecnica.model.DetalleVenta;
import com.cursospringboot.prueba_tecnica.model.Producto;
import com.cursospringboot.prueba_tecnica.model.Sucursal;
import com.cursospringboot.prueba_tecnica.model.Venta;
import com.cursospringboot.prueba_tecnica.repository.IProductoRepository;
import com.cursospringboot.prueba_tecnica.repository.ISucursalRepository;
import com.cursospringboot.prueba_tecnica.repository.IVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService implements IVentaService {

    private final IVentaRepository ventaRepository;
    private final IProductoRepository productoRepository;
    private final ISucursalRepository sucursalRepository;

    @Override
    public List<VentaDTO> getVentas() {
        List<Venta> listaVentas = ventaRepository.findAll();
        List<VentaDTO> listaVentaDTO = new ArrayList<>();
        VentaDTO ventaDTO;

        for (Venta venta : listaVentas) {
            ventaDTO = Mapper.toDTO(venta);
            listaVentaDTO.add(ventaDTO);
        }

        return listaVentaDTO;
    }

    @Override
    @Transactional
    public VentaDTO saveVenta(VentaDTO ventaDTO) {
        if (ventaDTO == null) throw new RuntimeException("La venta no puede ser nula.");
        if (ventaDTO.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal.");
        if (ventaDTO.getDetalle() == null || ventaDTO.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto.");

        Sucursal sucursal = sucursalRepository.findById(ventaDTO.getIdSucursal()).orElse(null);
        if (sucursal == null) throw new NotFoundException("Sucursal no encontrada.");

        Venta venta = new Venta();

        venta.setFecha(ventaDTO.getFecha());
        venta.setEstado(ventaDTO.getEstado());
        venta.setSucursal(sucursal);
        venta.setTotal(venta.getTotal());

        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO detalleVentaDTO : ventaDTO.getDetalle()) {
            Producto producto = productoRepository.findByNombre(detalleVentaDTO.getNombreProducto()).orElse(null);
            if (producto == null) throw new RuntimeException("Producto no encontrado." + detalleVentaDTO.getNombreProducto());

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setPrecioUnitario(detalleVentaDTO.getPrecioUnitario());
            detalleVenta.setCantidadProducto(detalleVentaDTO.getCantidadProducto());
            detalleVenta.setVenta(venta);

            detalles.add(detalleVenta);
            totalCalculado = totalCalculado + (detalleVentaDTO.getPrecioUnitario() * detalleVentaDTO.getCantidadProducto());
        }

        venta.setDetalleVenta(detalles);

        venta = ventaRepository.save(venta);

        VentaDTO ventaSalida = Mapper.toDTO(venta);

        return ventaSalida;
    }

    @Override
    @Transactional
    public VentaDTO editVenta(Long id, VentaDTO ventaDTO) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) throw new RuntimeException("Venta no encontrada.");

        if (ventaDTO.getFecha() != null) {
            venta.setFecha(ventaDTO.getFecha());
        }

        if (ventaDTO.getEstado() != null) {
            venta.setEstado(ventaDTO.getEstado());
        }

        if (ventaDTO.getTotal() != null) {
            venta.setTotal(ventaDTO.getTotal());
        }

        if (ventaDTO.getIdSucursal() != null) {
            Sucursal sucursal = sucursalRepository.findById(ventaDTO.getIdSucursal()).orElse(null);
            if (sucursal == null) throw new NotFoundException("Sucursal no encontrada.");
            venta.setSucursal(sucursal);
        }

        ventaRepository.save(venta);

        VentaDTO ventaSalida = Mapper.toDTO(venta);

        return ventaSalida;
    }

    @Override
    public void deleteVenta(Long id) {

        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) throw new RuntimeException("Venta no encontrada.");
        ventaRepository.delete(venta);
    }
}
