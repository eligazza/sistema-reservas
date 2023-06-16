package ar.com.clinica.service;

import ar.com.clinica.exceptions.ExcepcionNoHayContenido;
import ar.com.clinica.exceptions.ExcepcionRecursoNoEncontrado;
import ar.com.clinica.exceptions.ExcepcionParametroFaltante;

import java.util.List;

public interface IService<T, S> {

    public List<T> listar() throws ExcepcionNoHayContenido;

    public T buscarPorId(Long id) throws ExcepcionRecursoNoEncontrado;

    public T insertar(S s) throws ExcepcionParametroFaltante;

    public T modificar(S s) throws ExcepcionRecursoNoEncontrado;

    public T eliminar(Long id) throws ExcepcionRecursoNoEncontrado;

}
