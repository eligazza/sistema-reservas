package ar.com.clinica.entity;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String usuario;

    @Column
    private String contrasenia;

    @Column
    private String rol;

}
