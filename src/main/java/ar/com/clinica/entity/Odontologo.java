package ar.com.clinica.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;

}
