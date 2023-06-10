package ar.com.clinica.entity;

import ar.com.clinica.service.TurnoService;

import java.time.LocalDate;

public class Paciente extends Usuario {

    private int id;
    private int dni;
    private Domicilio domicilio;
    private LocalDate fechaDeAlta;
    private TurnoService turnoService;


    public Paciente(String nombre, String apellido, String usuario, String contrasenia, int dni, Domicilio domicilio, LocalDate fechaDeAlta, TurnoService turnoService) {
        super(nombre, apellido, usuario, contrasenia);
        this.dni = dni;
        this.domicilio = domicilio;
        this.fechaDeAlta = fechaDeAlta;
        this.turnoService = turnoService;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public TurnoService getTurnoService() {
        return turnoService;
    }

    public void setTurnoService(TurnoService turnoService) {
        this.turnoService = turnoService;
    }
}
