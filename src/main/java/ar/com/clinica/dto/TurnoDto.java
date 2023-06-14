package ar.com.clinica.dto;

import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TurnoDto {

    private Long id;
    private String fecha;
    private Odontologo odontologo;
    private Paciente paciente;

}
