package ar.com.clinica.repository;

import ar.com.clinica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
