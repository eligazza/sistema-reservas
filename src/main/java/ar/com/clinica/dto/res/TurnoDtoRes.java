package ar.com.clinica.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TurnoDtoRes {

    private Long id;
    private String fecha;
    private OdontologoDtoRes odontologo;
    private PacienteDtoRes paciente;

}
