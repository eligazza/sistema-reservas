package ar.com.clinica.service;

import ar.com.clinica.dto.TurnoDto;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService implements IService<TurnoDto, TurnoDto> {

    @Autowired
    ITurnoRepository repository;
    @Autowired
    ObjectMapper mapper;
    // ObjectMapper mapper = Mapper.getMapper(false, false);


    @Override
    public List<TurnoDto> listar() {

        List<Turno> listaEntidades = repository.findAll();

        return listaEntidades
                .stream()
                .map(paciente -> mapper.convertValue(paciente, TurnoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TurnoDto buscarPorId(Long id) {

        Turno turnoBuscado = null;
        if (repository.findById(id).isPresent()) {
            turnoBuscado = repository.findById(id).get();
        }
        return mapper.convertValue(turnoBuscado, TurnoDto.class);
    }

    @Override
    public TurnoDto insertar(TurnoDto turnoDto) {

        Turno turno = mapper.convertValue(turnoDto, Turno.class);
        if (turno.getPaciente()==null || turno.getOdontologo()==null) {
            return null;
        }
        return mapper.convertValue(repository.save(turno), TurnoDto.class);
    }


    @Override
    public TurnoDto modificar(TurnoDto turnoDto) {
        Turno turno = mapper.convertValue(turnoDto, Turno.class);
        return mapper.convertValue(repository.save(turno), TurnoDto.class);
    }

    @Override
    public TurnoDto eliminar(Long id) {

        Turno turno = repository.findById(id).get();
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        return mapper.convertValue(turno, TurnoDto.class);
    }

}
