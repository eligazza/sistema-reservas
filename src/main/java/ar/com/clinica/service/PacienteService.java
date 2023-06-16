package ar.com.clinica.service;

import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.dto.req.PacienteDtoReq;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.exceptions.ExcepcionNoHayContenido;
import ar.com.clinica.exceptions.ExcepcionRecursoNoEncontrado;
import ar.com.clinica.exceptions.ExcepcionParametroFaltante;
import ar.com.clinica.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService implements IService<PacienteDtoRes, PacienteDtoReq> {


    @Autowired
    IPacienteRepository repository;
    @Autowired
    ObjectMapper mapper;


    @Override
    public List<PacienteDtoRes> listar() throws ExcepcionNoHayContenido {

        if (repository.findAll().size() == 0) {
            throw new ExcepcionNoHayContenido("No existen pacientes registrados");
        }
        else {
            return repository.findAll()
                    .stream()
                    .map(paciente -> mapper.convertValue(paciente, PacienteDtoRes.class))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public PacienteDtoRes buscarPorId(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        }
        else {
            return mapper.convertValue(repository.findById(id).get(), PacienteDtoRes.class);
        }
    }

    @Override
    public PacienteDtoRes insertar(PacienteDtoReq pacienteDtoReq) throws ExcepcionParametroFaltante {

        if (pacienteDtoReq.getApellido().isEmpty() || pacienteDtoReq.getNombre().isEmpty()) {
            throw new ExcepcionParametroFaltante("Apellido y nombre obligatorios");
        }
        else {
            Paciente pacienteGuardado = repository.save(mapper.convertValue(pacienteDtoReq, Paciente.class));
            return mapper.convertValue(pacienteGuardado, PacienteDtoRes.class);
        }
    }

    @Override
    public PacienteDtoRes modificar(PacienteDtoReq pacienteDtoReq) throws ExcepcionRecursoNoEncontrado {

        Long id = pacienteDtoReq.getId();

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id );
        }
        else {
            Paciente pacienteGuardado = repository.save(mapper.convertValue(pacienteDtoReq, Paciente.class));
            return mapper.convertValue(pacienteGuardado, PacienteDtoRes.class);
        }
    }

    @Override
    public PacienteDtoRes eliminar(Long id) throws ExcepcionRecursoNoEncontrado {

        if (repository.findById(id).isEmpty()) {
            throw new ExcepcionRecursoNoEncontrado("No se encontró al paciente con el ID: " + id);
        }
        else {
            Paciente pacienteEliminado = repository.findById(id).get();
            PacienteDtoRes pacienteEliminadoDto = mapper.convertValue(pacienteEliminado, PacienteDtoRes.class);
            repository.deleteById(id);
            return pacienteEliminadoDto;
        }
    }

}
