package ar.com.clinica.controller;

import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.req.OdontologoDtoReq;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService service;

    @GetMapping
    public ResponseEntity<?> listar() throws ExcepcionNoHayContenido {
        List<OdontologoDtoRes> lista = service.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OdontologoDtoRes> insertar(@RequestBody OdontologoDtoReq odontologoDtoReq) throws ExcepcionParametroFaltante {
        OdontologoDtoRes odontologoNuevo = service.insertar(odontologoDtoReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoNuevo);
    }

    @PutMapping
    public ResponseEntity<OdontologoDtoRes> modificar(@RequestBody OdontologoDtoReq odontologoDtoReq) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.modificar(odontologoDtoReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OdontologoDtoRes> eliminar(@PathVariable Long id) throws ExcepcionRecursoNoEncontrado {
        return ResponseEntity.status(HttpStatus.OK).body(service.eliminar(id));
    }

}
