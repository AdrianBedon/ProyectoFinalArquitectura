package com.aerolinea.vuelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import static com.aerolinea.vuelos.VuelosApplication.NAMESPACE_URI;

import java.util.ArrayList;
import java.util.List;

import com.aerolineas.aerolinea_soap.Aerolinea;
import com.aerolineas.aerolinea_soap.GetAirlineByIdRequest;
import com.aerolineas.aerolinea_soap.GetAirlineByIdResponse;
import com.aerolineas.aerolinea_soap.GetAllFlightsRequest;
import com.aerolineas.aerolinea_soap.GetAllFlightsResponse;
import com.aerolineas.aerolinea_soap.Vuelo;

import jakarta.servlet.http.HttpServletRequest;

import com.aerolinea.vuelos.models.Aerolinea_Postgres;
import com.aerolinea.vuelos.models.Vuelo_Postgres;
import com.aerolinea.vuelos.repository.AerolineaRepository;
import com.aerolinea.vuelos.repository.VueloRepository;
@Endpoint
public class VuelosEndpoint {

    private static final String AUTH_HEADER = "Authorization";

    @Autowired
    private AerolineaRepository aerolineaRepository;
    @Autowired
    private VueloRepository vueloRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;

    HttpHeaders headers = new HttpHeaders();

    private final HttpServletRequest servletRequest;

    public VuelosEndpoint(HttpServletRequest request)
    {
        this.servletRequest = request;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAirlineByIdRequest")
    @ResponsePayload
    public GetAirlineByIdResponse getAirlineById(@RequestPayload GetAirlineByIdRequest request) {
        String auth  = servletRequest.getHeader(AUTH_HEADER);
        System.out.println(auth);
        if (auth != null && !auth.isEmpty()) 
        {
            headers.set(AUTH_HEADER, auth); 
            String status = restTemplate.exchange(environment.getProperty("validador"), HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                GetAirlineByIdResponse response = new GetAirlineByIdResponse();
                long id = request.getIdAerolinea();
                Aerolinea_Postgres aero = aerolineaRepository.findById(id).get();
                Aerolinea aeroResponse = new Aerolinea();
                aeroResponse.setIdAerolinea(aero.getIdAerolinea());
                aeroResponse.setNombre(aero.getNombre());
                aeroResponse.setPais(aero.getPais());
                response.setAerolinea(aeroResponse);
                return response;
            }
        }
        return null;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllFlightsRequest")
    @ResponsePayload
    public GetAllFlightsResponse getAllFlihgts(@RequestPayload GetAllFlightsRequest request) {
        String auth  = servletRequest.getHeader(AUTH_HEADER);
        System.out.println(auth);
        if (auth != null && !auth.isEmpty()) 
        {
            headers.set(AUTH_HEADER, auth);
            String status = restTemplate.exchange(environment.getProperty("validador"), HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                GetAllFlightsResponse response = new GetAllFlightsResponse();
                List<Vuelo_Postgres> vuelos = vueloRepository.findAll();
                List<Vuelo> vuelosf = new ArrayList<Vuelo>();
                for (Vuelo_Postgres vuelo : vuelos)
                {
                    Vuelo post = new Vuelo();
                    post.setIdVuelo(vuelo.getIdVuelo());
                    post.setNumero(vuelo.getNumero());
                    post.setIdAerolinea(vuelo.getIdAerolinea());
                    post.setFechaLlegada(vuelo.getFechaLlegada().toString());
                    post.setFechaSalida(vuelo.getFechaSalida().toString());
                    post.setIdAeropuertoLlegada(vuelo.getIdAeropuertoLlegada());
                    post.setIdAeropuertoSalida(vuelo.getIdAeropuertoSalida());
                    post.setIdAvion(vuelo.getIdAvion());
                    vuelosf.add(post);
                }
                response.setVuelos(vuelosf);
                return response;
            }
        }
        return null;
    }
}
