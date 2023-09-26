package com.example.gradobackend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rol")
public class Rol
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_rol")
    private String nombreRol;

    @Column(name = "enabled")
    private boolean enabled;

    //==================================================================================================================
    //region CONSTRUCTORES
    //==================================================================================================================
    public Rol() { }

    public Rol(long id, String nombreRol, boolean enabled)
    {
        this.id = id;
        this.nombreRol = nombreRol;
        this.enabled = enabled;
    }
    //endregion

    //==================================================================================================================
    //region GETTERS & SETTERS
    //==================================================================================================================
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNombreRol() { return nombreRol; }

    public void setNombreRol(String nombreRol) { this.nombreRol = nombreRol; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    //endregion

    //==================================================================================================================
    //region TOSTRING()
    //==================================================================================================================
    @Override
    public String toString()
    {
        return "Rol{" +
                "id=" + id +
                ", nombreRol='" + nombreRol + '\'' +
                ", enabled=" + enabled +
                '}';
    }
    //endregion
}
