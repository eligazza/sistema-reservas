package ar.com.clinica.dto;

import ar.com.clinica.entity.Odontologo;
import ar.com.clinica.entity.Paciente;

public class TurnoDto {

    private String fecha;
    private Odontologo odontologo;
    private Paciente paciente;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
