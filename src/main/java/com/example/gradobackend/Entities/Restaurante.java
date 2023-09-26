package com.example.gradobackend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurante")
public class Restaurante
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_restaurante")
    private String nombreRestaurante;

    @Column(name = "direccion_restaurante")
    private String direccionRestaurante;

    @Column(name = "coordenadas")
    private String coordenadas;

    @Column(name = "enabled")
    private boolean enabled;

    //==================================================================================================================
    //region CONSTRUCTORES
    //==================================================================================================================
    public Restaurante() { }

    public Restaurante(long id, String nombreRestaurante, String direccionRestaurante, String coordenadas, boolean enabled)
    {
        this.id = id;
        this.nombreRestaurante = nombreRestaurante;
        this.direccionRestaurante = direccionRestaurante;
        this.coordenadas = coordenadas;
        this.enabled = enabled;
    }
    //endregion

    //==================================================================================================================
    //region GETTERS & SETTERS
    //==================================================================================================================
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNombreRestaurante() { return nombreRestaurante; }

    public void setNombreRestaurante(String nombreRestaurante) { this.nombreRestaurante = nombreRestaurante; }

    public String getDireccionRestaurante() { return direccionRestaurante; }

    public void setDireccionRestaurante(String direccionRestaurante) { this.direccionRestaurante = direccionRestaurante; }

    public String getCoordenadas() { return coordenadas; }

    public void setCoordenadas(String coordenadas) { this.coordenadas = coordenadas; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    //endregion

    //==================================================================================================================
    //region TOSTRING()
    //==================================================================================================================
    @Override
    public String toString()
    {
        return "Restaurante{" +
                "id=" + id +
                ", nombreRestaurante='" + nombreRestaurante + '\'' +
                ", direccionRestaurante='" + direccionRestaurante + '\'' +
                ", coordenadas='" + coordenadas + '\'' +
                ", enabled=" + enabled +
                '}';
    }
    //endregion
}
