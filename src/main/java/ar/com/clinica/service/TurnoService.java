package ar.com.clinica.service;

import ar.com.clinica.dto.req.TurnoDtoReq;

import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.dto.res.TurnoDtoRes;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TurnoService implements ITurnoService {

    @Autowired
    ITurnoRepository repository;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    ObjectMapper mapper;


    @Override
    public List<TurnoDtoRes> listarTurnos() throws ExcepcionNoHayContenido {

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
    public TurnoDtoRes buscarTurnoPorId(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al turno con el ID: " + id);
        }
        else {

            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            Turno turnoEncontrado = repository.findById(id).get();

            Long id_odontologo = turnoEncontrado.getOdontologo().getId();
            OdontologoDtoRes odontologo_dto = odontologoService.buscarOdontologoPorId(id_odontologo);
            turnoEncontrado.setOdontologo(mapper.convertValue(odontologo_dto, Odontologo.class));

            Long id_paciente = turnoEncontrado.getPaciente().getId();
            PacienteDtoRes paciente_dto = pacienteService.buscarPacientePorId(id_paciente);
            turnoEncontrado.setPaciente(mapper.convertValue(paciente_dto, Paciente.class));

            TurnoDtoRes turnoDto = mapper.convertValue(turnoEncontrado, TurnoDtoRes.class);

            return turnoDto;
        }
    }

    @Override
    public TurnoDtoRes guardarTurno(TurnoDtoReq turnoDtoReq) throws ExcepcionRecursoNoEncontrado {

        Turno turnoEntity = mapper.convertValue(turnoDtoReq, Turno.class);
        Turno turnoGuardado = repository.save(turnoEntity);

        Long id_odontologo = turnoDtoReq.getOdontologo().getId();
        OdontologoDtoRes odontologoElegido = odontologoService.buscarOdontologoPorId(id_odontologo);
        turnoGuardado.setOdontologo(mapper.convertValue(odontologoElegido, Odontologo.class));

        Long id_paciente = turnoDtoReq.getPaciente().getId();
        PacienteDtoRes pacienteElegido = pacienteService.buscarPacientePorId(id_paciente);
        turnoGuardado.setPaciente(mapper.convertValue(pacienteElegido, Paciente.class));

        return mapper.convertValue(turnoGuardado, TurnoDtoRes.class);
    }


    @Override
    public TurnoDtoRes modificarTurno(TurnoDtoReq turnoDtoReq) throws ExcepcionRecursoNoEncontrado {

        Long id_Turno = turnoDtoReq.getId();
        if (repository.findById(id_Turno).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id_Turno);
        }
        else {
            Turno turnoModificado = repository.save(mapper.convertValue(turnoDtoReq, Turno.class));

            Long id_odontologo = turnoDtoReq.getOdontologo().getId();
            OdontologoDtoRes odontologoNuevo = odontologoService.buscarOdontologoPorId(id_odontologo);
            turnoModificado.setOdontologo(mapper.convertValue(odontologoNuevo, Odontologo.class));

            Long id_paciente = turnoDtoReq.getPaciente().getId();
            PacienteDtoRes pacienteNuevo = pacienteService.buscarPacientePorId(id_paciente);
            turnoModificado.setPaciente(mapper.convertValue(pacienteNuevo, Paciente.class));

            return mapper.convertValue(turnoModificado, TurnoDtoRes.class);
        }
    }

    @Override
    public TurnoDtoRes eliminarTurno(Long id) throws ExcepcionRecursoNoEncontrado {

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
