package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.request.TurnoDtoRequest;

import ar.com.clinica.dto.response.*;
import ar.com.clinica.entity.*;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.ITurnoRepository;
import ar.com.clinica.service.interfaces.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TurnoServiceImpl implements ITurnoService {

    private static final Logger LOG = LogManager.getLogger(TurnoServiceImpl.class);

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
    public TurnoDtoResponse buscarTurnoPorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al turno con el ID: " + id);
        } else {

            Turno turnoEncontrado = repository.findById(id).get();

            TurnoDtoResponse response = new TurnoDtoResponse();
            response.setId(turnoEncontrado.getId());
            response.setOdontologo(mapper.convertValue(turnoEncontrado.getOdontologo(), OdontologoDtoResponse.class));
            response.setPaciente(mapper.convertValue(turnoEncontrado.getPaciente(), PacienteDtoResponse.class));
            response.setFecha(turnoEncontrado.getFecha());
            response.setHora(turnoEncontrado.getHora());

            return response;
        }
    }

    @Override
    public TurnoDtoResponse guardarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroInvalido, ExcepcionParametroFaltante, ExcepcionDuplicado {

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        validarInputs(turnoDtoRequest);
        validarFechaYHora(turnoDtoRequest);
        validarDisponibilidad(turnoDtoRequest);

        OdontologoDtoResponse odontologo = odontologoService.buscarOdontologoPorId(turnoDtoRequest.getIdOdontologo());
        PacienteDtoResponse paciente = pacienteService.buscarPacientePorId(turnoDtoRequest.getIdPaciente());
        LocalDate fecha = turnoDtoRequest.getFecha();
        LocalTime hora = turnoDtoRequest.getHora();

        Turno nuevoTurno = new Turno();
        nuevoTurno.setOdontologo(mapper.convertValue(odontologo, Odontologo.class));
        nuevoTurno.setPaciente(mapper.convertValue(paciente, Paciente.class));
        nuevoTurno.setFecha(fecha);
        nuevoTurno.setHora(hora);

        Turno guardado = repository.save(nuevoTurno);
        LOG.info("Turno AGENDADO. " +
                "ID [" + guardado.getId() +
                "Fecha [" + guardado.getFecha() + " " + guardado.getHora() + "]hs " +
                "Paciente [" + guardado.getPaciente() + "] " +
                "Odontólogo [" + guardado.getOdontologo() + "]");
        return mapper.convertValue(guardado, TurnoDtoResponse.class);

    }

    @Override
    public TurnoDtoResponse modificarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido, ExcepcionDuplicado {

        validarInputs(turnoDtoRequest);
        validarFechaYHora(turnoDtoRequest);
        validarDisponibilidad(turnoDtoRequest);

        Long id_Turno = turnoDtoRequest.getId();
        OdontologoDtoResponse odontologo = odontologoService.buscarOdontologoPorId(turnoDtoRequest.getIdOdontologo());
        PacienteDtoResponse paciente = pacienteService.buscarPacientePorId(turnoDtoRequest.getIdPaciente());
        LocalDate fecha = turnoDtoRequest.getFecha();
        LocalTime hora = turnoDtoRequest.getHora();

        if (repository.findById(id_Turno).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id_Turno);
        } else {
            Turno turnoModificado = repository.findById(id_Turno).get();
            turnoModificado.setFecha(fecha);
            turnoModificado.setHora(hora);
            turnoModificado.setOdontologo(mapper.convertValue(odontologo, Odontologo.class));
            turnoModificado.setPaciente(mapper.convertValue(paciente, Paciente.class));

            Turno turnoActualizado = repository.save(turnoModificado);
            LOG.info("Turno ACTUALIZADO. " +
                    "ID [" + turnoActualizado.getId() +
                    "Fecha [" + turnoActualizado.getFecha() + " " + turnoActualizado.getHora() + "]hs " +
                    "Paciente [" + turnoActualizado.getPaciente() + "] " +
                    "Odontólogo [" + turnoActualizado.getOdontologo() + "]");
            return mapper.convertValue(turnoActualizado, TurnoDtoResponse.class);
        }
    }

    @Override
    public TurnoDtoResponse eliminarTurno(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else {
            TurnoDtoResponse turnoEliminado = mapper.convertValue(repository.findById(id).get(), TurnoDtoResponse.class);
            repository.deleteById(id);
            LOG.info("Turno ELIMINADO. " +
                    "ID [" + turnoEliminado.getId() +
                    "Fecha [" + turnoEliminado.getFecha() + " " + turnoEliminado.getHora() + "]hs " +
                    "Paciente [" + turnoEliminado.getPaciente() + "] " +
                    "Odontólogo [" + turnoEliminado.getOdontologo() + "]");
            return turnoEliminado;
        }
    }

    public List<TurnoDtoResponse> listarTurnosPorPacienteId(Long id) throws ExcepcionNoHayContenido, ExcepcionParametroInvalido {

        if (id == null || id.toString().equals("")) {
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

    private void validarInputs(TurnoDtoRequest t) throws ExcepcionParametroFaltante {

        Long paciente = t.getIdPaciente();
        Long odontologo = t.getIdOdontologo();
        LocalDate fecha = t.getFecha();
        LocalTime hora = t.getHora();

        if (paciente == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        } else if (odontologo == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un odontólogo");
        } else if (fecha == null || hora == null) {
            throw new ExcepcionParametroFaltante("Debe elegir fecha y horario");
        }
    }

    private void validarFechaYHora(TurnoDtoRequest t) throws ExcepcionParametroInvalido {

        LocalDate fecha = t.getFecha();
        LocalTime hora = t.getHora();

        if (fecha.isBefore(LocalDate.now())) {
            throw new ExcepcionParametroInvalido("Lamentamos no poder viajar al pasado. Por favor, elige otra fecha");
        } else if (fecha.isBefore(LocalDate.now().plusDays(1))) {
            throw new ExcepcionParametroInvalido("No podemos reservar un turno para hoy, lo sentimos. Intenta a partir de mañana");
        } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            throw new ExcepcionParametroInvalido("No podemos registrar turnos para sábados o domingos");
        } else if (hora.isBefore(LocalTime.of(9, 0)) || hora.isAfter(LocalTime.of(17, 30))) {
            throw new ExcepcionParametroInvalido("Los horarios de atención son de 9 a 18hs"); // los turnos son de media hora
        }
    }

    private void validarDisponibilidad(TurnoDtoRequest t) throws ExcepcionDuplicado {

        Long idOdontologo = t.getIdOdontologo();
        Long idPaciente = t.getIdPaciente();
        LocalDate fecha = t.getFecha();
        LocalTime hora = t.getHora();

        // Obtengo turnos del odontologo
        List<Turno> turnosOdontologo = repository.listarPorOdontologoId(idOdontologo);
        // Filtro los de la fecha buscada
        List<Turno> turnosOdontologoByFecha = new ArrayList<>();
        for (Turno turno : turnosOdontologo) {
            if (turno.getFecha().isEqual(fecha)) {
                turnosOdontologoByFecha.add(turno);
            }
        }
        // Filtro para esa hora en particular
        for (Turno turno : turnosOdontologoByFecha) {
            if (turno.getHora().equals(hora)) {
                throw new ExcepcionDuplicado("El odontólogo elegido ya tiene un turno reservado para esta fecha y hora");
            }
        }

        // Obtengo turnos del paciente
        List<Turno> turnosPaciente = repository.listarPorPacienteId(idPaciente);
        // Filtro los de esa fecha
        List<Turno> turnosPacienteByFecha = new ArrayList<>();
        for (Turno turno : turnosPaciente) {
            if (turno.getFecha().isEqual(fecha)) {
                turnosPacienteByFecha.add(turno);
            }
        }
        // Filtro para esa hora en particular
        for (Turno turno : turnosPacienteByFecha) {
            if (turno.getHora().equals(hora)) {
                throw new ExcepcionDuplicado("El paciente elegido ya tiene un turno reservado para esta fecha y hora");
            }
        }
    }

}

