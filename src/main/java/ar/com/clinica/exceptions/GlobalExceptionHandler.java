package ar.com.clinica.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger LOG = LogManager.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler({ExcepcionNoHayContenido.class})
    public ResponseEntity<String> manejarNoHayContenido(ExcepcionNoHayContenido ex) {
        ex.printStackTrace();
        LOG.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ExcepcionRecursoNoEncontrado.class})
    public ResponseEntity<String> manejarRecursoNoEncontrado(ExcepcionRecursoNoEncontrado ex) {
        ex.printStackTrace();
        LOG.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({ExcepcionParametroFaltante.class})
    public ResponseEntity<String> manejarParametroFaltante(ExcepcionParametroFaltante ex) {
        ex.printStackTrace();
        LOG.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
