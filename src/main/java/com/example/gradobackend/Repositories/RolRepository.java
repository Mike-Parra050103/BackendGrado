package com.example.gradobackend.Repositories;

import com.example.gradobackend.Entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long>
{

    List<Rol> findByEnabled(boolean isEnabled);

    Optional<Rol> findByNombreRol(String nombreRol);
}
