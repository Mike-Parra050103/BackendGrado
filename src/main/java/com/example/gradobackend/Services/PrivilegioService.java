package com.example.gradobackend.Services;

import com.example.gradobackend.Entities.Privilegio;
import com.example.gradobackend.Repositories.PrivilegioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegioService
{
    @Autowired
    PrivilegioRepository privilegioRepository;

    //==================================================================================================================
    //region LIST ALL PRIVILEGIOS
    //==================================================================================================================
    public List<Privilegio> getAllPrivilegios()
    {
        List<Privilegio> privilegios = privilegioRepository.findAll();
        if (privilegios.isEmpty()) {throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Privilegios found."); }
        return privilegios;
    }
    //endregion

    //==================================================================================================================
    //region LIST ENABLED OR DISABLED PRIVILEGIOS
    //==================================================================================================================
    public List<Privilegio> getPrivilegiosByEnabled(boolean isEnabled)
    {
        List<Privilegio> privilegios = privilegioRepository.findByEnabled(isEnabled);
        if (privilegios.isEmpty()) { throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Privilegios found with isEnabled = " + isEnabled); }
        return privilegios;
    }
    //endregion

    //==================================================================================================================
    //region LIST PRIVILEGIO BY ID
    //==================================================================================================================
    public Privilegio getPrivilegioById(long id)
    {
        Optional<Privilegio> privilegio = privilegioRepository.findById(id);
        if (privilegio.isPresent()) { return privilegio.get(); }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Privilegio not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region ADD PRIVILEGIO
    //==================================================================================================================
    public Privilegio addPrivilegio(Privilegio privilegio)
    {
        validatePrivilegio(privilegio);

        Optional<Privilegio> existingPrivilegio = privilegioRepository.findByNombrePrivilegio(privilegio.getNombrePrivilegio());
        if (existingPrivilegio.isPresent()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "A Privilegio with the same email already exists."); }
        privilegio.setEnabled(true);
        Privilegio savedPrivilegio = privilegioRepository.save(privilegio);
        return savedPrivilegio;
    }
    //endregion

    //==================================================================================================================
    //region EDIT PRIVILEGIO
    //==================================================================================================================
    public Privilegio editPrivilegio(long id, Privilegio editedPrivilegio) {
        validatePrivilegio(editedPrivilegio);

        Optional<Privilegio> existingPrivilegio = privilegioRepository.findByNombrePrivilegio(editedPrivilegio.getNombrePrivilegio());
        if (existingPrivilegio.isPresent() && existingPrivilegio.get().getId() != id) { throw new ResponseStatusException(HttpStatus.CONFLICT, "An Privilegio with the same email already exists."); }

        Optional<Privilegio> currentPrivilegioOptional = privilegioRepository.findById(id);
        if (currentPrivilegioOptional.isPresent())
        {
            Privilegio currentPrivilegio = currentPrivilegioOptional.get();
            if (currentPrivilegio.equals(editedPrivilegio)) { return currentPrivilegio; } // No changes were made
            // Update the fields of currentPrivilegio with the editedPrivilegio
            currentPrivilegio.setNombrePrivilegio(editedPrivilegio.getNombrePrivilegio());

            Privilegio updatedPrivilegio = privilegioRepository.save(currentPrivilegio);
            return updatedPrivilegio;
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Privilegio not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region DELETE PRIVILEGIO
    //==================================================================================================================
    public void softDeletePrivilegio(long id)
    {
        Optional<Privilegio> privilegioOptional = privilegioRepository.findById(id);
        if (privilegioOptional.isPresent())
        {
            Privilegio privilegio = privilegioOptional.get();
            privilegio.setEnabled(false); // Soft delete by setting enabled to false
            privilegioRepository.save(privilegio);
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Privilegio not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region VALIDATE PRIVILEGIO
    //==================================================================================================================
    private void validatePrivilegio(Privilegio privilegio) { if (privilegio.getNombrePrivilegio() == null || privilegio.getNombrePrivilegio().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre cannot be null or empty."); } }
    //endregion
}
