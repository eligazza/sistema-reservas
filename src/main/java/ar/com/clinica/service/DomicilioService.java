package ar.com.clinica.service;

import ar.com.clinica.entity.Domicilio;
import ar.com.clinica.repository.IDomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DomicilioService implements IService<Domicilio> {

    @Autowired
    private IDomicilioRepository repository;

    @Override
    public List<Domicilio> listar() {
        return null;
    }

    @Override
    public Domicilio buscarPorId(Long id) {
        return null;
    }

    @Override
    public Domicilio insertar(Domicilio domicilio) {
        return repository.save(domicilio);
    }

    @Override
    public Domicilio modificar(Domicilio domicilio) {
        return null;
    }

    @Override
    public Domicilio eliminar(Long id) {
        return null;
    }
}
