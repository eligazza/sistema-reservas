package ar.com.clinica.controller;

import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.dto.req.PacienteDtoReq;
import ar.com.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService service;


    @GetMapping
    public ResponseEntity<?> listar() {

        if (service.listar() == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existen pacientes para listar");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {

        if (service.buscarPorId(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No encontramos dicho paciente, por favor revisa el ID");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.buscarPorId(id));
    }

    /*
    @GetMapping(params = "apellido")
    public ResponseEntity<?> listarPorApellido(@RequestParam("apellido") String apellido) {
        return service.buscarPorApellido(apellido)
    }
     */

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody PacienteDtoReq pacienteDtoReq) {

        PacienteDtoRes pacienteDtoRes = null;

        try {
             pacienteDtoRes = service.insertar(pacienteDtoReq);
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteDtoRes);
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody PacienteDtoReq pacienteDtoReq) {

        if (service.modificar(pacienteDtoReq) == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No se pudieron actualizar los datos");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.modificar(pacienteDtoReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.eliminar(id) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar el paciente, corrobore el ID");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminar(id));
    }

}
