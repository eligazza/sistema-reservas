package ar.com.clinica.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OdontologoDtoReq {

    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;

}
