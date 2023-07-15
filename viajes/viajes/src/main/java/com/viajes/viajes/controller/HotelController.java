package com.viajes.viajes.controller;

import com.viajes.viajes.persistence.entity.entity.Cliente;
import com.viajes.viajes.persistence.entity.entity.Hotel;
import com.viajes.viajes.service.HotelService;
import com.viajes.viajes.service.dto.HotelInDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public Hotel createHotel(@RequestBody HotelInDTO hotelInDTO, @RequestHeader("Authorization") String token){
        if (token != null)
        {
            headers.set("Authorization", token);
            String status = restTemplate.exchange("http://localhost:8084/protected", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                return this.hotelService.createHotel(hotelInDTO);
            }
        }
        return null;
        
    }

    @GetMapping
    public List<Hotel> findAll(@RequestHeader("Authorization") String token){
        if (token != null)
        {
            headers.set("Authorization", token);
            String status = restTemplate.exchange("http://localhost:8084/protected", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                return this.hotelService.findAllHoteles();
            }
        }
        return null;
    }

    @DeleteMapping
    public boolean deleteHotel(HotelInDTO hotelInDTO){
        return this.hotelService.deleteHotel(hotelInDTO);
    }
}
