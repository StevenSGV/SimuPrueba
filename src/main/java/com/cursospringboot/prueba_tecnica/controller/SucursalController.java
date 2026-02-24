package com.cursospringboot.prueba_tecnica.controller;

import com.cursospringboot.prueba_tecnica.dto.SucursalDTO;
import com.cursospringboot.prueba_tecnica.service.ISucursalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> getSucursales() {
        return ResponseEntity.ok(sucursalService.getSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> createSucursal(@Valid @RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO sucursalCreada = sucursalService.saveSucursal(sucursalDTO);

        return ResponseEntity.created(URI.create("/api/sucursales" + sucursalCreada.getId())).body(sucursalCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> updateSucursal(@PathVariable Long id,
                                                      @Valid @RequestBody SucursalDTO sucursalDTO) {
        return ResponseEntity.ok(sucursalService.editSucursal(id, sucursalDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSurcursal(id);
        return ResponseEntity.noContent().build();
    }
}
