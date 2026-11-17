package com.aquitectura.spring.proyecto.proyecto_final.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquitectura.spring.proyecto.proyecto_final.models.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Integer> {

    Optional<Credencial> findByUsuarioAndContrasena(String usuario, String contrasena);

    boolean existsByUsuario(String usuario);
}
