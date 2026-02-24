package com.cursospringboot.prueba_tecnica.service;

import com.cursospringboot.prueba_tecnica.dto.SucursalDTO;
import com.cursospringboot.prueba_tecnica.exception.NotFoundException;
import com.cursospringboot.prueba_tecnica.mapper.Mapper;
import com.cursospringboot.prueba_tecnica.model.Sucursal;
import com.cursospringboot.prueba_tecnica.repository.ISucursalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SucursalService implements ISucursalService {

    private final ISucursalRepository sucursalRepository;

    @Override
    public List<SucursalDTO> getSucursales() {
        return sucursalRepository.findAll()
                .stream()
                .map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO saveSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = Sucursal.builder()
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();
        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public SucursalDTO editSucursal(Long id, SucursalDTO sucursalDTO) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada."));

        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setDireccion(sucursalDTO.getDireccion());

        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public void deleteSurcursal(Long id) {
        if (!sucursalRepository.existsById(id)) {
            throw new NotFoundException("Sucursal no encontrada.");
        }

        sucursalRepository.deleteById(id);
    }
}
