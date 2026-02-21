package com.cursospringboot.prueba_tecnica.controller;

import com.cursospringboot.prueba_tecnica.dto.ProductoDTO;
import com.cursospringboot.prueba_tecnica.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getProductos() {
        return ResponseEntity.ok(productoService.getProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoCreado = productoService.saveProducto(productoDTO);
        return ResponseEntity.created(URI.create("/api/productos" + productoCreado.getId())).body(productoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Long id,
                                                      @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.editProducto(id, productoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);

        return ResponseEntity.noContent().build();
    }
}
