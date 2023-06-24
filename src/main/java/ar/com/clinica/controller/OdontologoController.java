package ar.com.clinica.controller;

import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.req.OdontologoDtoReq;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.service.OdontologoServiceImpl;
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
    public ResponseEntity<List<OdontologoDtoRes>> listar() throws ExcepcionNoHayContenido {
        List<OdontologoDtoRes> lista = service.listarOdontologos();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDtoRes> listarPorId(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarOdontologoPorId(id));
    }

    @PostMapping
    public ResponseEntity<OdontologoDtoRes> guardar(@RequestBody OdontologoDtoReq odontologoDtoReq) throws ExcepcionParametroFaltante, ExcepcionParametroInvalido {
        OdontologoDtoRes odontologoNuevo = service.guardarOdontologo(odontologoDtoReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoNuevo);
    }

    @PutMapping
    public ResponseEntity<OdontologoDtoRes> modificar(@RequestBody OdontologoDtoReq odontologoDtoReq) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {
        return ResponseEntity.status(HttpStatus.OK).body(service.modificarOdontologo(odontologoDtoReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OdontologoDtoRes> eliminar(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminarOdontologo(id));
    }

}
