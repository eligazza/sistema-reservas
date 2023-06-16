package ar.com.clinica.service;

import ar.com.clinica.dto.res.OdontologoDtoRes;
import ar.com.clinica.dto.req.OdontologoDtoReq;
import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OdontologoService implements IService<OdontologoDtoRes, OdontologoDtoReq> {

    @Autowired
    IOdontologoRepository repository;
    @Autowired
    ObjectMapper mapper;


    @Override
    public List<OdontologoDtoRes> listar() {

        List<Odontologo> listaEntidades = repository.findAll();
        List<OdontologoDtoRes> odontologosDtos = null;

        if (!listaEntidades.isEmpty()) {
            odontologosDtos = listaEntidades
                    .stream()
                    .map(odontologo -> mapper.convertValue(odontologo, OdontologoDtoRes.class))
                    .collect(Collectors.toList());
        }
        return odontologosDtos;
    }

    @Override
    public OdontologoDtoRes buscarPorId(Long id) {

        Odontologo odontologo = null;

        if (repository.findById(id).isPresent()) {
            odontologo = repository.findById(id).get();
        }

        return mapper.convertValue(odontologo, OdontologoDtoRes.class);

    }

    @Override
    public OdontologoDtoRes insertar(OdontologoDtoReq odontologoDtoReq) {

        Odontologo odontologo = mapper.convertValue(odontologoDtoReq, Odontologo.class);
        Odontologo odontologoGuardado = repository.save(odontologo);
        return mapper.convertValue(odontologoGuardado, OdontologoDtoRes.class);

    }

    @Override
    public OdontologoDtoRes modificar(OdontologoDtoReq odontologoDtoReq) {

        Odontologo odontologo = mapper.convertValue(odontologoDtoReq, Odontologo.class);
        return mapper.convertValue(repository.save(odontologo), OdontologoDtoRes.class);

    }

    @Override
    public OdontologoDtoRes eliminar(Long id) {

        Odontologo odontologo = null;
        if (repository.findById(id).isPresent()) {
            odontologo = repository.findById(id).get();
            repository.deleteById(id);
        }
        return mapper.convertValue(odontologo, OdontologoDtoRes.class);
    }

}
