package com.example.gradobackend.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "privilegio")
public class Privilegio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre_privilegio")
    private String nombrePrivilegio;

    @Column(name = "enabled")
    private boolean enabled;

    //==================================================================================================================
    //region CONSTRUCTORES
    //==================================================================================================================
    public Privilegio() { }

    public Privilegio(long id, String nombrePrivilegio, boolean enabled)
    {
        this.id = id;
        this.nombrePrivilegio = nombrePrivilegio;
        this.enabled = enabled;
    }
    //endregion

    //==================================================================================================================
    //region GETTERS & SETTERS
    //==================================================================================================================
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNombrePrivilegio() { return nombrePrivilegio; }

    public void setNombrePrivilegio(String nombrePrivilegio) { this.nombrePrivilegio = nombrePrivilegio; }

    public boolean isEnabled() { return enabled; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    //endregion

    //==================================================================================================================
    //region TOSTRING()
    //==================================================================================================================
    @Override
    public String toString()
    {
        return "Privilegio{" +
                "id=" + id +
                ", nombrePrivilegio='" + nombrePrivilegio + '\'' +
                ", enabled=" + enabled +
                '}';
    }
    //endregion
}
