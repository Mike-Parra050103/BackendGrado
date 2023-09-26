package com.example.gradobackend.Controllers;

import com.example.gradobackend.Entities.Privilegio;
import com.example.gradobackend.Repositories.PrivilegioRepository;
import com.example.gradobackend.Services.PrivilegioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/privilegios")
public class PrivilegioController
{
    @Autowired
    PrivilegioRepository privilegioRepository;
    @Autowired
    PrivilegioService privilegioService;

    //==================================================================================================================
    //region GET @GetMapping(/api/privilegios/showAll)
    //==================================================================================================================
    @GetMapping("/showAll")
    public ResponseEntity<List<Privilegio>> getAllPrivilegios()
    {
        List<Privilegio> privilegios = privilegioService.getAllPrivilegios();
        return new ResponseEntity<>(privilegios, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/privilegios/showAll?isEnabled=")
    //==================================================================================================================
    @GetMapping("/showAllEnabledOrDisabled")
    public ResponseEntity<List<Privilegio>> getPrivilegiosByEnabled(@RequestParam(value = "isEnabled", required = true) boolean isEnabled)
    {
        List<Privilegio> privilegios = privilegioService.getPrivilegiosByEnabled(isEnabled);
        String message = isEnabled ? "Enabled Privilegios" : "Disabled Privilegios";
        return new ResponseEntity<>(privilegios, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/privilegios/{id}")
    //==================================================================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Privilegio> getPrivilegioById(@PathVariable("id") long id)
    {
        Privilegio privilegio = privilegioService.getPrivilegioById(id);
        return new ResponseEntity<>(privilegio, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region POST @PostMapping("/api/privilegios/add")
    //==================================================================================================================
    @PostMapping("/add")
    public ResponseEntity<Privilegio> addPrivilegio(@RequestBody Privilegio privilegio)
    {
        Privilegio savedPrivilegio = privilegioService.addPrivilegio(privilegio);
        return new ResponseEntity<>(savedPrivilegio, HttpStatus.CREATED);
    }
    //endregion

    //==================================================================================================================
    //region PUT @PutMapping("/api/privilegios/edit/{id}")
    //==================================================================================================================
    @PutMapping("/edit/{id}")
    public ResponseEntity<Privilegio> editPrivilegio(@PathVariable("id") long id, @RequestBody Privilegio editedPrivilegio)
    {
        Privilegio updatedPrivilegio = privilegioService.editPrivilegio(id, editedPrivilegio);
        return new ResponseEntity<>(updatedPrivilegio, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region DELETE @DeleteMapping("/api/privilegios/delete/{id}")
    //==================================================================================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> softDeletePrivilegio(@PathVariable("id") long id)
    {
        privilegioService.softDeletePrivilegio(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
