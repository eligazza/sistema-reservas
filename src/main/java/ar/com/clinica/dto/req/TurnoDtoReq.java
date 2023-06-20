package ar.com.clinica.dto.req;

import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDtoReq {

    private Long id;
    private String fecha;
    private String hora;
    private OdontologoDtoRes odontologo;
    private PacienteDtoRes paciente;

}
