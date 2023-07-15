package com.notify.notificator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RestController
public class MailNotificador {

    @Autowired
    SendMailService mailService;

    @Autowired
    private RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public String sendMail(@RequestBody MailForm data, @RequestHeader("Authorization") String token)
    {
        if (token != null)
        {
            headers.set("Authorization", token);
            String status = restTemplate.exchange("http://localhost:8084/protected", HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
            System.out.println(status);
            if (status != null && status.equals("Acceso permitido"))
            {
                String message = data.getBody() + "\n\n Datos de contacto: " + "\nNombre: " + data.getName() + "\nE-mail: " + data.getEmail();
                mailService.sendMail("NotificadorTRAVELec@gmail.com",data.getEmail(), data.getSubject(), message);
            } 
        }


        return "correo enviado";
    }        
}
