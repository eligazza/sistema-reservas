package ar.com.clinica.repository;

import java.util.List;

public interface IRepository<T,S> {

    public List<T> listar();

    public T listarPorId(int id);

    public T insertar(S s);

    public T modificar(S s);

    public T eliminar(int id);

}
