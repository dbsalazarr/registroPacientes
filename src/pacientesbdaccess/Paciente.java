package pacientesbdaccess;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author dbsal
 */
public class Paciente {
    private String DNI;
    private String aPaterno;
    private String aMaterno;
    private String nombres;
    private LocalDate fechaNacimiento;
    private String procedencia;
    private String tipoSangre;
    private float peso;
    
    // CONSTRUCTORES
    
    public Paciente(){
        this.DNI = "";
        this.aPaterno = "";
        this.aMaterno = "";
        this.nombres = "";
        this.fechaNacimiento = LocalDate.now();
        this.procedencia = "";
        this.tipoSangre = "";
        this.peso = 10;
    }

    public Paciente(String DNI, String aPaterno, String aMaterno, String nombres, LocalDate fechaNacimiento, String procedencia, String tipoSangre, float peso) {
        this.DNI = DNI;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.procedencia = procedencia;
        this.tipoSangre = tipoSangre;
        this.peso = peso;
    }
    
    // SETTERS

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    // GETTERS 

    public String getDNI() {
        return DNI;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public float getPeso() {
        return peso;
    }
    
    // OTROS MÉTODOS
    
    public String mostrarPaciente() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return " /// ==== PACIENTE ==== ///" + "\nDNI: " + DNI + " \nApellido Paterno: " + aPaterno + " \nApellido Materno: " + aMaterno +
                " \nNombres: " + nombres + " \nFecha de Nacimiento: " + formatoFecha.format(fechaNacimiento) + " \nProcedencia: " + procedencia +
                " \nTipo Sangre: " + tipoSangre + " \npeso: " + peso;
    }
    
    
}
