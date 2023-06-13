package ar.com.clinica.controller;

import ar.com.clinica.dto.TurnoDto;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService service;


    @GetMapping
    public ResponseEntity<List<TurnoDto>> listar() {
        if (service.listar() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> listarPorId(@PathVariable Long id) {
        if (service.buscarPorId(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.buscarPorId(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TurnoDto> insertar(@RequestBody Turno turno) {
        if (service.insertar(turno) == null) {
            return new ResponseEntity<>(service.insertar(turno), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.insertar(turno), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TurnoDto> modificar(@RequestBody Turno turno) {
        if (service.modificar(turno) == null) {
            return new ResponseEntity<>(service.modificar(turno), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.modificar(turno), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TurnoDto> eliminar(@PathVariable Long id) {
        if (service.eliminar(id) == null) {
            return new ResponseEntity<>(service.eliminar(id), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }

}
