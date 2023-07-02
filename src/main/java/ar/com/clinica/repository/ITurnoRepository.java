package ar.com.clinica.repository;

import ar.com.clinica.entity.Turno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

    @Query("SELECT t FROM Turno t WHERE t.paciente.id = ?1 ORDER BY t.fecha")
    List<Turno> listarPorPacienteId(Long id);

    @Query("SELECT t FROM Turno t WHERE t.odontologo.id = ?1 ORDER BY t.fecha")
    List<Turno> listarPorOdontologoId(Long id);



}
