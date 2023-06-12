package ar.com.clinica.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String calle;

    @Column
    private int numero;

    @Column(length = 50)
    private String localidad;

    @Column(length = 50)
    private String provincia;

    @OneToMany
    @JoinColumn(name = "ID")
    private List<Paciente> pacientes = new ArrayList<>();

    public Domicilio(){}
    public Domicilio(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
