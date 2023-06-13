package ar.com.clinica.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String contrasenia;
    private Rol rol;

}
