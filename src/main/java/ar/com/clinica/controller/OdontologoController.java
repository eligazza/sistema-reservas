package ar.com.clinica.controller;

import ar.com.clinica.dto.OdontologoDto;
import ar.com.clinica.entity.Odontologo;
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
    private OdontologoService service;

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
    public ResponseEntity<OdontologoDto> insertar(@RequestBody OdontologoDto odontologoDto) {
        if (service.insertar(odontologoDto) == null) {
            return new ResponseEntity<>(service.insertar(odontologoDto), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.insertar(odontologoDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OdontologoDto> modificar(@RequestBody OdontologoDto odontologoDto) {
        if (service.modificar(odontologoDto) == null) {
            return new ResponseEntity<>(service.modificar(odontologoDto), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.modificar(odontologoDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OdontologoDto> eliminar(@PathVariable Long id) {
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }

}
