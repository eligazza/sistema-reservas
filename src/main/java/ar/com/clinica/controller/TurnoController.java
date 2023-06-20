package ar.com.clinica.controller;

import ar.com.clinica.dto.req.TurnoDtoReq;
import ar.com.clinica.dto.res.TurnoDtoRes;
import ar.com.clinica.entity.Turno;
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
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDtoRes> listarPorId(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTurnoPorId(id));
    }

    @PostMapping
    public ResponseEntity<TurnoDtoRes> guardar(@RequestBody TurnoDtoReq turnoDtoReq) throws ExcepcionRecursoNoEncontrado {
        TurnoDtoRes turnoNuevo = service.guardarTurno(turnoDtoReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoNuevo);
    }

    @PutMapping
    public ResponseEntity<TurnoDtoRes> modificar(@RequestBody TurnoDtoReq turnoDtoReq) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.modificarTurno(turnoDtoReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TurnoDtoRes> eliminar(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminarTurno(id));
    }

}
