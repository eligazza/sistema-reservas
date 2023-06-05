package ar.com.clinica.models;

public class Administrador extends Usuario {

    // ATRIBUTOS
    private int id;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    // CONSTRUCTOR
    public Administrador(String nombre, String apellido, String usuario, String contrasenia, OdontologoService odontologoService, PacienteService pacienteService) {
        super(nombre, apellido, usuario, contrasenia);
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public OdontologoService getOdontologoService() {
        return odontologoService;
    }
    public void setOdontologoService(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    public PacienteService getPacienteService() {
        return pacienteService;
    }
    public void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
}
