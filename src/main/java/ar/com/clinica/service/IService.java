package ar.com.clinica.service;

import ar.com.clinica.entity.Odontologo;

import java.util.List;

public interface IService<T> {

    public List<T> listar();

    public T buscarPorId(Long id);

    public T insertar(T t);

    public T modificar(T t);

    public T eliminar(Long id);

}
