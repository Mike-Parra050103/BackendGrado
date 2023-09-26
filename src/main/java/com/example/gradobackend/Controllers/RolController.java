package com.example.gradobackend.Controllers;

import com.example.gradobackend.Entities.Rol;
import com.example.gradobackend.Repositories.RolRepository;
import com.example.gradobackend.Services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/roles")
public class RolController
{
    @Autowired
    RolRepository rolRepository;
    @Autowired
    RolService rolService;

    //==================================================================================================================
    //region GET @GetMapping(/api/roles/showAll)
    //==================================================================================================================
    @GetMapping("/showAll")
    public ResponseEntity<List<Rol>> getAllRoles()
    {
        List<Rol> roles = rolService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/roles/showAll?isEnabled=")
    //==================================================================================================================
    @GetMapping("/showAllEnabledOrDisabled")
    public ResponseEntity<List<Rol>> getRolesByEnabled(@RequestParam(value = "isEnabled", required = true) boolean isEnabled)
    {
        List<Rol> roles = rolService.getRolesByEnabled(isEnabled);
        String message = isEnabled ? "Enabled Roles" : "Disabled Roles";
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/roles/{id}")
    //==================================================================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable("id") long id)
    {
        Rol rol = rolService.getRolById(id);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region POST @PostMapping("/api/roles/add")
    //==================================================================================================================
    @PostMapping("/add")
    public ResponseEntity<Rol> addRol(@RequestBody Rol rol)
    {
        Rol savedRol = rolService.addRol(rol);
        return new ResponseEntity<>(savedRol, HttpStatus.CREATED);
    }
    //endregion

    //==================================================================================================================
    //region PUT @PutMapping("/api/roles/edit/{id}")
    //==================================================================================================================
    @PutMapping("/edit/{id}")
    public ResponseEntity<Rol> editRol(@PathVariable("id") long id, @RequestBody Rol editedRol)
    {
        Rol updatedRol = rolService.editRol(id, editedRol);
        return new ResponseEntity<>(updatedRol, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region DELETE @DeleteMapping("/api/roles/delete/{id}")
    //==================================================================================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> softDeleteRol(@PathVariable("id") long id)
    {
        rolService.softDeleteRol(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
