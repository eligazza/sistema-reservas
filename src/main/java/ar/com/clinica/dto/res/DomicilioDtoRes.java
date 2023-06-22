package ar.com.clinica.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomicilioDtoRes {

    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

}
