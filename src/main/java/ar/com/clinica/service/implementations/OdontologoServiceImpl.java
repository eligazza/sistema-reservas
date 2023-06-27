package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.response.OdontologoDtoResponse;
import ar.com.clinica.dto.request.OdontologoDtoRequest;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.IOdontologoRepository;
import ar.com.clinica.service.interfaces.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OdontologoServiceImpl implements IOdontologoService {

    @Autowired
    IOdontologoRepository repository;
    @Autowired
    ObjectMapper mapper;


    @Override
    public List<OdontologoDtoResponse> listarOdontologos() throws ExcepcionNoHayContenido {

        if (repository.findAll().size() == 0) {
            throw new ExcepcionNoHayContenido("No existen odontólogos registrados");
        } else {
            return repository.findAll()
                    .stream()
                    .map(odontologo -> mapper.convertValue(odontologo, OdontologoDtoResponse.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public OdontologoDtoResponse buscarOdontologoPorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        if (id == null) {
            throw new ExcepcionParametroFaltante("Debes elegir un odontólogo");
        } else if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al odontólogo con el ID: " + id);
        } else {
            return mapper.convertValue(repository.findById(id).get(), OdontologoDtoResponse.class);
        }
    }

    @Override
    public OdontologoDtoResponse guardarOdontologo(OdontologoDtoRequest odontologoDtoRequest) throws ExcepcionParametroFaltante, ExcepcionParametroInvalido, ExceptionDuplicado {

        if (odontologoDtoRequest.getApellido().isBlank() || odontologoDtoRequest.getApellido().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo apellido");
        } else if (odontologoDtoRequest.getNombre().isBlank() || odontologoDtoRequest.getNombre().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo nombre");
        } else if (odontologoDtoRequest.getMatricula() == null) {
            throw new ExcepcionParametroFaltante("Debe especificar la matrícula");
        } else if (odontologoDtoRequest.getMatricula() < 1) {
            throw new ExcepcionParametroInvalido("La matrícula debe ser numérica y mayor a 0");
        } else if (repository.findByMatricula(odontologoDtoRequest.getMatricula()).isPresent()) {
            throw new ExceptionDuplicado("Ya existe un odontólogo con esta matrícula");
        } else {
            Odontologo odontologoGuardado = repository.save(mapper.convertValue(odontologoDtoRequest, Odontologo.class));
            return mapper.convertValue(odontologoGuardado, OdontologoDtoResponse.class);
        }

    }

    @Override
    public OdontologoDtoResponse modificarOdontologo(OdontologoDtoRequest odontologoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        Long id = odontologoDtoRequest.getId();

        // todo: Checkear que la matricula sea solo números.
        if (id == null) {
            throw new ExcepcionParametroFaltante("Debes elegir un odontólogo");
        } else if (repository.findById(id).isEmpty()){
            throw new ExcepcionRecursoNoEncontrado("No se encontró al odontólogo con ID: " + id);
        } else if (odontologoDtoRequest.getApellido().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo apellido");
        } else if (odontologoDtoRequest.getNombre().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo nombre");
        } else if (odontologoDtoRequest.getMatricula() == null) {
            throw new ExcepcionParametroFaltante("Debe especificar la matrícula");
        } else if (odontologoDtoRequest.getMatricula() <= 0) {
            throw new ExcepcionParametroInvalido("La matrícula debe ser numérica y mayor a 0");
        } else {
            Odontologo odontologoModificado = repository.save(mapper.convertValue(odontologoDtoRequest, Odontologo.class));
            return mapper.convertValue(odontologoModificado, OdontologoDtoResponse.class);
        }
    }

    @Override
    public OdontologoDtoResponse eliminarOdontologo(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        if (id == null) {
            throw new ExcepcionParametroFaltante("Debes elegir un odontólogo");
        } else if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al odontólogo con el ID: " + id);
        } else {
            Odontologo odontologoEliminado = repository.findById(id).get();
            OdontologoDtoResponse odontologoEliminadoDto = mapper.convertValue(
                    odontologoEliminado, OdontologoDtoResponse.class);
            repository.deleteById(id);
            return odontologoEliminadoDto;
        }
    }

}
