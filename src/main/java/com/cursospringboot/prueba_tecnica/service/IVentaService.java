package com.cursospringboot.prueba_tecnica.service;

import com.cursospringboot.prueba_tecnica.dto.VentaDTO;

import java.util.List;

public interface IVentaService {

    public List<VentaDTO> getVentas();

    public VentaDTO saveVenta(VentaDTO ventaDTO);

    public VentaDTO editVenta(Long id, VentaDTO ventaDTO);

    public void deleteVenta(Long id);
}
