package ar.com.clinica.service;

import ar.com.clinica.entity.Paciente;
import ar.com.clinica.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImp implements IPacienteService {

    @Autowired
    private IPacienteRepository repository;

    @Autowired
    public PacienteServiceImp(IPacienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> lista = repository.findAll();
        if (lista.isEmpty()) {
            lista = null;
        }
        return lista;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Paciente paciente = null;
        if (repository.findById(id).isPresent()) {
            paciente = repository.findById(id).get();
        }
        return paciente;
    }

    @Override
    public Paciente insertar(Paciente paciente) {
        return paciente;
    }

    // TODO SEGUIR POR ACA


    @Override
    public Paciente modificar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente eliminar(Long id) {
        return null;
    }
}
