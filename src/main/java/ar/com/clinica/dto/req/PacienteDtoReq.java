package ar.com.clinica.dto.req;

import ar.com.clinica.dto.res.DomicilioDtoRes;
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
    private DomicilioDtoRes domicilio;

}