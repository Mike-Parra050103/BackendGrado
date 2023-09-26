package com.example.gradobackend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @Column(name = "enabled")
    private boolean enabled;

    //==================================================================================================================
    //region CONSTRUCTORES
    //==================================================================================================================
    public Categoria()
    {
    }

    public Categoria(long id, String nombreCategoria, boolean enabled)
    {
        this.id = id;
        this.nombreCategoria = nombreCategoria;
        this.enabled = enabled;
    }
    //endregion

    //==================================================================================================================
    //region GETTERS & SETTERS
    //==================================================================================================================
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getNombreCategoria()
    {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria)
    {
        this.nombreCategoria = nombreCategoria;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
    //endregion

    //==================================================================================================================
    //region TOSTRING()
    //==================================================================================================================
    @Override
    public String toString()
    {
        return "Categoria{" +
                "id=" + id +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                ", enabled=" + enabled +
                '}';
    }
    //endregion
}
