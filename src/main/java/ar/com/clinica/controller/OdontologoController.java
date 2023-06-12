package ar.com.clinica.controller;

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
    public ResponseEntity<List<Odontologo>> listar() {
        if (service.listar() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> listarPorId(@PathVariable Long id) {
        if (service.buscarPorId(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Odontologo> insertar(@RequestBody Odontologo odontologo) {
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
    public ResponseEntity<Odontologo> eliminar(@PathVariable Long id) {
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }

}
