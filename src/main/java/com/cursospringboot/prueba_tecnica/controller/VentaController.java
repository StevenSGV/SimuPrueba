package com.cursospringboot.prueba_tecnica.controller;

import com.cursospringboot.prueba_tecnica.dto.VentaDTO;
import com.cursospringboot.prueba_tecnica.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ventas")
public class VentaController {

    private final IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getVentas() {
        return ResponseEntity.ok(ventaService.getVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> saveVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO ventaCreada = ventaService.saveVenta(ventaDTO);
        return ResponseEntity.created(URI.create("/api/ventas" + ventaCreada.getId())).build();
    }

    @PutMapping("/{id}")
    public VentaDTO updateVenta(@PathVariable Long id,
                                @RequestBody VentaDTO ventaDTO) {
        return ventaService.editVenta(id, ventaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VentaDTO> deleteVenta(@RequestBody Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
