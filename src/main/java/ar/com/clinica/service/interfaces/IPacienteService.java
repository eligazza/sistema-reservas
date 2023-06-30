package ar.com.clinica.service.interfaces;

import ar.com.clinica.dto.request.PacienteDtoRequest;
import ar.com.clinica.dto.response.PacienteDtoResponse;
import ar.com.clinica.exceptions.*;

import java.util.List;

public interface IPacienteService {

    public List<PacienteDtoResponse> listarPacientes() throws ExcepcionNoHayContenido;

    public PacienteDtoResponse buscarPacientePorId(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido;

    public PacienteDtoResponse guardarPaciente(PacienteDtoRequest s) throws ExcepcionParametroFaltante, ExcepcionDuplicado;

    public PacienteDtoResponse modificarPaciente(PacienteDtoRequest s) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante;

    public PacienteDtoResponse eliminarPaciente(Long id) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido;

}
