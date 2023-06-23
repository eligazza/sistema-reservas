package ar.com.clinica.dto.req;

import ar.com.clinica.dto.res.DomicilioDtoRes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDtoReq {

    private Long id;
    private Integer dni;
    private String nombre;
    private String apellido;
    private Date fechaDeAlta;
    private DomicilioDtoRes domicilio;

}