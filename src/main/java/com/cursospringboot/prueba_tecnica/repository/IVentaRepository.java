package com.cursospringboot.prueba_tecnica.repository;

import com.cursospringboot.prueba_tecnica.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta,Long> {
}
