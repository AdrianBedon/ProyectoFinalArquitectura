package com.aerolinea.vuelos.repository;

import com.aerolinea.vuelos.models.Vuelo_Postgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo_Postgres, Long>{
    
}
