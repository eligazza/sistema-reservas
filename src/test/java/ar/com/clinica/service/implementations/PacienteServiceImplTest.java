package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.request.PacienteDtoRequest;
import ar.com.clinica.dto.response.DomicilioDtoResponse;
import ar.com.clinica.dto.response.PacienteDtoResponse;
import ar.com.clinica.entity.Domicilio;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.IPacienteRepository;
import ar.com.clinica.repository.ITurnoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PacienteServiceImplTest {

    @InjectMocks
    PacienteServiceImpl service;

    @Mock
    IPacienteRepository repository;

    @Mock
    ITurnoRepository turnoRepository;

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

    @Test
    void test_guardar_apellido_vacio () {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setDni("12345678");
        pacienteDtoRequest.setNombre("Juan");
        pacienteDtoRequest.setApellido("");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.guardarPaciente(pacienteDtoRequest),
                "Debe completar el campo apellido");
    }

    @Test
    void test_guardar_nombre_vacio () {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setDni("12345678");
        pacienteDtoRequest.setNombre("");
        pacienteDtoRequest.setApellido("Molina");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.guardarPaciente(pacienteDtoRequest),
                "Debe completar el campo nombre");

    }

    @Test
    void test_guardar_dni_vacio () {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setDni("");
        pacienteDtoRequest.setNombre("Juan");
        pacienteDtoRequest.setApellido("Molina");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.guardarPaciente(pacienteDtoRequest),
                "Debe completar el campo DNI");

    }

    @Test
    void test_guardar_dni_letras () {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setDni("1234hola");
        pacienteDtoRequest.setNombre("Juan");
        pacienteDtoRequest.setApellido("Molina");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroInvalido.class,
                () -> service.guardarPaciente(pacienteDtoRequest),
                "Ingrese un DNI válido de 8 números");

    }

    @Test
    void test_guardar_dni_largo () {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setDni("12345677899");
        pacienteDtoRequest.setNombre("Juan");
        pacienteDtoRequest.setApellido("Molina");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroInvalido.class,
                () -> service.guardarPaciente(pacienteDtoRequest),
                "Ingrese un DNI válido de 8 número");

    }

    @Test
    void test_guardar_paciente_existente () {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setDni("12345678");
        pacienteDtoRequest.setNombre("Juan");
        pacienteDtoRequest.setApellido("Molina");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());

        when(repository.findByDni(anyString())).thenReturn(Optional.of(new Paciente()));

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionDuplicado.class,
                () -> service.guardarPaciente(pacienteDtoRequest),
                "Ya se encuentra registrado un paciente con este DNI");

    }

    @Test
    void test_guardar_paciente_ok() throws ExcepcionDuplicado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {
        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setDni("12345678");
        pacienteDtoRequest.setNombre("Juan");
        pacienteDtoRequest.setApellido("Lopez");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());
        Paciente paciente = new Paciente(1L, "12345678", "Juan", "Lopez", LocalDate.now(), new Domicilio());
        when(repository.save(any())).thenReturn(paciente);

        //Act
        PacienteDtoResponse respuesta = service.guardarPaciente(pacienteDtoRequest);

        //Assert
        Assertions.assertEquals("12345678", respuesta.getDni());
    }

    @Test
    void test_modificar_id_null() {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setId(null);
        pacienteDtoRequest.setDni("12345671");
        pacienteDtoRequest.setNombre("Vigo");
        pacienteDtoRequest.setApellido("Lopez");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.modificarPaciente(pacienteDtoRequest),
                "Debe elegir un paciente");

    }

    @Test
    void test_modificar_id_notfound() {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setId(1L);
        pacienteDtoRequest.setDni("12345671");
        pacienteDtoRequest.setNombre("Vigo");
        pacienteDtoRequest.setApellido("Lopez");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionRecursoNoEncontrado.class,
                () -> service.modificarPaciente(pacienteDtoRequest),
                "No se encontró al paciente con el ID: ");

    }

    @Test
    void test_modificar_paciente_ok() throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        //Arrange
        PacienteDtoRequest pacienteDtoRequest = new PacienteDtoRequest();
        pacienteDtoRequest.setId(1L);
        pacienteDtoRequest.setDni("12345671");
        pacienteDtoRequest.setNombre("Vigo");
        pacienteDtoRequest.setApellido("Lopez");
        pacienteDtoRequest.setDomicilio(new DomicilioDtoResponse());

        Paciente paciente = new Paciente(1L, "12345671", "Vigo", "Lopez", LocalDate.now(), new Domicilio());
        when(repository.findById(anyLong())).thenReturn(Optional.of(paciente));
        when(repository.save(any())).thenReturn(paciente);

        //Act
        PacienteDtoResponse respuesta = service.modificarPaciente(pacienteDtoRequest);

        //Assert
        Assertions.assertEquals("Vigo", respuesta.getNombre());
    }

    @Test
    void test_eliminar_id_null() {

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.eliminarPaciente(null),
                "Debe elegir un paciente");
    }

    @Test
    void test_eliminar_id_notfound() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionRecursoNoEncontrado.class,
                () -> service.eliminarPaciente(1L),
                "No se encontró al paciente con el ID: 1");
    }

    @Test
    void test_eliminar_ocupado() {

        //Arrange
        Paciente paciente = new Paciente();
        when(repository.findById(anyLong())).thenReturn(Optional.of(paciente));
        List<Turno> lista = new ArrayList<>();
        lista.add(new Turno());
        when(turnoRepository.listarPorPacienteId(anyLong())).thenReturn(lista);

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroInvalido.class,
                () -> service.eliminarPaciente(1L),
                "No puede eliminar este paciente porque posee turnos agendados");
    }

    @Test
    void test_eliminar_paciente_ok() throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        //Arrange
        Paciente paciente = new Paciente(1L, "12345671", "Claudio", "Cardinal", LocalDate.now(), new Domicilio());
        when(repository.findById(anyLong())).thenReturn(Optional.of(paciente));
        when(turnoRepository.listarPorPacienteId(anyLong())).thenReturn(Collections.emptyList());

        //Act
        PacienteDtoResponse respuesta = service.eliminarPaciente(1L);

        //Assert
        Assertions.assertEquals("Claudio", respuesta.getNombre());
    }
}