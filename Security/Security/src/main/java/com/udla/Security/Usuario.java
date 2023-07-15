package com.udla.Security;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // Otros campos (si los tienes)

    // Constructor por defecto
    public Usuario() {
    }

    // Constructor con todos los campos
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Métodos getters y setters
    // ...
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Otros métodos adicionales
    // ...
}
