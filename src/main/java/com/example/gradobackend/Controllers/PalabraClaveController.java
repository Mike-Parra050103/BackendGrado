package com.example.gradobackend.Controllers;

import com.example.gradobackend.Entities.PalabraClave;
import com.example.gradobackend.Repositories.PalabraClaveRepository;
import com.example.gradobackend.Services.PalabraClaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localahost:8081")
@RestController
@RequestMapping("/api/palabrasClave")
public class PalabraClaveController
{
    @Autowired
    PalabraClaveRepository palabraClaveRepository;
    @Autowired
    PalabraClaveService palabraClaveService;

    //==================================================================================================================
    //region GET @GetMapping(/api/palabrasClave/showAll)
    //==================================================================================================================
    @GetMapping("/showAll")
    public ResponseEntity<List<PalabraClave>> getAllPalabrasClave()
    {
        List<PalabraClave> palabrasClave = palabraClaveService.getAllPalabrasClave();
        return new ResponseEntity<>(palabrasClave, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/palabrasClave/showAll?isEnabled=")
    //==================================================================================================================
    @GetMapping("/showAllEnabledOrDisabled")
    public ResponseEntity<List<PalabraClave>> getPalabrasClaveByEnabled(@RequestParam(value = "isEnabled", required = true) boolean isEnabled)
    {
        List<PalabraClave> palabrasClave = palabraClaveService.getPalabrasClaveByEnabled(isEnabled);
        String message = isEnabled ? "Enabled PalabrasClave" : "Disabled PalabrasClave";
        return new ResponseEntity<>(palabrasClave, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/palabrasClave/{id}")
    //==================================================================================================================
    @GetMapping("/{id}")
    public ResponseEntity<PalabraClave> getPalabraClaveById(@PathVariable("id") long id)
    {
        PalabraClave palabraClave = palabraClaveService.getPalabraClaveById(id);
        return new ResponseEntity<>(palabraClave, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region POST @PostMapping("/api/palabrasClave/add")
    //==================================================================================================================
    @PostMapping("/add")
    public ResponseEntity<PalabraClave> addPalabraClave(@RequestBody PalabraClave palabraClave)
    {
        PalabraClave savedPalabraClave = palabraClaveService.addPalabraClave(palabraClave);
        return new ResponseEntity<>(savedPalabraClave, HttpStatus.CREATED);
    }
    //endregion

    //==================================================================================================================
    //region PUT @PutMapping("/api/palabrasClave/edit/{id}")
    //==================================================================================================================
    @PutMapping("/edit/{id}")
    public ResponseEntity<PalabraClave> editPalabraClave(@PathVariable("id") long id, @RequestBody PalabraClave editedPalabraClave)
    {
        PalabraClave updatedPalabraClave = palabraClaveService.editPalabraClave(id, editedPalabraClave);
        return new ResponseEntity<>(updatedPalabraClave, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region DELETE @DeleteMapping("/api/palabrasClave/delete/{id}")
    //==================================================================================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> softDeletePalabraClave(@PathVariable("id") long id)
    {
        palabraClaveService.softDeletePalabraClave(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
