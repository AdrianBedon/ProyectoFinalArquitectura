package com.aerolinea.vuelos.repository;

import com.aerolinea.vuelos.models.Aerolinea_Postgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AerolineaRepository extends JpaRepository<Aerolinea_Postgres, Long> {
    
}
