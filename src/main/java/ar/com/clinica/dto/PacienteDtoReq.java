package ar.com.clinica.dto;

import ar.com.clinica.entity.Domicilio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDtoReq {

    private Long id;
    private Integer dni;
    private String nombre;
    private String apellido;
    private String fechaDeAlta;
    private Domicilio domicilio;

}