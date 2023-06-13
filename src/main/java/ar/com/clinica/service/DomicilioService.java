package ar.com.clinica.service;

import ar.com.clinica.dto.DomicilioDto;
import ar.com.clinica.entity.Domicilio;
import ar.com.clinica.repository.IDomicilioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomicilioService implements IService<Domicilio, DomicilioDto> {

    @Autowired
    private IDomicilioRepository repository;
    ObjectMapper mapper = Mapper.getMapper(false, false);

    @Override
    public List<DomicilioDto> listar() {

        List<Domicilio> listaEntidades = repository.findAll();
        return listaEntidades
                .stream()
                .map(domicilio -> mapper.convertValue(domicilio, DomicilioDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public DomicilioDto buscarPorId(Long id) {

        if (repository.findById(id).isPresent()) {
            return mapper.convertValue(repository.findById(id).get(), DomicilioDto.class);
        }
        return null;
    }

    @Override
    public DomicilioDto insertar(Domicilio domicilio) {

        return mapper.convertValue(repository.save(domicilio), DomicilioDto.class);
    }

    @Override
    public DomicilioDto modificar(Domicilio domicilioNuevo) {

        Long idBuscado = domicilioNuevo.getId();
        Domicilio domicilio = null;
        if (repository.findById(idBuscado).isPresent()) {
            domicilio = repository.findById(idBuscado).get();
            domicilio.setCalle(domicilioNuevo.getCalle());
            domicilio.setLocalidad(domicilioNuevo.getLocalidad());
            domicilio.setNumero(domicilioNuevo.getNumero());
        }
        return mapper.convertValue(repository.save(domicilio), DomicilioDto.class);
    }

    @Override
    public DomicilioDto eliminar(Long id) {

        Domicilio domicilioEliminado = null;
        if (repository.findById(id).isPresent()) {
            domicilioEliminado = repository.findById(id).get();
            repository.deleteById(id);
        }
        return mapper.convertValue(domicilioEliminado, DomicilioDto.class);
    }
}
