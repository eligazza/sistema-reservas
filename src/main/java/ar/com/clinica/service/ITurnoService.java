package ar.com.clinica.service;

import ar.com.clinica.dto.req.TurnoDtoReq;
import ar.com.clinica.dto.res.TurnoDtoRes;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.exceptions.ExcepcionNoHayContenido;
import ar.com.clinica.exceptions.ExcepcionRecursoNoEncontrado;

import java.util.List;

public interface ITurnoService {

    public List<TurnoDtoRes> listarTurnos()throws ExcepcionNoHayContenido;

    public TurnoDtoRes buscarTurnoPorId(Long id) throws ExcepcionRecursoNoEncontrado;

    public TurnoDtoRes guardarTurno(TurnoDtoReq turnoDtoReq) throws ExcepcionRecursoNoEncontrado;

    public TurnoDtoRes modificarTurno(Turno turno);

    public TurnoDtoRes eliminarTurno(Long id) throws ExcepcionRecursoNoEncontrado;

}
