package ar.com.clinica.repository;

import ar.com.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByDni(String dni);
}
