package com.cursospringboot.prueba_tecnica.service;

import com.cursospringboot.prueba_tecnica.dto.SucursalDTO;

import java.util.List;

public interface ISucursalService {

    public List<SucursalDTO> getSucursales();

    public SucursalDTO saveSucursal(SucursalDTO sucursalDTO);

    public SucursalDTO editSucursal(Long id, SucursalDTO sucursalDTO);

    public void deleteSurcursal(Long id);
}
