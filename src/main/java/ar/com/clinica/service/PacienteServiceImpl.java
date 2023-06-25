package ar.com.clinica.service;

import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.dto.req.PacienteDtoReq;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.IPacienteRepository;
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
    ObjectMapper mapper;


    @Override
    public List<PacienteDtoRes> listarPacientes() throws ExcepcionNoHayContenido {

        if (repository.findAll().size() == 0) {
            throw new ExcepcionNoHayContenido("No existen pacientes registrados");
        } else {
            return repository.findAll()
                    .stream()
                    .map(paciente -> mapper.convertValue(paciente, PacienteDtoRes.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public PacienteDtoRes buscarPacientePorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        if (id == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        }
        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else {
            return mapper.convertValue(repository.findById(id).get(), PacienteDtoRes.class);
        }
    }

    @Override
    public PacienteDtoRes guardarPaciente(PacienteDtoReq pacienteDtoReq) throws ExcepcionParametroFaltante {

        Date hoy = Date.valueOf(LocalDate.now());

        if (pacienteDtoReq.getApellido().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo apellido");
        } else if (pacienteDtoReq.getNombre().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo nombre");
        } else if (pacienteDtoReq.getDni() == null) {
            throw new ExcepcionParametroFaltante("Debe completar el campo DNI");
        } else {
            Paciente paciente = mapper.convertValue(pacienteDtoReq, Paciente.class);
            paciente.setFechaDeAlta(hoy);
            Paciente pacienteGuardado = repository.save(paciente);
            return mapper.convertValue(pacienteGuardado, PacienteDtoRes.class);
        }
    }

    @Override
    public PacienteDtoRes modificarPaciente(PacienteDtoReq pacienteDtoReq) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        Long id = pacienteDtoReq.getId();
        if (id == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        } else if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else if (pacienteDtoReq.getApellido().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo apellido");
        } else if (pacienteDtoReq.getNombre().isEmpty()) {
            throw new ExcepcionParametroFaltante("Debe completar el campo nombre");
        } else if (pacienteDtoReq.getDni() == null) {
            throw new ExcepcionParametroFaltante("Debe completar el campo DNI");
        } else {
            Paciente pacienteModificado = repository.save(mapper.convertValue(pacienteDtoReq, Paciente.class));
            return mapper.convertValue(pacienteModificado, PacienteDtoRes.class);
        }
    }

    @Override
    public PacienteDtoRes eliminarPaciente(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        if (id == null) {
            throw new ExcepcionParametroFaltante("Debe elegir un paciente");
        } else if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        } else {
            Paciente pacienteEliminado = repository.findById(id).get();
            PacienteDtoRes pacienteEliminadoDto = mapper.convertValue(pacienteEliminado, PacienteDtoRes.class);
            repository.deleteById(id);
            return pacienteEliminadoDto;
        }
    }

}
