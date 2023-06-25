package ar.com.clinica.service.interfaces;

import ar.com.clinica.dto.request.OdontologoDtoRequest;
import ar.com.clinica.dto.response.OdontologoDtoResponse;
import ar.com.clinica.exceptions.*;

import java.util.List;

public interface IOdontologoService {

    public List<OdontologoDtoResponse> listarOdontologos() throws ExcepcionNoHayContenido;

    public OdontologoDtoResponse buscarOdontologoPorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante;

    public OdontologoDtoResponse guardarOdontologo(OdontologoDtoRequest s) throws ExcepcionParametroFaltante, ExcepcionParametroInvalido;

    public OdontologoDtoResponse modificarOdontologo(OdontologoDtoRequest s) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido;

    public OdontologoDtoResponse eliminarOdontologo(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante;

}