package ar.com.clinica.entity;

import ar.com.clinica.service.OdontologoServiceImp;
import ar.com.clinica.service.PacienteServiceImp;
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


    public Administrador(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
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

}
