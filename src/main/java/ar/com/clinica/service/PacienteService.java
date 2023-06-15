package ar.com.clinica.service;

import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.dto.req.PacienteDtoReq;
import ar.com.clinica.entity.Paciente;
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

    //ObjectMapper mapper = Mapper.getMapper(false, false);


    @Override
    public List<PacienteDtoRes> listar() {

        List<Paciente> listaEntidades = repository.findAll();
        List<PacienteDtoRes> pacientesDtos = null;

        if (!listaEntidades.isEmpty()) {
            pacientesDtos = listaEntidades
                    .stream()
                    .map(paciente -> mapper.convertValue(paciente, PacienteDtoRes.class))
                    .collect(Collectors.toList());
        }
        return pacientesDtos;
    }

    @Override
    public PacienteDtoRes buscarPorId(Long id) {

        Paciente pacienteBuscado = null;

        if (repository.findById(id).isPresent()) {
            pacienteBuscado = repository.findById(id).get();
        }
        return mapper.convertValue(pacienteBuscado, PacienteDtoRes.class);
    }

    @Override
    public PacienteDtoRes insertar(PacienteDtoReq pacienteDtoReq) {

        Paciente paciente = mapper.convertValue(pacienteDtoReq, Paciente.class);
        Paciente pacienteGuardado = repository.save(paciente);
        return mapper.convertValue(pacienteGuardado, PacienteDtoRes.class);

    }

    @Override
    public PacienteDtoRes modificar(PacienteDtoReq pacienteDtoReq) {

        Paciente paciente = mapper.convertValue(pacienteDtoReq, Paciente.class);
        return mapper.convertValue(repository.save(paciente), PacienteDtoRes.class);

    }

    @Override
    public PacienteDtoRes eliminar(Long id) {

        Paciente paciente = null;
        if (repository.findById(id).isPresent()) {
            paciente = repository.findById(id).get();
            repository.deleteById(id);
        }
        return mapper.convertValue(paciente, PacienteDtoRes.class);
    }

}
