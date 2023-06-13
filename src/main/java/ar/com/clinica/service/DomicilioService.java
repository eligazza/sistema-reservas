package ar.com.clinica.service;

import ar.com.clinica.entity.Domicilio;
import ar.com.clinica.repository.IDomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService implements IService<Domicilio> {


    private IDomicilioRepository repository;

    @Autowired
    public DomicilioService(IDomicilioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Domicilio> listar() {
        if (repository.findAll().isEmpty()) {
            return null;
        }
        return repository.findAll();
    }

    @Override
    public Domicilio buscarPorId(Long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public Domicilio insertar(Domicilio domicilio) {
        return repository.save(domicilio);
    }

    @Override
    public Domicilio modificar(Domicilio domicilioNuevo) {
        Long idBuscado = domicilioNuevo.getId();
        Domicilio domicilio = null;
        if (repository.findById(idBuscado).isPresent()) {
            domicilio = repository.findById(idBuscado).get();
            domicilio.setCalle(domicilioNuevo.getCalle());
            domicilio.setLocalidad(domicilioNuevo.getLocalidad());
            domicilio.setNumero(domicilioNuevo.getNumero());
        }
        return repository.save(domicilio);
    }

    @Override
    public Domicilio eliminar(Long id) {
        Domicilio domicilioEliminado = null;
        if (repository.findById(id).isPresent()) {
            domicilioEliminado = repository.findById(id).get();
            repository.deleteById(id);
        }
        return domicilioEliminado;
    }
}
