package ar.com.clinica.service;

import ar.com.clinica.entity.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOdontologoService {

    public List<Odontologo> listar();

    public Odontologo buscarPorId(Long id);

    public Odontologo insertar(Odontologo odontologo);

    public Odontologo modificar(Odontologo odontologo);

    public Odontologo eliminar(Long id);
}
