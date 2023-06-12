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

    //OK
    @Override
    public List<Paciente> listar() {
        if (repository.findAll().isEmpty()) {
            return null;
        } return repository.findAll();
    }

    //OK
    @Override
    public Paciente buscarPorId(Long id) {
        Paciente pacienteBuscado = null;
        if (repository.findById(id).isPresent()) {
            pacienteBuscado = repository.findById(id).get();
        }
        return pacienteBuscado;
    }

    //OK
    @Override
    public Paciente insertar(Paciente pacienteNuevo) {
        domicilioRepository.save(pacienteNuevo.getDomicilio());
        return repository.save(pacienteNuevo);
    }

    //TODO NO MODIFICA EL DOMICILIO
    @Override
    public Paciente modificar(Paciente pacienteModificado) {
        Long idBuscado = pacienteModificado.getId();
        Paciente paciente = null;
        if (repository.findById(idBuscado).isPresent()) {
            paciente = repository.findById(idBuscado).get();
            paciente.setDni(pacienteModificado.getDni());
            paciente.setApellido(pacienteModificado.getApellido());
            paciente.setNombre(pacienteModificado.getNombre());
            paciente.setFechaDeAlta(pacienteModificado.getFechaDeAlta());
            paciente.setDomicilio(pacienteModificado.getDomicilio());
        }
        domicilioRepository.save(paciente.getDomicilio());
        return repository.save(paciente);
    }

    //OK
    @Override
    public Paciente eliminar(Long id) {
        Paciente paciente = repository.findById(id).get();
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        return paciente;
    }

}
