package ar.com.clinica.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDtoResponse {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha;
    private OdontologoDtoResponse odontologo;
    private PacienteDtoResponse paciente;

}
