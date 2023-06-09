package ar.com.clinica.controllers;

import ar.com.clinica.dtos.request.PacienteDtoReq;
import ar.com.clinica.dtos.response.PacienteDtoRes;
import ar.com.clinica.services.PacienteService;
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
    public ResponseEntity<List<PacienteDtoRes>> listar() {
        if (service.listar() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDtoRes> listarPorId(@PathVariable int id) {
        if (service.listarPorId(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.listarPorId(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PacienteDtoRes> insertar(@RequestBody PacienteDtoReq paciente) {
        if (service.agregar(paciente) == null) {
            return new ResponseEntity<>(service.agregar(paciente), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.agregar(paciente), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PacienteDtoRes> modificar(@RequestBody PacienteDtoReq paciente) {
        if (service.modificar(paciente) == null) {
            return new ResponseEntity<>(service.modificar(paciente), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.modificar(paciente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDtoRes> eliminar(@PathVariable int id) {
        if (service.eliminar(id) == null) {
            return new ResponseEntity<>(service.eliminar(id), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }

}
