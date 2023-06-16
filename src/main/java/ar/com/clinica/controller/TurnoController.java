package ar.com.clinica.controller;

import ar.com.clinica.dto.res.TurnoDtoRes;
import ar.com.clinica.exceptions.*;
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
    public ResponseEntity<List<TurnoDtoRes>> listar() throws ExcepcionNoHayContenido {
        return ResponseEntity.status(HttpStatus.OK).body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDtoRes> listarPorId(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TurnoDtoRes> insertar(@RequestBody TurnoDtoRes turnoDtoRes) throws ExcepcionParametroFaltante {
        TurnoDtoRes turnoNuevo = service.insertar(turnoDtoRes);
        return ResponseEntity.status(HttpStatus.OK).body(turnoNuevo);
    }

    @PutMapping
    public ResponseEntity<TurnoDtoRes> modificar(@RequestBody TurnoDtoRes turnoDtoRes) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.modificar(turnoDtoRes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TurnoDtoRes> eliminar(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminar(id));
    }

}
