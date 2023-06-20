package ar.com.clinica.service;

import ar.com.clinica.dto.req.OdontologoDtoReq;
import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.exceptions.*;

import java.util.List;

public interface IOdontologoService {

    public List<OdontologoDtoRes> listarOdontologos() throws ExcepcionNoHayContenido;

    public OdontologoDtoRes buscarOdontologoPorId(Long id) throws ExcepcionRecursoNoEncontrado;

    public OdontologoDtoRes guardarOdontologo(OdontologoDtoReq s) throws ExcepcionParametroFaltante;

    public OdontologoDtoRes modificarOdontologo(OdontologoDtoReq s) throws ExcepcionRecursoNoEncontrado;

    public OdontologoDtoRes eliminarOdontologo(Long id) throws ExcepcionRecursoNoEncontrado;

}