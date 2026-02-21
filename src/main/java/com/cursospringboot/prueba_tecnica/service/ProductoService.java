package com.cursospringboot.prueba_tecnica.service;

import com.cursospringboot.prueba_tecnica.dto.ProductoDTO;
import com.cursospringboot.prueba_tecnica.exception.NotFoundException;
import com.cursospringboot.prueba_tecnica.mapper.Mapper;
import com.cursospringboot.prueba_tecnica.model.Producto;
import com.cursospringboot.prueba_tecnica.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> getProductos() {
        return productoRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        var producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .precio(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();
        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO editProducto(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado."));

        producto.setNombre(productoDTO.getNombre());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setPrecio(productoDTO.getPrecio());

        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NotFoundException("Producto no encontrado.");
        }

        productoRepository.deleteById(id);
    }
}
