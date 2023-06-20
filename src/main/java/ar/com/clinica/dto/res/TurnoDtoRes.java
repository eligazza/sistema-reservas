package ar.com.clinica.dto.res;

import ar.com.clinica.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class TurnoDtoRes {

    private Long id;
    private String fecha;
    private OdontologoDtoRes odontologo;
    private Paciente paciente;

}
