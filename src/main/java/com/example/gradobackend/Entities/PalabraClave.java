package com.example.gradobackend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "palabra-clave")
public class PalabraClave
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "palabra")
    private String palabra;

    @Column(name = "enabled")
    private boolean enabled;

    //==================================================================================================================
    //region CONSTRUCTORES
    //==================================================================================================================
    public PalabraClave() { }

    public PalabraClave(long id, String palabra, boolean enabled)
    {
        this.id = id;
        this.palabra = palabra;
        this.enabled = enabled;
    }
    //endregion

    //==================================================================================================================
    //region GETTERS & SETTERS
    //==================================================================================================================
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getPalabra() { return palabra; }

    public void setPalabra(String palabra) { this.palabra = palabra; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    //endregion

    //==================================================================================================================
    //region TOSTRING()
    //==================================================================================================================

    @Override
    public String toString()
    {
        return "PalabraClave{" +
                "id=" + id +
                ", palabra='" + palabra + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    //endregion
}
