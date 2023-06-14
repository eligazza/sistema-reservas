package ar.com.clinica.service;

import ar.com.clinica.dto.PacienteDto;
import ar.com.clinica.dto.PacienteDtoReq;
import ar.com.clinica.entity.Odontologo;

import java.util.List;

public interface IService<T, S> {

    public List<T> listar();

    public T buscarPorId(Long id);

    public T insertar(S s);

    public T modificar(S s);

    public T eliminar(Long id);

}
