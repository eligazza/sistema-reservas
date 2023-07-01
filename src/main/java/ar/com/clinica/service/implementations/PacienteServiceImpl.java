package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.response.PacienteDtoResponse;
import ar.com.clinica.dto.request.PacienteDtoRequest;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.IPacienteRepository;
import ar.com.clinica.repository.ITurnoRepository;
import ar.com.clinica.service.interfaces.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    IPacienteRepository repository;
    @Autowired
    ITurnoRepository turnoRepository;
    @Autowired
    ObjectMapper mapper;


    @Override
    public List<PacienteDtoResponse> listarPacientes() throws ExcepcionNoHayContenido {

        if (repository.findAll().size() == 0) {
            throw new ExcepcionNoHayContenido("No existen pacientes registrados");
        } else {
            return repository.findAll()
                    .stream()
                    .map(paciente -> mapper.convertValue(paciente, PacienteDtoResponse.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public PacienteDtoResponse buscarPacientePorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        if (id == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        } else if (id < 1) {
            throw new ExcepcionParametroInvalido("El ID del paciente debe ser mayor a 1");
        } else if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else {
            return mapper.convertValue(repository.findById(id).get(), PacienteDtoResponse.class);
        }
    }

    @Override
    public PacienteDtoResponse guardarPaciente(PacienteDtoRequest pacienteDtoRequest) throws ExcepcionParametroFaltante, ExcepcionDuplicado, ExcepcionParametroInvalido {

        LocalDate hoy = LocalDate.now();

        if (pacienteDtoRequest.getApellido().isBlank() || pacienteDtoRequest.getApellido().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo apellido");
        } else if (pacienteDtoRequest.getNombre().isBlank() || pacienteDtoRequest.getNombre().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo nombre");
        } else if (pacienteDtoRequest.getDni() == null) {
            throw new ExcepcionParametroFaltante("Debe completar el campo DNI");
        } else if (pacienteDtoRequest.getDni().matches("[0-9]+")) {
            throw new ExcepcionParametroInvalido("Ingrese un DNI válido de 8 números");
        } else if (pacienteDtoRequest.getDni().length() < 8) {
            throw new ExcepcionParametroInvalido("Ingrese un DNI válido de 8 número");
        } else if (repository.findByDni(pacienteDtoRequest.getDni()).isPresent()) {
            throw new ExcepcionDuplicado("Ya se encuentra registrado un paciente con este DNI");
        } else {
            Paciente paciente = mapper.convertValue(pacienteDtoRequest, Paciente.class);
            paciente.setFechaDeAlta(hoy);
            Paciente pacienteGuardado = repository.save(paciente);
            return mapper.convertValue(pacienteGuardado, PacienteDtoResponse.class);
        }
    }

    @Override
    public PacienteDtoResponse modificarPaciente(PacienteDtoRequest pacienteDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        Long id = pacienteDtoRequest.getId();
        if (id == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        } else if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else if (pacienteDtoRequest.getApellido().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo apellido");
        } else if (pacienteDtoRequest.getNombre().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo nombre");
        } else if (pacienteDtoRequest.getDni() == null) {
            throw new ExcepcionParametroFaltante("Debe completar el campo DNI");
        } else if (pacienteDtoRequest.getDni().matches("[0-9]+")) {
            throw new ExcepcionParametroInvalido("Ingrese un DNI válido de 8 números");
        } else if (pacienteDtoRequest.getDni().length() < 8) {
            throw new ExcepcionParametroInvalido("Ingrese un DNI válido de 8 número");
        } else {
            Paciente pacienteModificado = repository.save(mapper.convertValue(pacienteDtoRequest, Paciente.class));
            return mapper.convertValue(pacienteModificado, PacienteDtoResponse.class);
        }
    }

    @Override
    public PacienteDtoResponse eliminarPaciente(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        if (id == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        } else if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else if (!turnoRepository.listarPorPacienteId(id).isEmpty()) {
            throw new ExcepcionParametroInvalido("No puede eliminar este paciente porque posee turnos agendados");
        } else {
            Paciente pacienteEliminado = repository.findById(id).get();
            PacienteDtoResponse pacienteEliminadoDto = mapper.convertValue(pacienteEliminado, PacienteDtoResponse.class);
            repository.deleteById(id);
            return pacienteEliminadoDto;
        }
    }

}
