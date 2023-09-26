package com.example.gradobackend.Repositories;

import com.example.gradobackend.Entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>
{

    List<Restaurante> findByEnabled(boolean isEnabled);

    Optional<Restaurante> findByNombreRestaurante(String nombreRestaurante);
}
