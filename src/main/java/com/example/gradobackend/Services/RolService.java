package com.example.gradobackend.Services;

import com.example.gradobackend.Entities.Rol;
import com.example.gradobackend.Repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RolService
{
    @Autowired
    RolRepository rolRepository;

    //==================================================================================================================
    //region LIST ALL ROLES
    //==================================================================================================================
    public List<Rol> getAllRoles()
    {
        List<Rol> roles = rolRepository.findAll();
        if (roles.isEmpty()) {throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Roles found."); }
        return roles;
    }
    //endregion

    //==================================================================================================================
    //region LIST ENABLED OR DISABLED ROLES
    //==================================================================================================================
    public List<Rol> getRolesByEnabled(boolean isEnabled)
    {
        List<Rol> roles = rolRepository.findByEnabled(isEnabled);
        if (roles.isEmpty()) { throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Roles found with isEnabled = " + isEnabled); }
        return roles;
    }
    //endregion

    //==================================================================================================================
    //region LIST ROL BY ID
    //==================================================================================================================
    public Rol getRolById(long id)
    {
        Optional<Rol> rol = rolRepository.findById(id);
        if (rol.isPresent()) { return rol.get(); }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region ADD ROL
    //==================================================================================================================
    public Rol addRol(Rol rol)
    {
        validateRol(rol);

        Optional<Rol> existingRol = rolRepository.findByNombreRol(rol.getNombreRol());
        if (existingRol.isPresent()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "A Rol with the same email already exists."); }
        rol.setEnabled(true);
        Rol savedRol = rolRepository.save(rol);
        return savedRol;
    }
    //endregion

    //==================================================================================================================
    //region EDIT ROL
    //==================================================================================================================
    public Rol editRol(long id, Rol editedRol) {
        validateRol(editedRol);

        Optional<Rol> existingRol = rolRepository.findByNombreRol(editedRol.getNombreRol());
        if (existingRol.isPresent() && existingRol.get().getId() != id) { throw new ResponseStatusException(HttpStatus.CONFLICT, "An Rol with the same email already exists."); }

        Optional<Rol> currentRolOptional = rolRepository.findById(id);
        if (currentRolOptional.isPresent())
        {
            Rol currentRol = currentRolOptional.get();
            if (currentRol.equals(editedRol)) { return currentRol; } // No changes were made
            // Update the fields of currentRol with the editedRol
            currentRol.setNombreRol(editedRol.getNombreRol());

            Rol updatedRol = rolRepository.save(currentRol);
            return updatedRol;
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region DELETE ROL
    //==================================================================================================================
    public void softDeleteRol(long id)
    {
        Optional<Rol> rolOptional = rolRepository.findById(id);
        if (rolOptional.isPresent())
        {
            Rol rol = rolOptional.get();
            rol.setEnabled(false); // Soft delete by setting enabled to false
            rolRepository.save(rol);
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region VALIDATE ROL
    //==================================================================================================================
    private void validateRol(Rol rol) { if (rol.getNombreRol() == null || rol.getNombreRol().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre cannot be null or empty."); } }
    //endregion
}
