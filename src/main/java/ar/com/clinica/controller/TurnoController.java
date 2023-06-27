package ar.com.clinica.controller;

import ar.com.clinica.dto.request.TurnoDtoRequest;
import ar.com.clinica.dto.response.TurnoDtoResponse;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.service.implementations.TurnoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoServiceImpl service;


    @GetMapping
    public ResponseEntity<List<TurnoDtoResponse>> listar() throws ExcepcionNoHayContenido {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDtoResponse> listarPorId(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarTurnoPorId(id));
    }

    @GetMapping(params = "paciente-id")
    public ResponseEntity<List<TurnoDtoResponse>> listarPorPacienteId(@RequestParam("paciente-id") Long id) throws ExcepcionNoHayContenido, ExcepcionParametroInvalido {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTurnosPorPacienteId(id));
    }

    @GetMapping(params = "odontologo-id")
    public ResponseEntity<List<TurnoDtoResponse>> listarPorOdontologoId(@RequestParam("odontologo-id") Long id) throws ExcepcionNoHayContenido, ExcepcionParametroInvalido {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTurnosPorOdontologoId(id));
    }

    @PostMapping
    public ResponseEntity<TurnoDtoResponse> guardar(@RequestBody TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroInvalido, ExcepcionParametroFaltante, ExcepcionDuplicado {
        TurnoDtoResponse turnoNuevo = service.guardarTurno(turnoDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoNuevo);
    }

    @PutMapping
    public ResponseEntity<TurnoDtoResponse> modificar(@RequestBody TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {
        return ResponseEntity.status(HttpStatus.OK).body(service.modificarTurno(turnoDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TurnoDtoResponse> eliminar(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminarTurno(id));
    }

}
