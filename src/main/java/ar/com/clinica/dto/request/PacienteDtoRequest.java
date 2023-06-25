package ar.com.clinica.dto.request;

import ar.com.clinica.dto.response.DomicilioDtoResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDtoRequest {

    private Long id;
    private Integer dni;
    private String nombre;
    private String apellido;
    private Date fechaDeAlta;
    private DomicilioDtoResponse domicilio;

}