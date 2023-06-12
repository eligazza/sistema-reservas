package ar.com.clinica.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Odontologo odontologo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    @Column
    private String fecha;


    public Turno(Odontologo odontologo, Paciente paciente, String fecha) {
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.fecha = fecha;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
