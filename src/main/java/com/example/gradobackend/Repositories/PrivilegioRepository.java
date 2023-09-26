package com.example.gradobackend.Repositories;

import com.example.gradobackend.Entities.Privilegio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrivilegioRepository extends JpaRepository<Privilegio, Long>
{

    List<Privilegio> findByEnabled(boolean isEnabled);

    Optional<Privilegio> findByNombrePrivilegio(String nombrePrivilegio);
}
