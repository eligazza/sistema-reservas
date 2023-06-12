package ar.com.clinica.service;

import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.repository.IOdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OdontologoService implements IService<Odontologo> {

    @Autowired
    private IOdontologoRepository repository;

    @Autowired
    public OdontologoService(IOdontologoRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Odontologo> listar() {
        if (repository.findAll().isEmpty()) {
            return null;
        } return repository.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Odontologo odontologoBuscado = null;
        if (repository.findById(id).isPresent()) {
            odontologoBuscado = repository.findById(id).get();
        }
        return odontologoBuscado;
    }

    @Override
    public Odontologo insertar(Odontologo odontologo) {
        return repository.save(odontologo);
    }

    @Override
    public Odontologo modificar(Odontologo odontologoModificado) {
        Long idBuscado = odontologoModificado.getId();
        Odontologo odontologo = null;
        if (repository.findById(idBuscado).isPresent()) {
            odontologo = repository.findById(idBuscado).get();
            odontologo.setNombre(odontologoModificado.getNombre());
            odontologo.setApellido(odontologoModificado.getApellido());
            odontologo.setMatricula(odontologoModificado.getMatricula());
        }
        return repository.save(odontologo);
    }

    @Override
    public Odontologo eliminar(Long id) {
        Odontologo odontologo = repository.findById(id).get();
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        return odontologo;
    }


}
