package com.viajes.viajes.controller;

import com.viajes.viajes.mapper.ClienteInDTOtoCliente;
import com.viajes.viajes.persistence.entity.entity.Cliente;
import com.viajes.viajes.service.ClientService;
import com.viajes.viajes.service.dto.ClienteInDTO;
import com.viajes.viajes.service.dto.HotelInDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClientService clientService;

    @Autowired
    private RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    public ClienteController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Cliente createCliente(@RequestBody ClienteInDTO clienteInDTO, @RequestHeader("Authorization") String token){
        if (token != null)
        {
            headers.set("Authorization", token);
            String status = restTemplate.exchange("http://localhost:8084/protected", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                return this.clientService.createCliente(clienteInDTO);
            }
        }
        return null;
    }

    @GetMapping
    public List<Cliente> findAll(@RequestHeader("Authorization") String token){
        if (token != null)
        {
            headers.set("Authorization", token);
            String status = restTemplate.exchange("http://localhost:8084/protected", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                return this.clientService.findAllClientes();
            }
        }
        return null;
    }

    @DeleteMapping
    public boolean deleteCliente(ClienteInDTO clienteInDTO){
        return this.clientService.deleteCliente(clienteInDTO);
    }
}

