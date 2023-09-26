package com.example.gradobackend.Controllers;

import com.example.gradobackend.Entities.Categoria;
import com.example.gradobackend.Repositories.CategoriaRepository;
import com.example.gradobackend.Services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController
{
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    CategoriaService categoriaService;

    //==================================================================================================================
    //region GET @GetMapping(/api/categorias/showAll)
    //==================================================================================================================
    @GetMapping("/showAll")
    public ResponseEntity<List<Categoria>> getAllCategorias()
    {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/categorias/showAll?isEnabled=")
    //==================================================================================================================
    @GetMapping("/showAllEnabledOrDisabled")
    public ResponseEntity<List<Categoria>> getCategoriasByEnabled(@RequestParam(value = "isEnabled", required = true) boolean isEnabled)
    {
        List<Categoria> categorias = categoriaService.getCategoriasByEnabled(isEnabled);
        String message = isEnabled ? "Enabled Categorias" : "Disabled Categorias";
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/categorias/{id}")
    //==================================================================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") long id)
    {
        Categoria categoria = categoriaService.getCategoriaById(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region POST @PostMapping("/api/categorias/add")
    //==================================================================================================================
    @PostMapping("/add")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria)
    {
        Categoria savedCategoria = categoriaService.addCategoria(categoria);
        return new ResponseEntity<>(savedCategoria, HttpStatus.CREATED);
    }
    //endregion

    //==================================================================================================================
    //region PUT @PutMapping("/api/categorias/edit/{id}")
    //==================================================================================================================
    @PutMapping("/edit/{id}")
    public ResponseEntity<Categoria> editCategoria(@PathVariable("id") long id, @RequestBody Categoria editedCategoria)
    {
        Categoria updatedCategoria = categoriaService.editCategoria(id, editedCategoria);
        return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region DELETE @DeleteMapping("/api/categorias/delete/{id}")
    //==================================================================================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> softDeleteCategoria(@PathVariable("id") long id)
    {
        categoriaService.softDeleteCategoria(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
