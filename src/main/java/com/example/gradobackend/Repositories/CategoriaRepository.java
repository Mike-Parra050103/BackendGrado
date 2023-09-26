package com.example.gradobackend.Repositories;

import com.example.gradobackend.Entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>
{
    List<Categoria> findByEnabled(boolean isEnabled);

    Optional<Categoria> findByNombreCategoria(String nombreCategoria);
}
