package ar.com.clinica.controller;

import ar.com.clinica.dto.response.PacienteDtoResponse;
import ar.com.clinica.dto.request.PacienteDtoRequest;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.service.implementations.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteServiceImpl service;


    @GetMapping
    public ResponseEntity<List<PacienteDtoResponse>> listar() throws ExcepcionNoHayContenido {
        List<PacienteDtoResponse> lista = service.listarPacientes();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDtoResponse> listarPorId(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPacientePorId(id));
    }

    /*
    @GetMapping(params = "apellido")
    public ResponseEntity<?> listarPorApellido(@RequestParam("apellido") String apellido) {
        return service.buscarPorApellido(apellido)
    }
    */

    @PostMapping
    public ResponseEntity<PacienteDtoResponse> guardar(@RequestBody PacienteDtoRequest pacienteDtoRequest) throws ExcepcionParametroFaltante {
        PacienteDtoResponse pacienteNuevo = service.guardarPaciente(pacienteDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteNuevo);
    }

    @PutMapping
    public ResponseEntity<PacienteDtoResponse> modificar(@RequestBody PacienteDtoRequest pacienteDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {
        return ResponseEntity.status(HttpStatus.OK).body(service.modificarPaciente(pacienteDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDtoResponse> eliminar(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminarPaciente(id));
    }

}
