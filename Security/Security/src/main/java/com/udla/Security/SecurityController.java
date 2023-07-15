package com.udla.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

@RestController
public class SecurityController {
    @Autowired
    private UsuarioRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario credentials) {
        // Verificar las credenciales del usuario y generar el token
        if (authenticate(credentials.getUsername(), credentials.getPassword())) {
            try {
                String token = TokenService.generateToken(credentials.getUsername(), 3600000); // Token válido por 1
                                                                                               // hora
                return ResponseEntity.ok(token);
            } catch (JOSEException e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint(@RequestHeader("Authorization") String token) {
        // Validar el token en el encabezado de autorización
        try {
            if (TokenService.validateToken(token)) {
                return ResponseEntity.ok("Acceso permitido");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (ParseException | JOSEException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean authenticate(String username, String password) {
        // Buscar el usuario en la base de datos por nombre de usuario
        Usuario usuario = userRepository.findByUsername(username);

        // Verificar si el usuario existe y si la contraseña coincide
        if (usuario != null && usuario.getPassword().equals(password)) {
            return true; // Autenticación exitosa
        }

        return false; // Autenticación fallida
    }
}
