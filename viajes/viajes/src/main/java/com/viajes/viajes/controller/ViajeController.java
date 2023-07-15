package com.viajes.viajes.controller;

import com.viajes.viajes.persistence.entity.entity.Cliente;
import com.viajes.viajes.persistence.entity.entity.Viaje;
import com.viajes.viajes.service.ViajeService;
import com.viajes.viajes.service.dto.HotelInDTO;
import com.viajes.viajes.service.dto.ViajeInDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    private final ViajeService viajeService;

    @Autowired
    private RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    public ViajeController(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @PostMapping
    public Viaje createViaje(@RequestBody ViajeInDTO viajeInDTO, @RequestHeader("Authorization") String token){
        if (token != null)
        {
            headers.set("Authorization", token);
            String status = restTemplate.exchange("http://localhost:8084/protected", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                return this.viajeService.createViaje(viajeInDTO);
            }
        }
        return null;
        
    }

    @GetMapping
    public List<Viaje> findAll(@RequestHeader("Authorization") String token){
        if (token != null)
        {
            headers.set("Authorization", token);
            String status = restTemplate.exchange("http://localhost:8084/protected", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                return this.viajeService.findAllViajes();
            }
        }
        return null;
        
    }

    @DeleteMapping
    public boolean deleteViaje(ViajeInDTO viajeInDTO){
        return this.viajeService.deleteViaje(viajeInDTO);
    }
}
