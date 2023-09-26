package com.example.gradobackend.Services;

import com.example.gradobackend.Entities.Usuario;
import com.example.gradobackend.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService
{
    @Autowired
    UsuarioRepository usuarioRepository;

    //==================================================================================================================
    //region LIST ALL USUARIOS
    //==================================================================================================================
    public List<Usuario> getAllUsuarios()
    {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Usuarios found."); }
        return usuarios;
    }
    //endregion

    //==================================================================================================================
    //region LIST ENABLED OR DISABLED USUARIOS
    //==================================================================================================================
    public List<Usuario> getUsuariosByEnabled(boolean isEnabled)
    {
        List<Usuario> usuarios = usuarioRepository.findByEnabled(isEnabled);
        if (usuarios.isEmpty()) { throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Usuarios found with isEnabled = " + isEnabled); }
        return usuarios;
    }
    //endregion

    //==================================================================================================================
    //region LIST USUARIO BY ID
    //==================================================================================================================
    public Usuario getUsuarioById(long id)
    {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) { return usuario.get(); }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario not found with ID: " + id); }
    }
    //endregion
    
    //==================================================================================================================
    //region ADD USUARIO
    //==================================================================================================================
    public Usuario addUsuario(Usuario usuario) 
    {
        validateUsuario(usuario);

        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUsuario.isPresent()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "An Usuario with the same email already exists."); }
        usuario.setEnabled(true);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return savedUsuario;
    }
    //endregion

    //==================================================================================================================
    //region EDIT USUARIO
    //==================================================================================================================
    public Usuario editUsuario(long id, Usuario editedUsuario) {
        validateUsuario(editedUsuario);

        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(editedUsuario.getEmail());
        if (existingUsuario.isPresent() && existingUsuario.get().getId() != id) { throw new ResponseStatusException(HttpStatus.CONFLICT, "An Usuario with the same email already exists."); }

        Optional<Usuario> currentUsuarioOptional = usuarioRepository.findById(id);
        if (currentUsuarioOptional.isPresent())
        {
            Usuario currentUsuario = currentUsuarioOptional.get();
            if (currentUsuario.equals(editedUsuario)) { return currentUsuario; } // No changes were made
            // Update the fields of currentUsuario with the editedUsuario
            currentUsuario.setNombre(editedUsuario.getNombre());
            currentUsuario.setApellido(editedUsuario.getApellido());
            currentUsuario.setEmail(editedUsuario.getEmail());
            currentUsuario.setPassword(editedUsuario.getPassword());

            Usuario updatedUsuario = usuarioRepository.save(currentUsuario);
            return updatedUsuario;
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region DELETE USUARIO
    //==================================================================================================================
    public void softDeleteUsuario(long id)
    {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent())
        {
            Usuario usuario = usuarioOptional.get();
            usuario.setEnabled(false); // Soft delete by setting enabled to false
            usuarioRepository.save(usuario);
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region VALIDATE USUARIO
    //==================================================================================================================
    private void validateUsuario(Usuario usuario)
    {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre cannot be null or empty."); }

        if (usuario.getApellido() == null || usuario.getApellido().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Apellido cannot be null or empty."); }

        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Email cannot be null or empty."); }

        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Password cannot be null or empty."); }
    }
    //endregion
}
