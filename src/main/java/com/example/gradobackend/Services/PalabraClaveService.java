package com.example.gradobackend.Services;

import com.example.gradobackend.Entities.PalabraClave;
import com.example.gradobackend.Repositories.PalabraClaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PalabraClaveService
{
    @Autowired
    PalabraClaveRepository palabraClaveRepository;

    //==================================================================================================================
    //region LIST ALL PALABRAS CLAVE
    //==================================================================================================================
    public List<PalabraClave> getAllPalabrasClave()
    {
        List<PalabraClave> palabrasClave = palabraClaveRepository.findAll();
        if (palabrasClave.isEmpty()) {throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No PalabrasClave found."); }
        return palabrasClave;
    }
    //endregion

    //==================================================================================================================
    //region LIST ENABLED OR DISABLED PALABRAS CLAVE
    //==================================================================================================================
    public List<PalabraClave> getPalabrasClaveByEnabled(boolean isEnabled)
    {
        List<PalabraClave> palabrasClave = palabraClaveRepository.findByEnabled(isEnabled);
        if (palabrasClave.isEmpty()) { throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No PalabrasClave found with isEnabled = " + isEnabled); }
        return palabrasClave;
    }
    //endregion

    //==================================================================================================================
    //region LIST PALABRA CLAVE BY ID
    //==================================================================================================================
    public PalabraClave getPalabraClaveById(long id)
    {
        Optional<PalabraClave> palabraClave = palabraClaveRepository.findById(id);
        if (palabraClave.isPresent()) { return palabraClave.get(); }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PalabraClave not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region ADD PALABRA CLAVE
    //==================================================================================================================
    public PalabraClave addPalabraClave(PalabraClave palabraClave)
    {
        validatePalabraClave(palabraClave);

        Optional<PalabraClave> existingPalabraClave = palabraClaveRepository.findByPalabra(palabraClave.getPalabra());
        if (existingPalabraClave.isPresent()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "A PalabraClave with the same email already exists."); }
        palabraClave.setEnabled(true);
        PalabraClave savedPalabraClave = palabraClaveRepository.save(palabraClave);
        return savedPalabraClave;
    }
    //endregion

    //==================================================================================================================
    //region EDIT PALABRA CLAVE
    //==================================================================================================================
    public PalabraClave editPalabraClave(long id, PalabraClave editedPalabraClave) {
        validatePalabraClave(editedPalabraClave);

        Optional<PalabraClave> existingPalabraClave = palabraClaveRepository.findByPalabra(editedPalabraClave.getPalabra());
        if (existingPalabraClave.isPresent() && existingPalabraClave.get().getId() != id) { throw new ResponseStatusException(HttpStatus.CONFLICT, "An PalabraClave with the same email already exists."); }

        Optional<PalabraClave> currentPalabraClaveOptional = palabraClaveRepository.findById(id);
        if (currentPalabraClaveOptional.isPresent())
        {
            PalabraClave currentPalabraClave = currentPalabraClaveOptional.get();
            if (currentPalabraClave.equals(editedPalabraClave)) { return currentPalabraClave; } // No changes were made
            // Update the fields of currentPalabraClave with the editedPalabraClave
            currentPalabraClave.setPalabra(editedPalabraClave.getPalabra());

            PalabraClave updatedPalabraClave = palabraClaveRepository.save(currentPalabraClave);
            return updatedPalabraClave;
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PalabraClave not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region DELETE PALABRA CLAVE
    //==================================================================================================================
    public void softDeletePalabraClave(long id)
    {
        Optional<PalabraClave> palabraClaveOptional = palabraClaveRepository.findById(id);
        if (palabraClaveOptional.isPresent())
        {
            PalabraClave palabraClave = palabraClaveOptional.get();
            palabraClave.setEnabled(false); // Soft delete by setting enabled to false
            palabraClaveRepository.save(palabraClave);
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PalabraClave not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region VALIDATE PALABRA CLAVE
    //==================================================================================================================
    private void validatePalabraClave(PalabraClave palabraClave) { if (palabraClave.getPalabra() == null || palabraClave.getPalabra().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre cannot be null or empty."); } }
    //endregion
}
