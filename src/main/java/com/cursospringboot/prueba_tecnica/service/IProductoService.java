package com.cursospringboot.prueba_tecnica.service;

import com.cursospringboot.prueba_tecnica.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    public List<ProductoDTO> getProductos();

    public ProductoDTO saveProducto(ProductoDTO productoDTO);

    public ProductoDTO editProducto(Long id, ProductoDTO productoDTO);

    public void deleteProducto(Long id);
}
