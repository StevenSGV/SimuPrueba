package com.cursospringboot.prueba_tecnica.repository;

import com.cursospringboot.prueba_tecnica.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Long> {
}
