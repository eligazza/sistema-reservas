package ar.com.clinica.service;

import ar.com.clinica.entity.Paciente;
import ar.com.clinica.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IService<Paciente> {

    private IPacienteRepository repository;


    @Autowired
    public PacienteService(IPacienteRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Paciente> listar() {
        return null;
    }

    @Override
    public Paciente listarPorId(int id) {
        return null;
    }

    @Override
    public Paciente insertar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente eliminar(int id) {
        return null;
    }
}
