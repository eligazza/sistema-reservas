package ar.com.clinica.service;

import ar.com.clinica.entity.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IPacienteService {

    public List<Paciente> listar();

    public Paciente buscarPorId(Long id);

    public Paciente insertar(Paciente paciente);

    public Paciente modificar(Paciente paciente);

    public Paciente eliminar(Long id);

}

