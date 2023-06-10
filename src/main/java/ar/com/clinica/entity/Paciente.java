package ar.com.clinica.entity;

import ar.com.clinica.service.TurnoService;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int dni;
    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @Column(length = 50)
    private String usuario;

    @Column(length = 50)
    private String contrasenia;

    @ManyToOne(fetch = FetchType.LAZY)
    private Domicilio domicilio;

    @Column
    private LocalDate fechaDeAlta;


    private TurnoService turnoService;






}
