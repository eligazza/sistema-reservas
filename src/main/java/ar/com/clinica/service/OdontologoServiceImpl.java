package ar.com.clinica.service;

import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.req.OdontologoDtoReq;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.exceptions.ExcepcionNoHayContenido;
import ar.com.clinica.exceptions.ExcepcionParametroFaltante;
import ar.com.clinica.exceptions.ExcepcionRecursoNoEncontrado;
import ar.com.clinica.repository.IOdontologoRepository;
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
    public List<OdontologoDtoRes> listarOdontologos() throws ExcepcionNoHayContenido {

        if (repository.findAll().size() == 0) {
            throw new ExcepcionNoHayContenido("No existen odontólogos registrados");
        } else {
            return repository.findAll()
                    .stream()
                    .map(odontologo -> mapper.convertValue(odontologo, OdontologoDtoRes.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public OdontologoDtoRes buscarOdontologoPorId(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al odontólogo con el ID: " + id);
        } else {
            return mapper.convertValue(repository.findById(id).get(), OdontologoDtoRes.class);
        }
    }

    @Override
    public OdontologoDtoRes guardarOdontologo(OdontologoDtoReq odontologoDtoReq) throws ExcepcionParametroFaltante {

        if (odontologoDtoReq.getApellido().isEmpty() || odontologoDtoReq.getNombre().isEmpty()) {
            throw new ExcepcionParametroFaltante("Apellido y nombre obligatorios");
        } else {
            Odontologo odontologoGuardado = repository.save(mapper.convertValue(odontologoDtoReq, Odontologo.class));
            return mapper.convertValue(odontologoGuardado, OdontologoDtoRes.class);
        }

    }

    @Override
    public OdontologoDtoRes modificarOdontologo(OdontologoDtoReq odontologoDtoReq) throws ExcepcionRecursoNoEncontrado {

        Long id = odontologoDtoReq.getId();

        if (repository.findById(id).isEmpty()){
            throw new ExcepcionRecursoNoEncontrado("No se encontró al odontólogo con ID: " + id);
        } else {
            Odontologo odontologoModificado = repository.save(mapper.convertValue(odontologoDtoReq, Odontologo.class));
            return mapper.convertValue(odontologoModificado, OdontologoDtoRes.class);
        }
    }

    @Override
    public OdontologoDtoRes eliminarOdontologo(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al odontólogo con el ID: " + id);
        } else {
            Odontologo odontologoEliminado = repository.findById(id).get();
            OdontologoDtoRes odontologoEliminadoDto = mapper.convertValue(
                    odontologoEliminado, OdontologoDtoRes.class);
            repository.deleteById(id);
            return odontologoEliminadoDto;
        }
    }

}