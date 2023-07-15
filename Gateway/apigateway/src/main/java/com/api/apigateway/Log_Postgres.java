package com.api.apigateway;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "logs")
public class Log_Postgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLog;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created;


    public Log_Postgres() {
    }


    public Log_Postgres(String origin, String endpoint) {
        this.origin = origin;
        this.endpoint = endpoint;
    }



    public Long getId() {
        return this.idLog;
    }

    public void setId(Long id) {
        this.idLog = id;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    
}
