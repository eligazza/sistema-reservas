package ar.com.clinica.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExcepcionParametroInvalido extends Exception {
    public ExcepcionParametroInvalido(String message) {
        super(message);
    }
}
