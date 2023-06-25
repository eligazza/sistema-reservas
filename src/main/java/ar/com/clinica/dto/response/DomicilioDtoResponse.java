package ar.com.clinica.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioDtoResponse {

    private Long id;
    private String calle;
    private int numero;
    private String localidad;
    private String provincia;

}
