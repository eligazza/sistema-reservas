package ar.com.clinica.controller;

import ar.com.clinica.dto.res.TurnoDtoRes;
import ar.com.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService service;


    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {

        if (service.buscarPorId(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No encontramos dicho turno, por favor revisa el ID");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody TurnoDtoRes turnoDtoRes) {

        TurnoDtoRes turnoGuardado = null;
        try {
            turnoGuardado = service.insertar(turnoDtoRes);
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(turnoGuardado);

    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody TurnoDtoRes turnoDtoRes) {

        if (service.modificar(turnoDtoRes) == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No se pudieron modificar el turno");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.modificar(turnoDtoRes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.eliminar(id) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar el turno, corrobore el ID");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminar(id));
    }

}
