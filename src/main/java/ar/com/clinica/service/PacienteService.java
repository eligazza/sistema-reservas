package ar.com.clinica.service;

import ar.com.clinica.entity.Paciente;
import ar.com.clinica.repository.IDomicilioRepository;
import ar.com.clinica.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IService<Paciente> {

    @Autowired
    private IPacienteRepository repository;
    @Autowired
    private IDomicilioRepository domicilioRepository;

    @Autowired
    public PacienteService(IPacienteRepository repository, IDomicilioRepository domicilioRepository) {
        this.repository = repository;
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    public List<Paciente> listar() {
        if (repository.findAll().isEmpty()) {
            return null;
        } return repository.findAll();
    }

    @Override
    public Paciente buscarPorId(Long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    //OK
    @Override
    public Paciente insertar(Paciente parametroPaciente) {
        domicilioRepository.save(parametroPaciente.getDomicilio());
        return repository.save(parametroPaciente);
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
