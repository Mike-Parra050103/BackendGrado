package com.example.gradobackend.Services;

import com.example.gradobackend.Entities.Restaurante;
import com.example.gradobackend.Repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService
{
    @Autowired
    RestauranteRepository restauranteRepository;

    //==================================================================================================================
    //region LIST ALL RESTAURANTES
    //==================================================================================================================
    public List<Restaurante> getAllRestaurantes()
    {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        if (restaurantes.isEmpty()) {throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Restaurantes found."); }
        return restaurantes;
    }
    //endregion

    //==================================================================================================================
    //region LIST ENABLED OR DISABLED RESTAURANTES
    //==================================================================================================================
    public List<Restaurante> getRestaurantesByEnabled(boolean isEnabled)
    {
        List<Restaurante> restaurantes = restauranteRepository.findByEnabled(isEnabled);
        if (restaurantes.isEmpty()) { throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Restaurantes found with isEnabled = " + isEnabled); }
        return restaurantes;
    }
    //endregion

    //==================================================================================================================
    //region LIST RESTAURANTE BY ID
    //==================================================================================================================
    public Restaurante getRestauranteById(long id)
    {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);
        if (restaurante.isPresent()) { return restaurante.get(); }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region ADD RESTAURANTE
    //==================================================================================================================
    public Restaurante addRestaurante(Restaurante restaurante)
    {
        validateRestaurante(restaurante);

        Optional<Restaurante> existingRestaurante = restauranteRepository.findByNombreRestaurante(restaurante.getNombreRestaurante());
        if (existingRestaurante.isPresent()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "A Restaurante with the same email already exists."); }
        restaurante.setEnabled(true);
        Restaurante savedRestaurante = restauranteRepository.save(restaurante);
        return savedRestaurante;
    }
    //endregion

    //==================================================================================================================
    //region EDIT RESTAURANTE
    //==================================================================================================================
    public Restaurante editRestaurante(long id, Restaurante editedRestaurante) {
        validateRestaurante(editedRestaurante);

        Optional<Restaurante> existingRestaurante = restauranteRepository.findByNombreRestaurante(editedRestaurante.getNombreRestaurante());
        if (existingRestaurante.isPresent() && existingRestaurante.get().getId() != id) { throw new ResponseStatusException(HttpStatus.CONFLICT, "An Restaurante with the same email already exists."); }

        Optional<Restaurante> currentRestauranteOptional = restauranteRepository.findById(id);
        if (currentRestauranteOptional.isPresent())
        {
            Restaurante currentRestaurante = currentRestauranteOptional.get();
            if (currentRestaurante.equals(editedRestaurante)) { return currentRestaurante; } // No changes were made
            // Update the fields of currentRestaurante with the editedRestaurante
            currentRestaurante.setNombreRestaurante(editedRestaurante.getNombreRestaurante());
            currentRestaurante.setDireccionRestaurante(editedRestaurante.getDireccionRestaurante());
            currentRestaurante.setCoordenadas(editedRestaurante.getCoordenadas());

            Restaurante updatedRestaurante = restauranteRepository.save(currentRestaurante);
            return updatedRestaurante;
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region DELETE RESTAURANTE
    //==================================================================================================================
    public void softDeleteRestaurante(long id)
    {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        if (restauranteOptional.isPresent())
        {
            Restaurante restaurante = restauranteOptional.get();
            restaurante.setEnabled(false); // Soft delete by setting enabled to false
            restauranteRepository.save(restaurante);
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region VALIDATE RESTAURANTE
    //==================================================================================================================
    private void validateRestaurante(Restaurante restaurante)
    {
        if (restaurante.getNombreRestaurante() == null || restaurante.getNombreRestaurante().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre cannot be null or empty."); }
        if (restaurante.getDireccionRestaurante() == null || restaurante.getDireccionRestaurante().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Direccion cannot be null or empty."); }
        if (restaurante.getCoordenadas() == null || restaurante.getCoordenadas().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Coordenadas cannot be null or empty."); }
    }
    //endregion
}
