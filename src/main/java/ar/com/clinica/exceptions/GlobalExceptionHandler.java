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
    public ResponseEntity<RespuestaError> manejarNoHayContenido(ExcepcionNoHayContenido ex) {
        ex.printStackTrace();
        LOG.error(ex.getMessage());
        RespuestaError respuestaError = new RespuestaError(1001, "No hay contenido", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaError);
    }

    @ExceptionHandler({ExcepcionRecursoNoEncontrado.class})
    public ResponseEntity<RespuestaError> manejarRecursoNoEncontrado(ExcepcionRecursoNoEncontrado ex) {
        ex.printStackTrace();
        LOG.error(ex.getMessage());
        RespuestaError respuestaError = new RespuestaError(1002, "Recurso no encontrado" , ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuestaError);
    }

    @ExceptionHandler({ExcepcionDuplicado.class})
    public ResponseEntity<RespuestaError> manejarDuplicado(ExcepcionDuplicado ex) {
        ex.printStackTrace();
        LOG.error(ex.getMessage());
        RespuestaError respuestaError = new RespuestaError(2002, "Recurso duplicado" , ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
    }

    @ExceptionHandler({ExcepcionParametroFaltante.class})
    public ResponseEntity<RespuestaError> manejarParametroFaltante(ExcepcionParametroFaltante ex) {
        ex.printStackTrace();
        LOG.error(ex.getMessage());
        RespuestaError respuestaError = new RespuestaError(2001, "Parámetro faltante", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
    }

    @ExceptionHandler({ExcepcionParametroInvalido.class})
    public ResponseEntity<RespuestaError> manejarParametroInvalido(ExcepcionParametroInvalido ex) {
        ex.printStackTrace();
        LOG.error(ex.getMessage());
        RespuestaError respuestaError = new RespuestaError(2002, "Parámetro inválido" , ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaError);
    }

}
