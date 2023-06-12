package ar.com.clinica.service;

import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements IService<Turno> {

    @Autowired
    private ITurnoRepository repository;

    @Autowired
    public TurnoService(ITurnoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Turno> listar() {
        if (repository.findAll().isEmpty()) {
            return null;
        }
        return repository.findAll();
    }

    @Override
    public Turno buscarPorId(Long id) {
        Turno turnoBuscado = null;
        if (repository.findById(id).isPresent()) {
            turnoBuscado = repository.findById(id).get();
        }
        return turnoBuscado;
    }

    @Override
    public Turno insertar(Turno turno) {
        Odontologo odontologo = turno.getOdontologo();
        Paciente paciente = turno.getPaciente();
        if (odontologo == null || paciente == null) { // regla de negocio, debe dar "bad_request"
            return null;
        }
        return repository.save(turno);
    }


    @Override
    public Turno modificar(Turno turnoModificado) {
        Long idBuscado = turnoModificado.getId();
        Turno turno = null;
        if (repository.findById(idBuscado).isPresent()) {
            turno = repository.findById(idBuscado).get();
            turno.setFecha(turnoModificado.getFecha());
            turno.setOdontologo(turnoModificado.getOdontologo());
        }
        return repository.save(turno);
    }

    @Override
    public Turno eliminar(Long id) {
        Turno turno = repository.findById(id).get();
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        return turno;
    }

}
