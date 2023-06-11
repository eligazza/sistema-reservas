package ar.com.clinica.service;

import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.repository.IOdontologoRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OdontologoServiceImp implements IOdontologoService {

    @Autowired
    private IOdontologoRepository repository;

    @Autowired
    public OdontologoServiceImp(IOdontologoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Odontologo> listar() {
        List<Odontologo> lista = repository.findAll();
        if (lista.isEmpty()) {
            lista = null;
        }
        return lista;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return null;
    }

    @Override
    public Odontologo insertar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo eliminar(Long id) {
        return null;
    }


}
