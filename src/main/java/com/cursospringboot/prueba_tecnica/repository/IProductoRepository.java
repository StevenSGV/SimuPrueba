package com.cursospringboot.prueba_tecnica.repository;

import com.cursospringboot.prueba_tecnica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar producto por nombre
    Optional<Producto> findByNombre(String nombre);
}
