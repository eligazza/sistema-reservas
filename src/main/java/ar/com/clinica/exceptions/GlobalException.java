package ar.com.clinica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({ExcepcionNoHayContenido.class})
    public ResponseEntity<String> procesarNoHayContenido(ExcepcionNoHayContenido ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ExcepcionRecursoNoEncontrado.class})
    public ResponseEntity<String> procesarRecursoNoEncontrado(ExcepcionRecursoNoEncontrado ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ExcepcionParametroFaltante.class})
    public ResponseEntity<String> procesarParametroFaltante(ExcepcionParametroFaltante ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
