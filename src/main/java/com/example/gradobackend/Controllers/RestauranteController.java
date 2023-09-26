package com.example.gradobackend.Controllers;

import com.example.gradobackend.Entities.Restaurante;
import com.example.gradobackend.Repositories.RestauranteRepository;
import com.example.gradobackend.Services.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController
{
    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    RestauranteService restauranteService;

    //==================================================================================================================
    //region GET @GetMapping(/api/restaurantes/showAll)
    //==================================================================================================================
    @GetMapping("/showAll")
    public ResponseEntity<List<Restaurante>> getAllRestaurantes()
    {
        List<Restaurante> restaurantes = restauranteService.getAllRestaurantes();
        return new ResponseEntity<>(restaurantes, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/restaurantes/showAll?isEnabled=")
    //==================================================================================================================
    @GetMapping("/showAllEnabledOrDisabled")
    public ResponseEntity<List<Restaurante>> getRestaurantesByEnabled(@RequestParam(value = "isEnabled", required = true) boolean isEnabled)
    {
        List<Restaurante> restaurantes = restauranteService.getRestaurantesByEnabled(isEnabled);
        String message = isEnabled ? "Enabled Restaurantes" : "Disabled Restaurantes";
        return new ResponseEntity<>(restaurantes, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/restaurantes/{id}")
    //==================================================================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable("id") long id)
    {
        Restaurante restaurante = restauranteService.getRestauranteById(id);
        return new ResponseEntity<>(restaurante, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region POST @PostMapping("/api/restaurantes/add")
    //==================================================================================================================
    @PostMapping("/add")
    public ResponseEntity<Restaurante> addRestaurante(@RequestBody Restaurante restaurante)
    {
        Restaurante savedRestaurante = restauranteService.addRestaurante(restaurante);
        return new ResponseEntity<>(savedRestaurante, HttpStatus.CREATED);
    }
    //endregion

    //==================================================================================================================
    //region PUT @PutMapping("/api/restaurantes/edit/{id}")
    //==================================================================================================================
    @PutMapping("/edit/{id}")
    public ResponseEntity<Restaurante> editRestaurante(@PathVariable("id") long id, @RequestBody Restaurante editedRestaurante)
    {
        Restaurante updatedRestaurante = restauranteService.editRestaurante(id, editedRestaurante);
        return new ResponseEntity<>(updatedRestaurante, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region DELETE @DeleteMapping("/api/restaurantes/delete/{id}")
    //==================================================================================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> softDeleteRestaurante(@PathVariable("id") long id)
    {
        restauranteService.softDeleteRestaurante(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
