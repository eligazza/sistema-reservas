package ar.com.clinica.service;

import ar.com.clinica.entity.Paciente;
import ar.com.clinica.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IService<Paciente> {

    @Autowired
    private IPacienteRepository repository;

    public PacienteService(IPacienteRepository repository) {
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
    public Paciente insertar(Paciente p) {
        Paciente pGuardado = null;
        if (p.getApellido().isEmpty() || p.getNombre().isEmpty()) {
            pGuardado = repository.save(p);
        }
        return pGuardado;
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
