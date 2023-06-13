package ar.com.clinica.controller;


import ar.com.clinica.dto.PacienteDto;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<PacienteDto>> listar() {
        if (service.listar() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> listarPorId(@PathVariable Long id) {
        if (service.buscarPorId(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteDto> insertar(@RequestBody Paciente paciente) {
        if (service.insertar(paciente) == null) {
            return new ResponseEntity<>(service.insertar(paciente), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.insertar(paciente), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PacienteDto> modificar(@RequestBody Paciente paciente) {
        if (service.modificar(paciente) == null) {
            return new ResponseEntity<>(service.modificar(paciente), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.modificar(paciente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDto> eliminar(@PathVariable Long id) {
        if (service.eliminar(id) == null) {
            return new ResponseEntity<>(service.eliminar(id), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }

}
