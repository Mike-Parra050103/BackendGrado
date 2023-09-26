package com.example.gradobackend.Repositories;

import com.example.gradobackend.Entities.PalabraClave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PalabraClaveRepository extends JpaRepository<PalabraClave, Long>
{

    List<PalabraClave> findByEnabled(boolean isEnabled);

    Optional<PalabraClave> findByPalabra(String palabra);
}
