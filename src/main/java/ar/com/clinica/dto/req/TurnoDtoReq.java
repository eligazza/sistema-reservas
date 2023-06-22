package ar.com.clinica.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDtoReq {

    private Long id;
    private String fecha;
    private String hora;
    private Long idOdontologo;
    private Long idPaciente;

}
