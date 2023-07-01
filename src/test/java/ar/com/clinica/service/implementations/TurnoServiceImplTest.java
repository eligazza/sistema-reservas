package ar.com.clinica.service.implementations;

import ar.com.clinica.dto.response.OdontologoDtoResponse;
import ar.com.clinica.dto.response.PacienteDtoResponse;
import ar.com.clinica.dto.response.TurnoDtoResponse;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.entity.Domicilio;
import ar.com.clinica.entity.Turno;
import ar.com.clinica.exceptions.*;
import ar.com.clinica.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@SpringBootTest
class TurnoServiceImplTest {

    @InjectMocks
    TurnoServiceImpl turnoService;
    @InjectMocks
    PacienteServiceImpl pacienteService;
    @InjectMocks
    OdontologoServiceImpl odontologoService;

    @Mock
    ObjectMapper mapper;

    @Mock
    ITurnoRepository repository;

    @Test
    public void listar_sin_Turnos() {
        //Arrange
        when(repository.findAll()).thenReturn(Collections.emptyList());
        //Assert
        Assertions.assertThrows(ExcepcionNoHayContenido.class, () -> turnoService.listarTurnos(), "No existen turnos registrados");
    }

    @Test
    public void listar_con_Turnos() throws ExcepcionNoHayContenido {
        //Arrange
        List<Turno> lista = new ArrayList<>();
        lista.add(new Turno());
        lista.add(new Turno());
        lista.add(new Turno());
        when(repository.findAll()).thenReturn(lista);
        //Act
        List<TurnoDtoResponse> actual = turnoService.listarTurnos();
        //Assert
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    public void buscar_id_inexistente() {
        //Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        //Assert
        Assertions.assertThrows(ExcepcionRecursoNoEncontrado.class, ()-> turnoService.buscarTurnoPorId(1L), "No se encontró al turno con el ID: 1");
    }


}
