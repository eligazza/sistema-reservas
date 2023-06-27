package ar.com.clinica.controller;

import ar.com.clinica.dto.response.OdontologoDtoResponse;
import ar.com.clinica.dto.request.OdontologoDtoRequest;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.service.implementations.OdontologoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoServiceImpl service;

    @GetMapping
    public ResponseEntity<List<OdontologoDtoResponse>> listar() throws ExcepcionNoHayContenido {
        List<OdontologoDtoResponse> lista = service.listarOdontologos();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDtoResponse> listarPorId(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarOdontologoPorId(id));
    }

    @PostMapping
    public ResponseEntity<OdontologoDtoResponse> guardar(@RequestBody OdontologoDtoRequest odontologoDtoRequest) throws ExcepcionParametroFaltante, ExcepcionParametroInvalido, ExcepcionDuplicado {
        OdontologoDtoResponse odontologoNuevo = service.guardarOdontologo(odontologoDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoNuevo);
    }

    @PutMapping
    public ResponseEntity<OdontologoDtoResponse> modificar(@RequestBody OdontologoDtoRequest odontologoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {
        return ResponseEntity.status(HttpStatus.OK).body(service.modificarOdontologo(odontologoDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OdontologoDtoResponse> eliminar(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminarOdontologo(id));
    }

}
