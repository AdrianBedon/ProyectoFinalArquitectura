package com.aerolinea.vuelos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Aerolinea")
public class Aerolinea_Postgres {
    private long idAerolinea;
    public String nombre;
    public String pais;

    public Aerolinea_Postgres()
    {

    }


    public Aerolinea_Postgres(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdAerolinea() {
        return this.idAerolinea;
    }

    public void setIdAerolinea(long idAerolinea) {
        this.idAerolinea = idAerolinea;
    }

    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "pais", nullable = false)
    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

}
