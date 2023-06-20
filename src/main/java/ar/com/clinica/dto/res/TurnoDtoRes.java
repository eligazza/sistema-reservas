package ar.com.clinica.dto.res;

import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter

public class TurnoDtoRes {

    private Long id;
    private String fecha;
    private String hora;
    private OdontologoDtoRes odontologo;
    private PacienteDtoRes paciente;

}
