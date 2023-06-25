package ar.com.clinica.service.interfaces;

import ar.com.clinica.dto.request.TurnoDtoRequest;
import ar.com.clinica.dto.response.TurnoDtoResponse;
import ar.com.clinica.exceptions.ExcepcionNoHayContenido;
import ar.com.clinica.exceptions.ExcepcionParametroFaltante;
import ar.com.clinica.exceptions.ExcepcionParametroInvalido;
import ar.com.clinica.exceptions.ExcepcionRecursoNoEncontrado;

import java.util.List;

public interface ITurnoService {

    public List<TurnoDtoResponse> listarTurnos()throws ExcepcionNoHayContenido;

    public TurnoDtoResponse buscarTurnoPorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante;

    public TurnoDtoResponse guardarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroInvalido, ExcepcionParametroFaltante;

    public TurnoDtoResponse modificarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante;

    public TurnoDtoResponse eliminarTurno(Long id) throws ExcepcionRecursoNoEncontrado;

}
