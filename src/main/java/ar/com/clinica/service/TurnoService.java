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
public class TurnoService implements IService<Turno, TurnoDto> {

    @Autowired
    private ITurnoRepository repository;
    ObjectMapper mapper = Mapper.getMapper(false, false);


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
    public TurnoDto insertar(Turno turno) {

        Odontologo odontologo = turno.getOdontologo();
        Paciente paciente = turno.getPaciente();
        if (odontologo == null || paciente == null) { // regla de negocio, debe dar "bad_request"
            return null;
        }
        return mapper.convertValue(repository.save(turno), TurnoDto.class);
    }


    @Override
    public TurnoDto modificar(Turno turnoModificado) {

        Long idBuscado = turnoModificado.getId();
        Turno turno = null;
        if (repository.findById(idBuscado).isPresent()) {
            turno = repository.findById(idBuscado).get();
            turno.setFecha(turnoModificado.getFecha());
            turno.setOdontologo(turnoModificado.getOdontologo());
        }
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
