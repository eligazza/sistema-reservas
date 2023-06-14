package ar.com.clinica.service;

import ar.com.clinica.dto.OdontologoDto;
import ar.com.clinica.dto.PacienteDto;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;
import ar.com.clinica.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService implements IService<Paciente, PacienteDto> {


    @Autowired
    private IPacienteRepository repository;
    ObjectMapper mapper = Mapper.getMapper(false, false);


    @Override
    public List<PacienteDto> listar() {

        List<Paciente> listaEntidades = repository.findAll();
        if (listaEntidades.isEmpty()){
            return null;
        } return listaEntidades
                .stream()
                .map(paciente -> mapper.convertValue(paciente, PacienteDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDto buscarPorId(Long id) {

        Paciente pacienteBuscado = null;
        if (repository.findById(id).isPresent()) {
            pacienteBuscado = repository.findById(id).get();
        }
        return mapper.convertValue(pacienteBuscado, PacienteDto.class);
    }

    @Override
    public PacienteDto insertar(Paciente paciente) {
        return mapper.convertValue(repository.save(paciente), PacienteDto.class);

    }

    @Override
    public PacienteDto modificar(Paciente pacienteModificado) {

        Long idBuscado = pacienteModificado.getId();
        Paciente paciente = null;

        if (repository.findById(idBuscado).isPresent()) {
            paciente = repository.findById(idBuscado).get();
            paciente.setDni(pacienteModificado.getDni());
            paciente.setApellido(pacienteModificado.getApellido());
            paciente.setNombre(pacienteModificado.getNombre());
            paciente.setFechaDeAlta(pacienteModificado.getFechaDeAlta());
            paciente.setDomicilio(pacienteModificado.getDomicilio());
        }
        return mapper.convertValue(repository.save(paciente), PacienteDto.class);
    }

    @Override
    public PacienteDto eliminar(Long id) {

        Paciente paciente = repository.findById(id).get();
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        return mapper.convertValue(paciente, PacienteDto.class);
    }

}
