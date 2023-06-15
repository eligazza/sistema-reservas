package ar.com.clinica.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDtoRes {

    private Long id;
    private Integer dni;
    private String nombre;
    private String apellido;

}
