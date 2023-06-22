package ar.com.clinica.dto.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDtoRes {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date fecha;
    private OdontologoDtoRes odontologo;
    private PacienteDtoRes paciente;

}
