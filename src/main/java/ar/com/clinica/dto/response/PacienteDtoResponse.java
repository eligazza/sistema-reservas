package ar.com.clinica.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDtoResponse {

    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaDeAlta;
    private DomicilioDtoResponse domicilio;

}
