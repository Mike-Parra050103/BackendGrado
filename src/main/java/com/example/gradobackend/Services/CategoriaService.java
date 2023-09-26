package com.example.gradobackend.Services;

import com.example.gradobackend.Entities.Categoria;
import com.example.gradobackend.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService
{
    @Autowired
    CategoriaRepository categoriaRepository;

    //==================================================================================================================
    //region LIST ALL CATEGORIAS
    //==================================================================================================================
    public List<Categoria> getAllCategorias()
    {
        List<Categoria> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Categorias found."); }
        return categorias;
    }
    //endregion

    //==================================================================================================================
    //region LIST ENABLED OR DISABLED CATEGORIAS
    //==================================================================================================================
    public List<Categoria> getCategoriasByEnabled(boolean isEnabled)
    {
        List<Categoria> categorias = categoriaRepository.findByEnabled(isEnabled);
        if (categorias.isEmpty()) { throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Categorias found with isEnabled = " + isEnabled); }
        return categorias;
    }
    //endregion

    //==================================================================================================================
    //region LIST CATEGORIA BY ID
    //==================================================================================================================
    public Categoria getCategoriaById(long id)
    {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) { return categoria.get(); }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region ADD CATEGORIA
    //==================================================================================================================
    public Categoria addCategoria(Categoria categoria)
    {
        validateCategoria(categoria);

        Optional<Categoria> existingCategoria = categoriaRepository.findByNombreCategoria(categoria.getNombreCategoria());
        if (existingCategoria.isPresent()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "A Categoria with the same email already exists."); }
        categoria.setEnabled(true);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return savedCategoria;
    }
    //endregion

    //==================================================================================================================
    //region EDIT CATEGORIA
    //==================================================================================================================
    public Categoria editCategoria(long id, Categoria editedCategoria) {
        validateCategoria(editedCategoria);

        Optional<Categoria> existingCategoria = categoriaRepository.findByNombreCategoria(editedCategoria.getNombreCategoria());
        if (existingCategoria.isPresent() && existingCategoria.get().getId() != id) { throw new ResponseStatusException(HttpStatus.CONFLICT, "An Categoria with the same email already exists."); }

        Optional<Categoria> currentCategoriaOptional = categoriaRepository.findById(id);
        if (currentCategoriaOptional.isPresent())
        {
            Categoria currentCategoria = currentCategoriaOptional.get();
            if (currentCategoria.equals(editedCategoria)) { return currentCategoria; } // No changes were made
            // Update the fields of currentCategoria with the editedCategoria
            currentCategoria.setNombreCategoria(editedCategoria.getNombreCategoria());

            Categoria updatedCategoria = categoriaRepository.save(currentCategoria);
            return updatedCategoria;
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region DELETE CATEGORIA
    //==================================================================================================================
    public void softDeleteCategoria(long id)
    {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent())
        {
            Categoria categoria = categoriaOptional.get();
            categoria.setEnabled(false); // Soft delete by setting enabled to false
            categoriaRepository.save(categoria);
        }
        else { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria not found with ID: " + id); }
    }
    //endregion

    //==================================================================================================================
    //region VALIDATE CATEGORIA
    //==================================================================================================================
    private void validateCategoria(Categoria categoria) { if (categoria.getNombreCategoria() == null || categoria.getNombreCategoria().isEmpty()) { throw new ResponseStatusException(HttpStatus.CONFLICT, "Nombre cannot be null or empty."); } }
    //endregion
}
