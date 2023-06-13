package ar.com.clinica.service;

import ar.com.clinica.entity.Odontologo;

import java.util.List;

public interface IService<T, E> {

    public List<E> listar();

    public E buscarPorId(Long id);

    public E insertar(T t);

    public E modificar(T t);

    public E eliminar(Long id);

}
