package ar.com.clinica.service;

import ar.com.clinica.entity.Turno;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ITurnoService {

    public List<Turno> listar();

    public Turno buscarPorId(Long id);

    public Turno insertar(Turno turno);

    public Turno modificar(Turno turno);

    public Turno eliminar(Long id);

}
