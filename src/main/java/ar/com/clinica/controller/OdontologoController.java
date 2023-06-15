package ar.com.clinica.controller;

import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.req.OdontologoDtoReq;
import ar.com.clinica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    OdontologoService service;

    @GetMapping
    public ResponseEntity<?> listar() {

        if (service.listar() == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existen odontologo para listar");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {

        if (service.buscarPorId(id) == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No encontramos dicho odontologo, por favor revisa el ID");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OdontologoDtoRes> insertar(@RequestBody OdontologoDtoReq odontologoDtoReq) {
        return new ResponseEntity<>(service.insertar(odontologoDtoReq), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OdontologoDtoRes> modificar(@RequestBody OdontologoDtoReq odontologoDtoReq) {
        if (service.modificar(odontologoDtoReq) == null) {
            return new ResponseEntity<>(service.modificar(odontologoDtoReq), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.modificar(odontologoDtoReq), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OdontologoDtoRes> eliminar(@PathVariable Long id) {
        return new ResponseEntity<>(service.eliminar(id), HttpStatus.OK);
    }

}
