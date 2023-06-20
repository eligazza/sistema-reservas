package ar.com.clinica.dto.req;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurnoDtoReq {

    private Long id;
    private String fecha;
    //@OneToOne
    //@JoinColumn(name = "odontologo_id")
    private Long odontologo;
    //@OneToOne
    //@JoinColumn(name = "paciente_id")
    private Long paciente;

}
