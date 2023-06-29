package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.request.TurnoDtoRequest;

import ar.com.clinica.dto.response.*;
import ar.com.clinica.entity.*;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.ITurnoRepository;
import ar.com.clinica.service.interfaces.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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
        } else {

            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            Turno turnoEncontrado = repository.findById(id).get();

            Long id_odontologo = turnoEncontrado.getOdontologo().getId();
            OdontologoDtoResponse odontologo_dto = odontologoService.buscarOdontologoPorId(id_odontologo);
            turnoEncontrado.setOdontologo(mapper.convertValue(odontologo_dto, Odontologo.class));

            Long id_paciente = turnoEncontrado.getPaciente().getId();
            PacienteDtoResponse paciente_dto = pacienteService.buscarPacientePorId(id_paciente);
            turnoEncontrado.setPaciente(mapper.convertValue(paciente_dto, Paciente.class));

            return mapper.convertValue(turnoEncontrado, TurnoDtoResponse.class);
        }
    }

    @Override
    public TurnoDtoResponse guardarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroInvalido, ExcepcionParametroFaltante, ExcepcionNoHayContenido, ExcepcionDuplicado {

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        if (turnoDtoRequest.getIdOdontologo() == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        } else if (turnoDtoRequest.getIdPaciente() == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un odontólogo");
        } else if (turnoDtoRequest.getFecha() == null || turnoDtoRequest.getHora() == null) {
            throw new ExcepcionParametroFaltante ("Debe elegir fecha y horario");
        }

        Long idOdontologo = turnoDtoRequest.getIdOdontologo();
        Long idPaciente = turnoDtoRequest.getIdPaciente();
        LocalDate fecha = turnoDtoRequest.getFecha();
        LocalTime hora = turnoDtoRequest.getHora();

        if (fecha.isBefore(LocalDate.now())) {
            throw new ExcepcionParametroInvalido("Lamentamos no poder viajar al pasado. Por favor, elige otra fecha");
        } else if (fecha.isBefore(LocalDate.now().plusDays(1))) {
            throw new ExcepcionParametroInvalido("No podemos reservar un turno para hoy, lo sentimos. Intenta a partir de mañana");
        } else if (listarFechasDeTurnosPorOdontologoId(idOdontologo).contains(fecha)) {
            throw new ExcepcionDuplicado("El odontólogo elegido ya tiene un turno reservado para esta fecha");
        } else {
            Odontologo odontologo = mapper.convertValue(odontologoService.buscarOdontologoPorId(idOdontologo), Odontologo.class);
            Paciente paciente = mapper.convertValue(pacienteService.buscarPacientePorId(idPaciente), Paciente.class);

            Turno nuevoTurno = new Turno();
            nuevoTurno.setOdontologo(odontologo);
            nuevoTurno.setPaciente(paciente);
            nuevoTurno.setFecha(fecha);
            nuevoTurno.setHora(hora);
            return mapper.convertValue(repository.save(nuevoTurno), TurnoDtoResponse.class);
        }

    }

    @Override
    public TurnoDtoResponse modificarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        Long id_Turno = turnoDtoRequest.getId();

        if (turnoDtoRequest.getIdOdontologo() == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        } else if (turnoDtoRequest.getIdPaciente() == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un odontólogo");
        } else if (turnoDtoRequest.getFecha() == null || turnoDtoRequest.getHora() == null) {
            throw new ExcepcionParametroFaltante ("Debe elegir fecha y horario");
        } else if (repository.findById(id_Turno).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id_Turno);
        } else {

            OdontologoDtoResponse nuevoOdontologo = odontologoService.buscarOdontologoPorId(turnoDtoRequest.getIdOdontologo());
            PacienteDtoResponse nuevoPaciente = pacienteService.buscarPacientePorId(turnoDtoRequest.getIdPaciente());

            Turno turnoModificado = repository.findById(id_Turno).get();
            turnoModificado.setOdontologo(mapper.convertValue(nuevoOdontologo, Odontologo.class));
            turnoModificado.setPaciente(mapper.convertValue(nuevoPaciente, Paciente.class));
            turnoModificado.setFecha(turnoDtoRequest.getFecha());
            turnoModificado.setHora(turnoDtoRequest.getHora());

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

    public List<LocalDate> listarFechasDeTurnosPorOdontologoId(Long id) throws ExcepcionNoHayContenido, ExcepcionParametroInvalido {

        if (id == null) {
            throw new ExcepcionParametroInvalido("Debes elegir un odontologo");
        } else if (repository.listarPorOdontologoId(id) == null) {
            throw new ExcepcionNoHayContenido("No hay turnos agendados");
        } else {
            return repository.listarPorOdontologoId(id)
                    .stream()
                    .map(turno -> turno.getFecha())
                    .collect(Collectors.toList());
        }
    }
}
