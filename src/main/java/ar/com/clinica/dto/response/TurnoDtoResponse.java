package ar.com.clinica.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDtoResponse {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;
    private OdontologoDtoResponse odontologo;
    private PacienteDtoResponse paciente;

}
