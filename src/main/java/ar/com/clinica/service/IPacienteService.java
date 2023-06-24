package ar.com.clinica.service;

import ar.com.clinica.dto.req.PacienteDtoReq;
import ar.com.clinica.dto.res.PacienteDtoRes;
import ar.com.clinica.exceptions.*;

import java.util.List;

public interface IPacienteService {

    public List<PacienteDtoRes> listarPacientes() throws ExcepcionNoHayContenido;

    public PacienteDtoRes buscarPacientePorId(Long id) throws ExcepcionRecursoNoEncontrado;

    public PacienteDtoRes guardarPaciente(PacienteDtoReq s) throws ExcepcionParametroFaltante;

    public PacienteDtoRes modificarPaciente(PacienteDtoReq s) throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante;

    public PacienteDtoRes eliminarPaciente(Long id) throws ExcepcionRecursoNoEncontrado;

}
