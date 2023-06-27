package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.request.TurnoDtoRequest;

import ar.com.clinica.dto.response.*;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.ITurnoRepository;
import ar.com.clinica.service.interfaces.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    ITurnoRepository repository;
    @Autowired
    PacienteServiceImpl pacienteService;
    @Autowired
    OdontologoServiceImpl odontologoService;
    @Autowired
    ObjectMapper mapper;


    @Override
    public List<TurnoDtoResponse> listarTurnos() throws ExcepcionNoHayContenido {

        if (repository.findAll().size() == 0) {
            throw new ExcepcionNoHayContenido("No existen turnos registrados");
        } else {
            return repository.findAll()
                    .stream()
                    .map(turno -> mapper.convertValue(turno, TurnoDtoResponse.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public TurnoDtoResponse buscarTurnoPorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al turno con el ID: " + id);
        }
        else {

            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            Turno turnoEncontrado = repository.findById(id).get();

            Long id_odontologo = turnoEncontrado.getOdontologo().getId();
            OdontologoDtoResponse odontologo_dto = odontologoService.buscarOdontologoPorId(id_odontologo);
            turnoEncontrado.setOdontologo(mapper.convertValue(odontologo_dto, Odontologo.class));

            Long id_paciente = turnoEncontrado.getPaciente().getId();
            PacienteDtoResponse paciente_dto = pacienteService.buscarPacientePorId(id_paciente);
            turnoEncontrado.setPaciente(mapper.convertValue(paciente_dto, Paciente.class));

            TurnoDtoResponse turnoDto = mapper.convertValue(turnoEncontrado, TurnoDtoResponse.class);

            return turnoDto;
        }
    }

    @Override
    public TurnoDtoResponse guardarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroInvalido, ExcepcionParametroFaltante {

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Puede devolver ExceptionRecursoNoEncontrado
        OdontologoDtoResponse odontologoDto = odontologoService.buscarOdontologoPorId(turnoDtoRequest.getIdOdontologo());
        Odontologo odontologo = mapper.convertValue(odontologoDto, Odontologo.class);

        // Puede devolver ExceptionRecursoNoEncontrado
        PacienteDtoResponse pacienteDto = pacienteService.buscarPacientePorId(turnoDtoRequest.getIdPaciente());
        Paciente paciente = mapper.convertValue(pacienteDto, Paciente.class);

        if(turnoDtoRequest.getFecha().before(Date.valueOf(LocalDate.now()))) {
            throw new ExcepcionParametroInvalido("Lamentamos no poder viajar al pasado. Por favor, elige otra fecha");
        } else if (turnoDtoRequest.getFecha().before(Date.valueOf(LocalDate.now().plusDays(1)))) {
            throw new ExcepcionParametroInvalido("No podemos reservar un turno para hoy, lo sentimos. Intenta a partir de mañana");
        /*} else if (repository.checkDisponibilidad()) {
            throw new ExceptionDuplicado("El odontólogo elegido ya tiene un turno reservado para esta fecha");*/
        } else {
            Turno nuevoTurno = new Turno();
            nuevoTurno.setOdontologo(odontologo);
            nuevoTurno.setPaciente(paciente);
            nuevoTurno.setFecha(turnoDtoRequest.getFecha());
            return mapper.convertValue(repository.save(nuevoTurno), TurnoDtoResponse.class);
        }
    }

    @Override
    public TurnoDtoResponse modificarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        Long id_Turno = turnoDtoRequest.getId();

        if (repository.findById(id_Turno).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id_Turno);
        }
        else {

            Turno turnoModificado = repository.findById(id_Turno).get();

            OdontologoDtoResponse nuevoOdontologo = odontologoService.buscarOdontologoPorId(turnoDtoRequest.getIdOdontologo());
            turnoModificado.setOdontologo(mapper.convertValue(nuevoOdontologo, Odontologo.class));

            PacienteDtoResponse nuevoPaciente = pacienteService.buscarPacientePorId(turnoDtoRequest.getIdPaciente());
            turnoModificado.setPaciente(mapper.convertValue(nuevoPaciente, Paciente.class));

            turnoModificado.setFecha(turnoDtoRequest.getFecha());

            Turno turnoActualizado = repository.save(turnoModificado);
            return mapper.convertValue(turnoActualizado, TurnoDtoResponse.class);
        }
    }

    @Override
    public TurnoDtoResponse eliminarTurno(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else {
            Turno turnoEliminado = repository.findById(id).get();
            TurnoDtoResponse turnoEliminadoDto = mapper.convertValue(turnoEliminado, TurnoDtoResponse.class);
            repository.deleteById(id);
            return turnoEliminadoDto;
        }
    }

    public List<TurnoDtoResponse> listarTurnosPorPacienteId(Long id) throws ExcepcionNoHayContenido, ExcepcionParametroInvalido {

        if (id == null) {
            throw new ExcepcionParametroInvalido("Debes elegir un paciente");
        } else if (repository.listarPorPacienteId(id) == null) {
            throw new ExcepcionNoHayContenido("No hay turnos agendados");
        } else {
            return repository.listarPorPacienteId(id)
                    .stream()
                    .map(turno -> mapper.convertValue(turno, TurnoDtoResponse.class))
                    .collect(Collectors.toList());
        }
    }

    public List<TurnoDtoResponse> listarTurnosPorOdontologoId(Long id) throws ExcepcionNoHayContenido, ExcepcionParametroInvalido {

        if (id == null) {
            throw new ExcepcionParametroInvalido("Debes elegir un odontologo");
        } else if (repository.listarPorOdontologoId(id) == null) {
            throw new ExcepcionNoHayContenido("No hay turnos agendados");
        } else {
            return repository.listarPorOdontologoId(id)
                    .stream()
                    .map(turno -> mapper.convertValue(turno, TurnoDtoResponse.class))
                    .collect(Collectors.toList());
        }
    }
}
