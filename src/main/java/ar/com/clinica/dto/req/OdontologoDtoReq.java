package ar.com.clinica.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoDtoReq {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

}
