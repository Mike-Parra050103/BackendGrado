package com.example.gradobackend.Controllers;

import com.example.gradobackend.Entities.Usuario;
import com.example.gradobackend.Repositories.UsuarioRepository;
import com.example.gradobackend.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController
{
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioService usuarioService;

    //==================================================================================================================
    //region GET @GetMapping(/api/usuarios/showAll)
    //==================================================================================================================
    @GetMapping("/showAll")
    public ResponseEntity<List<Usuario>> getAllUsuarios()
    {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/usuarios/showAll?isEnabled=")
    //==================================================================================================================
    @GetMapping("/showAllEnabledOrDisabled")
    public ResponseEntity<List<Usuario>> getUsuariosByEnabled(@RequestParam(value = "isEnabled", required = true) boolean isEnabled)
    {
        List<Usuario> usuarios = usuarioService.getUsuariosByEnabled(isEnabled);
        String message = isEnabled ? "Enabled Usuarios" : "Disabled Usuarios";
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region GET @GetMapping("/api/usuarios/{id}")
    //==================================================================================================================
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") long id)
    {
        Usuario usuario = usuarioService.getUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region POST @PostMapping("/api/usuarios/add")
    //==================================================================================================================
    @PostMapping("/add")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario)
    {
        Usuario savedUsuario = usuarioService.addUsuario(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }
    //endregion

    //==================================================================================================================
    //region PUT @PutMapping("/api/usuarios/edit/{id}")
    //==================================================================================================================
    @PutMapping("/edit/{id}")
    public ResponseEntity<Usuario> editUsuario(@PathVariable("id") long id, @RequestBody Usuario editedUsuario)
    {
        Usuario updatedUsuario = usuarioService.editUsuario(id, editedUsuario);
        return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
    }
    //endregion

    //==================================================================================================================
    //region DELETE @DeleteMapping("/api/usuarios/delete/{id}")
    //==================================================================================================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> softDeleteUsuario(@PathVariable("id") long id)
    {
        usuarioService.softDeleteUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //endregion
}
