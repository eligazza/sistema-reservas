package ar.com.clinica.entity;

import ar.com.clinica.service.OdontologoService;
import ar.com.clinica.service.PacienteService;
import jakarta.persistence.*;


@Entity
public class Administrador extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    private OdontologoService odontologoService;

    private PacienteService pacienteService;


    public Administrador(String nombre, String apellido, OdontologoService odontologoService, PacienteService pacienteService) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public OdontologoService getOdontologoService() {
        return odontologoService;
    }

    public void setOdontologoService(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    public PacienteService getPacienteService() {
        return pacienteService;
    }

    public void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
}
