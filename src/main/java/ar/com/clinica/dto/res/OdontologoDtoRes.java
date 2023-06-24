package ar.com.clinica.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoDtoRes {

    private Long id;
    private String nombre;
    private String apellido;
    private Integer matricula;

}
