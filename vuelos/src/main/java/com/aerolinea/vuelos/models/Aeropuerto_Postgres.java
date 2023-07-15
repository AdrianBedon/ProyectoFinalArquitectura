package com.aerolinea.vuelos.models;

import jakarta.persistence.*;

public class Aeropuerto_Postgres {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAeropuerto;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String pais;
}
