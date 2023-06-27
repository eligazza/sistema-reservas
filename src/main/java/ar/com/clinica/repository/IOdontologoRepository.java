package ar.com.clinica.repository;

import ar.com.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    Optional<Odontologo> findByMatricula(Integer matricula);
}
