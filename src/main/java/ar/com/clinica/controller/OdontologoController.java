package ar.com.clinica.controller;

import ar.com.clinica.dto.OdontologoDto;
import ar.com.clinica.dto.OdontologoDtoReq;
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
    public ResponseEntity<List<OdontologoDto>> listar() {
        if (service.listar() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> listarPorId(@PathVariable Long id) {
        if (service.buscarPorId(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OdontologoDto> insertar(@RequestBody OdontologoDtoReq odontologoDtoReq) {
        if (service.insertar(odontologoDtoReq) == null) {
            return new ResponseEntity<>(service.insertar(odontologoDtoReq), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.insertar(odontologoDtoReq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OdontologoDto> modificar(@RequestBody OdontologoDtoReq odontologoDtoReq) {
        if (service.modificar(odontologoDtoReq) == null) {
            return new ResponseEntity<>(service.modificar(odontologoDtoReq), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.modificar(odontologoDtoReq), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OdontologoDto> eliminar(@PathVariable Long id) {
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }

}
