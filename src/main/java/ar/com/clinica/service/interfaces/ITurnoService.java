package ar.com.clinica.service.interfaces;

import ar.com.clinica.dto.request.TurnoDtoRequest;
import ar.com.clinica.dto.response.TurnoDtoResponse;
import ar.com.clinica.exceptions.*;

import java.util.List;

public interface ITurnoService {

    public List<TurnoDtoResponse> listarTurnos()throws ExcepcionNoHayContenido;

    public TurnoDtoResponse buscarTurnoPorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante;

    public TurnoDtoResponse guardarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroInvalido, ExcepcionParametroFaltante, ExcepcionDuplicado;

    public TurnoDtoResponse modificarTurno(TurnoDtoRequest turnoDtoRequest) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante;

    public TurnoDtoResponse eliminarTurno(Long id) throws ExcepcionRecursoNoEncontrado;

}
