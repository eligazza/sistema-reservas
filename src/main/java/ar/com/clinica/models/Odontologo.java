package ar.com.clinica.models;

public class Odontologo {

    private String matricula;


    public Odontologo() {
    }

    public Odontologo(String matricula) {
        this.matricula = matricula;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
