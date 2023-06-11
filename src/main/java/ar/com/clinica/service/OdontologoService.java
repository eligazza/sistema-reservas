package ar.com.clinica.service;

import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.repository.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OdontologoService implements IService<Odontologo> {

    @Autowired
    private IOdontologoRepository repository;

    @Autowired
    public OdontologoService(IOdontologoRepository repository) {
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
