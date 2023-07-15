package com.aerolinea.vuelos.models;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "Vuelo")
public class Vuelo_Postgres {
    private long idVuelo;
    private String numero;
    private Timestamp fechaSalida;
    private Timestamp fechaLlegada;
    private long idAerolinea;
    private long idAeropuertoSalida;
    private long idAeropuertoLlegada;
    private long idAvion;

    public Vuelo_Postgres()
    {

    }

    public Vuelo_Postgres(String numero, Timestamp fechaSalida, Timestamp fechaLlegada, long idAerolinea, long idAeropuertoSalida, long idAeropuertoLlegada, long idAvion) {
        this.numero = numero;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.idAerolinea = idAerolinea;
        this.idAeropuertoSalida = idAeropuertoSalida;
        this.idAeropuertoLlegada = idAeropuertoLlegada;
        this.idAvion = idAvion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdVuelo() {
        return this.idVuelo;
    }

    public void setIdVuelo(long idVuelo) {
        this.idVuelo = idVuelo;
    }

    @Column(name = "numero", nullable = false)
    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(name = "fechaSalida", nullable = false)
    public Timestamp getFechaSalida() {
        return this.fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Column(name = "fechaLlegada", nullable = false)
    public Timestamp getFechaLlegada() {
        return this.fechaLlegada;
    }

    public void setFechaLlegada(Timestamp fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    @Column(name = "idAerolinea", nullable = false)
    public long getIdAerolinea() {
        return this.idAerolinea;
    }

    public void setIdAerolinea(long idAerolinea) {
        this.idAerolinea = idAerolinea;
    }

    @Column(name = "idAeropuertoSalida", nullable = false)
    public long getIdAeropuertoSalida() {
        return this.idAeropuertoSalida;
    }

    public void setIdAeropuertoSalida(long idAeropuertoSalida) {
        this.idAeropuertoSalida = idAeropuertoSalida;
    }

    @Column(name = "idAeropuertoLlegada", nullable = false)
    public long getIdAeropuertoLlegada() {
        return this.idAeropuertoLlegada;
    }

    public void setIdAeropuertoLlegada(long idAeropuertoLlegada) {
        this.idAeropuertoLlegada = idAeropuertoLlegada;
    }

    @Column(name = "idAvion", nullable = false)
    public long getIdAvion() {
        return this.idAvion;
    }

    public void setIdAvion(long idAvion) {
        this.idAvion = idAvion;
    }
}
