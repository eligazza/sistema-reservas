package ar.com.clinica.service;

import java.util.List;

public interface IService<T> {

    public List<T> listar();

    public T listarPorId(int id);

    public T insertar(T t);

    public T modificar(T t);

    public T eliminar(int id);
}
