package ar.com.clinica.service;

import ar.com.clinica.dto.OdontologoDto;
import ar.com.clinica.dto.OdontologoDtoReq;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OdontologoService implements IService<OdontologoDto, OdontologoDtoReq> {

    @Autowired
    IOdontologoRepository repository;
    @Autowired
    ObjectMapper mapper;
    //ObjectMapper mapper = Mapper.getMapper(false, false);


    @Override
    public List<OdontologoDto> listar() {

        List<Odontologo> listaEntidades = repository.findAll();
        return listaEntidades
                .stream()
                .map(odontologo -> mapper.convertValue(odontologo, OdontologoDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public OdontologoDto buscarPorId(Long id) {

        Odontologo odontologo = null;

        if (repository.findById(id).isPresent()) {
            odontologo = repository.findById(id).get();
        }

        return mapper.convertValue(odontologo, OdontologoDto.class);

    }

    @Override
    public OdontologoDto insertar(OdontologoDtoReq odontologoDtoReq) {

        Odontologo odontologo = mapper.convertValue(odontologoDtoReq, Odontologo.class);
        return mapper.convertValue(repository.save(odontologo), OdontologoDto.class);

    }

    @Override
    public OdontologoDto modificar(OdontologoDtoReq odontologoDtoReq) {

        Odontologo odontologo = mapper.convertValue(odontologoDtoReq, Odontologo.class);
        return mapper.convertValue(repository.save(odontologo), OdontologoDto.class);

    }

    @Override
    public OdontologoDto eliminar(Long id) {

        Odontologo odontologo = repository.findById(id).get();
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        return mapper.convertValue(odontologo, OdontologoDto.class);
    }

}
