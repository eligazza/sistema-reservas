package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.response.PacienteDtoResponse;
import ar.com.clinica.entity.Domicilio;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.exceptions.ExcepcionNoHayContenido;
import ar.com.clinica.exceptions.ExcepcionParametroFaltante;
import ar.com.clinica.exceptions.ExcepcionParametroInvalido;
import ar.com.clinica.exceptions.ExcepcionRecursoNoEncontrado;
import ar.com.clinica.repository.IPacienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class PacienteServiceImplTest {

    @InjectMocks
    PacienteServiceImpl service;

    @Mock
    IPacienteRepository repository;


    @Test
    public void test_listar_vacio() {
        //Arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionNoHayContenido.class,
                () -> service.listarPacientes(),
                "No existen pacientes registrados");
    }

    @Test
    public void test_listar_pacientes_ok() throws ExcepcionNoHayContenido {
        //Arrange
        List<Paciente> lista = new ArrayList<>();
        lista.add(new Paciente());
        when(repository.findAll()).thenReturn(lista);
        //Act
        List<PacienteDtoResponse> resultado = service.listarPacientes();
        //Assert
        Assertions.assertEquals(1, resultado.size());
    }

    @Test
    public void test_buscar_id_null() {
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.buscarPacientePorId(null),
                "Debe elegir un paciente");
    }

    @Test
    public void test_buscar_id_negativo() {
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroInvalido.class,
                () -> service.buscarPacientePorId(0L),
                "El ID del paciente debe ser mayor a 1");
    }

    @Test
    public void test_buscar_id_not_found() {
        //Arrange
        when(repository.findById(3L)).thenReturn(Optional.empty());
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionRecursoNoEncontrado.class,
                () -> service.buscarPacientePorId(3L),
                "No se encontró al paciente con el ID: 3");
    }

    @Test
    public void test_buscar_paciente_ok() throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {
        //Arrange
        Paciente paciente = new Paciente(1L, "12345678", "Juan", "Lopez", LocalDate.now(), new Domicilio());
        when(repository.findById(1L)).thenReturn(Optional.of(paciente));
        //Act
        PacienteDtoResponse respuesta = service.buscarPacientePorId(1L);
        //Assert
        assertEquals("Lopez", respuesta.getApellido());
    }

    // TODO

    @Test
    void guardarPaciente() {
    }

    @Test
    void modificarPaciente() {
    }

    @Test
    void eliminarPaciente() {
    }
}