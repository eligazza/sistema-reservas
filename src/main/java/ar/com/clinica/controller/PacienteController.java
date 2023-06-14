package ar.com.clinica.controller;


import ar.com.clinica.dto.PacienteDto;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;


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

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody Paciente paciente) {

        PacienteDto pacienteInsertado = null;
        try {
             pacienteInsertado = service.insertar(paciente);
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteInsertado);
    }

    @PutMapping
    public ResponseEntity<?> modificar(@RequestBody Paciente paciente) {

        if (service.modificar(paciente) == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("No se pudieron actualizar los datos");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.modificar(paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.eliminar(id) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo eliminar el paciente, corrobore el ID");
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminar(id));
    }

}
