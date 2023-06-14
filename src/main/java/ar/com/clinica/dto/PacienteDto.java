package ar.com.clinica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {

    private Long id;
    private Integer dni;
    private String nombre;
    private String apellido;

}
