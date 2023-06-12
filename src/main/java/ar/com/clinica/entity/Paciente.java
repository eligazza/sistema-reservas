package ar.com.clinica.entity;

import jakarta.persistence.*;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

}
