package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.request.OdontologoDtoRequest;
import ar.com.clinica.dto.response.OdontologoDtoResponse;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.IOdontologoRepository;
import ar.com.clinica.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OdontologoServiceImplTest {

    @InjectMocks
    OdontologoServiceImpl service;

    @Mock
    IOdontologoRepository repository;
    @Mock
    ITurnoRepository turnoRepository;


    @Test
    public void test_lista_vacia() throws ExcepcionNoHayContenido {

        //Arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());

        //Act&Assert
        Assertions.assertThrows(ExcepcionNoHayContenido.class, () -> service.listarOdontologos(), "No existen odontólogos registrados");

    }

    @Test
    void test_lista_ok() throws ExcepcionNoHayContenido {

        //Arrange
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos.add(new Odontologo(1L, "Juan", "Perez", 1234));
        odontologos.add(new Odontologo(2L, "Pedro", "Gonzalez", 5678));
        when(repository.findAll()).thenReturn(odontologos);

        //Act
        List<OdontologoDtoResponse> odontologosDto = service.listarOdontologos();

        //Assert
        assertEquals(odontologosDto.size(), 2);

    }

    @Test
    public void test_buscar_id_null() throws ExcepcionParametroFaltante {
        // Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.buscarOdontologoPorId(null),
                "Debes elegir un odontólogo");
    }

    @Test
    public void test_buscar_not_found() throws ExcepcionRecursoNoEncontrado {

        //Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        //Act & Assert
        Assertions.assertThrows(
                ExcepcionRecursoNoEncontrado.class,
                () -> service.buscarOdontologoPorId(1L),
                "No se encontró al odontólogo con el ID: 1");

    }

    @Test
    public void test_buscar_odontologo_ok() throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante {

        //Arrange
        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 1234);
        when(repository.findById(anyLong())).thenReturn(Optional.of(odontologo));

        OdontologoDtoResponse odontologoDto = service.buscarOdontologoPorId(1L);

        Assertions.assertEquals(odontologo.getNombre(), odontologoDto.getNombre());

    }

    @Test
    void test_guardar_apellido_vacio() throws ExcepcionParametroFaltante {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setNombre("Juan");
        odontologoDtoRequest.setApellido("");
        odontologoDtoRequest.setMatricula(1234);
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.guardarOdontologo(odontologoDtoRequest),
                "Debe completar el campo apellido");

    }

    @Test
    void test_guardar_nombre_vacio() {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setNombre("");
        odontologoDtoRequest.setApellido("Lopez");
        odontologoDtoRequest.setMatricula(1234);
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.guardarOdontologo(odontologoDtoRequest),
                "Debe completar el campo nombre");

    }

    @Test
    void test_guardar_nombre_corto() throws ExcepcionParametroInvalido {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setNombre("Ju");
        odontologoDtoRequest.setApellido("An");
        odontologoDtoRequest.setMatricula(1234);
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroInvalido.class,
                () -> service.guardarOdontologo(odontologoDtoRequest),
                "Tanto nombre como apellido deben tener 3 o más caracteres");

    }

    @Test
    void test_guardar_matricula_vacia() throws ExcepcionParametroFaltante {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setNombre("Juan");
        odontologoDtoRequest.setApellido("Perez");
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.guardarOdontologo(odontologoDtoRequest),
                "Debe especificar la matrícula");

    }

    @Test
    void test_guardar_matricula_negativa() throws ExcepcionParametroFaltante {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setNombre("Juan");
        odontologoDtoRequest.setApellido("Perez");
        odontologoDtoRequest.setMatricula(-1);
        //Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroInvalido.class,
                () -> service.guardarOdontologo(odontologoDtoRequest),
                "La matrícula debe ser numérica y mayor a 0");

    }


    @Test
    void test_guardar_odontologo_ok() throws ExcepcionDuplicado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setNombre("Juan");
        odontologoDtoRequest.setApellido("Perez");
        odontologoDtoRequest.setMatricula(1234);

        when(repository.findByMatricula(anyInt())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(new Odontologo(1L, "Juan", "Perez", 1234));

        // Act
        OdontologoDtoResponse response = service.guardarOdontologo(odontologoDtoRequest);

        // Assert
        assertEquals(1234, response.getMatricula());
    }

    @Test
    void test_guardar_duplicado() throws ExcepcionDuplicado {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setNombre("Juan");
        odontologoDtoRequest.setApellido("Perez");
        odontologoDtoRequest.setMatricula(1234);
        when(repository.findByMatricula(anyInt())).thenReturn(Optional.of(new Odontologo()));

        // Act & Assert
        Assertions.assertThrows(
                ExcepcionDuplicado.class,
                () -> service.guardarOdontologo(odontologoDtoRequest),
                "Ya existe un odontólogo con esta matrícula");

    }

    @Test
    void test_modificar_id_null() {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setNombre("Carlos");
        odontologoDtoRequest.setApellido("Gonzalez");
        odontologoDtoRequest.setMatricula(1234);

        // Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.modificarOdontologo(odontologoDtoRequest),
                "Debes elegir un odontólogo");
    }

    @Test
    void test_modificar_id_not_found() {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setId(1L);
        odontologoDtoRequest.setNombre("Carlos");
        odontologoDtoRequest.setApellido("Gonzalez");
        odontologoDtoRequest.setMatricula(1234);
        when(repository.findByMatricula(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(
                ExcepcionRecursoNoEncontrado.class,
                () -> service.modificarOdontologo(odontologoDtoRequest),
                "No se encontró al odontólogo con ID: 1");

    }

    @Test
    void test_modificar_odontologo_ok() throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        //Arrange
        OdontologoDtoRequest odontologoDtoRequest = new OdontologoDtoRequest();
        odontologoDtoRequest.setId(1L);
        odontologoDtoRequest.setNombre("Matias");
        odontologoDtoRequest.setApellido("Gonzalez");
        odontologoDtoRequest.setMatricula(1234);

        when(repository.findById(anyLong())).thenReturn(Optional.of(new Odontologo(1L, "Carlos", "Gonzalez", 1234)));
        when(repository.save(any())).thenReturn(new Odontologo(1L, "Matias", "Gonzalez", 1234));

        // Act
        OdontologoDtoResponse response = service.modificarOdontologo(odontologoDtoRequest);

        // Assert
        Assertions.assertEquals("Matias", response.getNombre());
    }

    @Test
    void test_eliminar_id_null() {

        // Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroFaltante.class,
                () -> service.eliminarOdontologo(null),
                "Debes elegir un odontólogo");

    }

    @Test
    void test_eliminar_not_found() {

        // Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        // Act & Assert
        Assertions.assertThrows(
                ExcepcionRecursoNoEncontrado.class,
                () -> service.eliminarOdontologo(1L),
                "No se encontró al odontólogo con el ID: 1");

    }

    @Test
    public void test_eliminar_ocupado() {

        // Arrange
        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 1234);
        Paciente paciente = new Paciente();
        when(repository.findById(anyLong())).thenReturn(Optional.of(odontologo));
        List<Turno> listaDeTurnos = new ArrayList<>();
        listaDeTurnos.add(new Turno(1L, LocalDate.now(), LocalTime.now(), odontologo, paciente));
        when(turnoRepository.listarPorOdontologoId(anyLong())).thenReturn(listaDeTurnos);

        // Act & Assert
        Assertions.assertThrows(
                ExcepcionParametroInvalido.class,
                () -> service.eliminarOdontologo(1L),
                "No puede eliminar este odontólogo porque posee turnos agendados");
    }

    @Test
    public void test_eliminar_odontologo_ok() throws ExcepcionRecursoNoEncontrado, ExcepcionParametroFaltante, ExcepcionParametroInvalido {

        // Arrange
        Odontologo odontologo = new Odontologo(1L, "Juan", "Perez", 1234);
        when(repository.findById(anyLong())).thenReturn(Optional.of(odontologo));
        when(turnoRepository.listarPorOdontologoId(anyLong())).thenReturn(Collections.emptyList());

        // Act
        OdontologoDtoResponse eliminado = service.eliminarOdontologo(1L);

        // Assert
        assertEquals("Perez", eliminado.getApellido());
    }
}