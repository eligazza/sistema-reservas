package ar.com.clinica.service;

import ar.com.clinica.entity.Turno;
import ar.com.clinica.repository.ITurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoServiceImp implements ITurnoService {

    @Autowired
    private ITurnoRepository repository;

    @Autowired
    public TurnoServiceImp(ITurnoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Turno> listar() {
        return null;
    }

    @Override
    public Turno buscarPorId(Long id) {
        return null;
    }

    @Override
    public Turno insertar(Turno turno) {
        return null;
    }

    @Override
    public Turno modificar(Turno turno) {
        return null;
    }

    @Override
    public Turno eliminar(Long id) {
        return null;
    }
}