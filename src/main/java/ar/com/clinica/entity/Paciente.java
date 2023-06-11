package ar.com.clinica.entity;

import jakarta.persistence.*;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dni;
    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @ManyToOne
    @JoinColumn
    private Domicilio domicilio;

    @Column
    private String fechaDeAlta;



    public Paciente() {}
    public Paciente(Integer dni, String nombre, String apellido, Domicilio domicilio, String fechaDeAlta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.fechaDeAlta = fechaDeAlta;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(String fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

}
