package ar.com.clinica.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExcepcionNoHayContenido extends Exception {
    public ExcepcionNoHayContenido(String message) {
        super(message);
    }
}
