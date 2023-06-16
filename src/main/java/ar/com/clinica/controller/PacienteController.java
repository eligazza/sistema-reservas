package ar.com.clinica.controller;

import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.dto.req.PacienteDtoReq;
import ar.com.clinica.exceptions.*;
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
    PacienteService service;


    @GetMapping
    public ResponseEntity<List<PacienteDtoRes>> listar() throws ExcepcionNoHayContenido {
        List<PacienteDtoRes> lista = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDtoRes> listarPorId(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    /*
    @GetMapping(params = "apellido")
    public ResponseEntity<?> listarPorApellido(@RequestParam("apellido") String apellido) {
        return service.buscarPorApellido(apellido)
    }
     */

    @PostMapping
    public ResponseEntity<PacienteDtoRes> insertar(@RequestBody PacienteDtoReq pacienteDtoReq) throws ExcepcionParametroFaltante {
        PacienteDtoRes pacienteNuevo = service.insertar(pacienteDtoReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteNuevo);
    }

    @PutMapping
    public ResponseEntity<PacienteDtoRes> modificar(@RequestBody PacienteDtoReq pacienteDtoReq) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.modificar(pacienteDtoReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDtoRes> eliminar(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminar(id));
    }

}
