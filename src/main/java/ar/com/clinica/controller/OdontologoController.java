package ar.com.clinica.controller;

import ar.com.clinica.dtos.request.Odontologo;
import ar.com.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    @Autowired
    private OdontologoService service;

    @GetMapping
    public ResponseEntity<List<Odontologo>> listar() {
        if (service.listar() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> listarPorId(@PathVariable int id) {
        if (service.listarPorId(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.listarPorId(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Odontologo> agregar(@RequestBody Odontologo odontologo) {
        if (service.insertar(odontologo) == null) {
            return new ResponseEntity<>(service.insertar(odontologo), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.insertar(odontologo), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Odontologo> modificar(@RequestBody Odontologo odontologo) {
        if (service.modificar(odontologo) == null) {
            return new ResponseEntity<>(service.modificar(odontologo), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.modificar(odontologo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Odontologo> eliminar(@PathVariable int id) {
        if (service.eliminar(id) == null) {
            return new ResponseEntity<>(service.eliminar(id), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }

}
