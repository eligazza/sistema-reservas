package ar.com.clinica.service;

import ar.com.clinica.dto.req.TurnoDtoReq;
import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.res.PacienteDtoRes;

import ar.com.clinica.dto.res.TurnoDtoRes;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.IOdontologoRepository;
import ar.com.clinica.repository.IPacienteRepository;
import ar.com.clinica.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TurnoService implements IService<TurnoDtoRes, TurnoDtoReq> {

    @Autowired
    ITurnoRepository repository;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    ObjectMapper mapper;


    @Override
    public List<TurnoDtoRes> listar() throws ExcepcionNoHayContenido {

        if (repository.findAll().size() == 0) {
            throw new ExcepcionNoHayContenido("No existen turnos registrados");
        } else {
            return repository.findAll()
                    .stream()
                    .map(turno -> mapper.convertValue(turno, TurnoDtoRes.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public TurnoDtoRes buscarPorId(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al turno con el ID: " + id);
        } else {
            return mapper.convertValue(repository.findById(id).get(), TurnoDtoRes.class);
        }
    }

    @Override
    public TurnoDtoRes insertar(TurnoDtoReq turnoDtoReq) throws ExcepcionParametroFaltante {
        Turno turno = repository.save(mapper.convertValue(turnoDtoReq, Turno.class));
        return mapper.convertValue(turno, TurnoDtoRes.class);
    }


    @Override
    public TurnoDtoRes modificar(TurnoDtoReq turnoDtoReq) throws ExcepcionRecursoNoEncontrado {

        Long id = turnoDtoReq.getId();

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al turno con el ID: " + id);
        } else {
            Turno turnoModificado = repository.save(mapper.convertValue(turnoDtoReq, Turno.class));
            return mapper.convertValue(turnoModificado, TurnoDtoRes.class);
        }
    }

    @Override
    public TurnoDtoRes eliminar(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else {
            Turno turnoEliminado = repository.findById(id).get();
            TurnoDtoRes turnoEliminadoDto = mapper.convertValue(turnoEliminado, TurnoDtoRes.class);
            repository.deleteById(id);
            return turnoEliminadoDto;
        }
    }

}
