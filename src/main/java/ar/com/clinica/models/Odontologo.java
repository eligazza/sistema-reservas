package ar.com.clinica.models;

public class Odontologo {

    // ATRIBUTOS
    private String matricula;

    // CONSTRUCTOR
    public Odontologo() {}
    public Odontologo(String matricula) {
        this.matricula = matricula;
    }

    // Getters & Setters
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
