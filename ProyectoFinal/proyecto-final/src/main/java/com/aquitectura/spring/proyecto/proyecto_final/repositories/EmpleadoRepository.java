package com.aquitectura.spring.proyecto.proyecto_final.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquitectura.spring.proyecto.proyecto_final.models.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    boolean existsByCorreo(String correo);

    Optional<Empleado> findByCorreo(String correo);
}
