package com.example.gradobackend.Repositories;

import com.example.gradobackend.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{

    List<Usuario> findByEnabled(boolean isEnabled);

    Optional<Usuario> findByEmail(String email);
}
