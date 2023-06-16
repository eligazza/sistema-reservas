package ar.com.clinica.service;

import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.dto.res.TurnoDtoRes;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService implements IService<TurnoDtoRes, TurnoDtoRes> {

    @Autowired
    ITurnoRepository repository;
    @Autowired
    ObjectMapper mapper;
    // ObjectMapper mapper = Mapper.getMapper(false, false);


    @Override
    public List<TurnoDtoRes> listar() {

        List<TurnoDtoRes> turnosDto = null;

        List<Turno> turnos = repository.findAll();

        for (Turno turno: turnos) {
            TurnoDtoRes turnoDto = mapper.convertValue(turno, TurnoDtoRes.class);
                        turnoDto.setOdontologo(mapper.convertValue(turno.getOdontologo(), OdontologoDtoRes.class));
                        turnoDto.setPaciente(mapper.convertValue(turno.getPaciente(), PacienteDtoRes.class));

                        turnosDto.add(turnoDto);
        }

        return turnosDto;
    }

    @Override
    public TurnoDtoRes buscarPorId(Long id) {

        TurnoDtoRes turnoRespuesta = null;
        if (repository.findById(id).isPresent()) {
            Turno turnoEntity = repository.findById(id).get();
            turnoRespuesta = mapper.convertValue(turnoEntity, TurnoDtoRes.class);
        }
        return turnoRespuesta;
    }

    @Override
    public TurnoDtoRes insertar(TurnoDtoRes turnoDtoRes) {

        Turno turno = mapper.convertValue(turnoDtoRes, Turno.class);
        Turno turnoGuardado = repository.save(turno);
        return mapper.convertValue(turnoGuardado, TurnoDtoRes.class);

    }

    @Override
    public TurnoDtoRes modificar(TurnoDtoRes turnoDtoRes) {
        Turno turno = mapper.convertValue(turnoDtoRes, Turno.class);
        return mapper.convertValue(repository.save(turno), TurnoDtoRes.class);
    }

    @Override
    public TurnoDtoRes eliminar(Long id) {

        Turno turno = repository.findById(id).get();
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        return mapper.convertValue(turno, TurnoDtoRes.class);
    }

}
