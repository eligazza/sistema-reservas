package ar.com.clinica.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RespuestaError {

    private int codigo;
    private String mensaje;

}
