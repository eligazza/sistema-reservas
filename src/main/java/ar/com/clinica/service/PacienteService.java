package ar.com.clinica.service;

import ar.com.clinica.dto.PacienteDto;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService implements IService<PacienteDto> {


    @Autowired
    private IPacienteRepository repository;
    ObjectMapper mapper = Mapper.getMapper(false, false);


    @Override
    public List<PacienteDto> listar() {

        List<Paciente> listaEntidades = repository.findAll();
        if (listaEntidades.isEmpty()){
            return null;
        } return listaEntidades
                .stream()
                .map(paciente -> mapper.convertValue(paciente, PacienteDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDto buscarPorId(Long id) {

        Paciente pacienteBuscado = null;
        if (repository.findById(id).isPresent()) {
            pacienteBuscado = repository.findById(id).get();
        }
        return mapper.convertValue(pacienteBuscado, PacienteDto.class);
    }

    @Override
    public PacienteDto insertar(PacienteDto pacienteDto) {
        Paciente pacienteEntity = mapper.convertValue(pacienteDto, Paciente.class);
        return mapper.convertValue(repository.save(pacienteEntity), PacienteDto.class);

    }

    @Override
    public PacienteDto modificar(PacienteDto pacienteDto) {

        Paciente paciente = mapper.convertValue(pacienteDto, Paciente.class);
        return mapper.convertValue(repository.save(paciente), PacienteDto.class);

    }

    @Override
    public PacienteDto eliminar(Long id) {

        Paciente paciente = null;
        if (repository.findById(id).isPresent()) {
            paciente = repository.findById(id).get();
            repository.deleteById(id);
        }
        return mapper.convertValue(paciente, PacienteDto.class);
    }

}
