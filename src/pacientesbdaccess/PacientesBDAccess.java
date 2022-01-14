package pacientesbdaccess;

// IMPORTACIONES
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author dbsal
 */
public class PacientesBDAccess {
    
    public static void main(String[] args) {
       
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese el número de pacientes a registrar");
        int numeroRegistros = entrada.nextInt();
        
        for (int i = 0; i < numeroRegistros; i++) {
            JOptionPane.showMessageDialog(null, "Registrando al Paciente " + (i + 1));
            insertarPaciente(); // Registra un nuevo paciente en base a la Clase Paciente
        }
      
       // Se encarga de recuperar el ultimo paciente registrado y mostrarlo
       Paciente objPaciente = null;
        
       objPaciente = leerPaciente();
       JOptionPane.showConfirmDialog(null, objPaciente.getaPaterno());
       
       System.out.println( objPaciente.mostrarPaciente() );
       
        
    }
    
    public static Paciente leerPaciente(){
        
        // Creando el objeto
        Paciente objPaciente = new Paciente();
        
        
        String rutaConexion = "D:\\PROGRAMACION\\Practicas\\java\\unsaac\\bdProyectosCitas.accdb";
        Connection conexion;
        ResultSet rSet;
        Statement mistatement;
        PreparedStatement pStatement;
        
        try{
            
            conexion = DriverManager.getConnection("jdbc:ucanaccess://" + rutaConexion, "", "");
            System.out.println("Conexión Exitosa");
            
            String query = "select * from tPacientes";
            pStatement = conexion.prepareStatement(query);
            rSet = pStatement.executeQuery();
            while( rSet.next() ){
                objPaciente.setDNI( rSet.getString("DNI") );
                objPaciente.setaPaterno( rSet.getString("APaterno"));
                objPaciente.setaMaterno( rSet.getString("AMaterno"));
                objPaciente.setNombres( rSet.getString("Nombres") );
                // Formateando la Fecha
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("d/MM/yyyy");
                
                String fecha = rSet.getString("FechaNacimiento");
                
                objPaciente.setFechaNacimiento( LocalDate.parse(fecha, formateador) );
                objPaciente.setProcedencia( rSet.getString("Procedencia") );
                objPaciente.setTipoSangre( rSet.getString("TipoSangre"));
                objPaciente.setPeso( rSet.getFloat("Peso") );
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        
        return objPaciente;
    }
    
    public static void insertar(){
        String rutaConexion = "D:\\PROGRAMACION\\Practicas\\java\\unsaac\\bdProyectosCitas.accdb";
        Connection conexion = null;
        Statement mistatement = null;
        
        try{
            
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
            
            conexion = DriverManager.getConnection("jdbc:ucanaccess://" + rutaConexion, "", "");
            System.out.println("Conexión Exitosa");
            
            mistatement = conexion.createStatement();
            String DNI, aPaterno, aMaterno, nombre, fechaNacimiento, procedencia, tipoSangre;
            float peso;
            
            DNI = JOptionPane.showInputDialog("¿Cuál es tu DNI?");
            aPaterno = JOptionPane.showInputDialog("¿ Cúal es su Apellido Paterno?");
            aMaterno = JOptionPane.showInputDialog("¿ Cúal es su Apellido Materno?");
            nombre = JOptionPane.showInputDialog("¿ Cúal es su nombre/s?");
            fechaNacimiento = JOptionPane.showInputDialog("¿ Cúal es su fecha de Nacimiento?");
            procedencia = JOptionPane.showInputDialog("¿Cuál es su lugar de procedencia?");
            tipoSangre = JOptionPane.showInputDialog("¿ Cúal es su tipo de Sangre?");
            peso = Float.parseFloat(JOptionPane.showInputDialog("¿ Cúal es su Peso?"));
            
            
            String queryInsercion;
            queryInsercion = "insert into tPacientes (DNI, APaterno, AMaterno, Nombres, FechaNacimiento, Procedencia, TipoSangre, Peso)";
            queryInsercion += " values('"+DNI+"', '"+aPaterno+"', '"+aMaterno+"', '"+nombre+"', '"+fechaNacimiento+"', '"+procedencia+"', '"+tipoSangre+"', '"+peso+"')";
            
            mistatement.executeUpdate(queryInsercion);
            System.out.println("El registro se inserto con éxito");
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.out.println( e.getMessage() );
        }
        
    }
    
    /* === TAREA === */
    
    // REALIZAR UN MDOULO insertar con el objeto Persona 
    public static void insertarPaciente(){
        
        Paciente objPaciente = new Paciente();
        
        
        String rutaConexion = "D:\\PROGRAMACION\\Practicas\\java\\unsaac\\bdProyectosCitas.accdb";
        Connection conexion = null;
        Statement mistatement = null;
        
        try{
            
            conexion = DriverManager.getConnection("jdbc:ucanaccess://" + rutaConexion, "", "");
            System.out.println("Conexión Exitosa");
            
            mistatement = conexion.createStatement();
            
            objPaciente.setDNI( JOptionPane.showInputDialog("¿Cuál es tu DNI?") );
            objPaciente.setaPaterno( JOptionPane.showInputDialog("¿Cuál es tu Apellido Paterno?") );
            objPaciente.setaMaterno(JOptionPane.showInputDialog("¿Cuál es tu Apellido Materno?"));
            objPaciente.setNombres(JOptionPane.showInputDialog("¿Cuál es tu nombre/es?"));
            String fechaNacimiento = JOptionPane.showInputDialog("¿Cuál es tu fecha de Nacimiento dd/mm/yyyy?");
            
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("d/MM/yyyy");
            objPaciente.setFechaNacimiento( LocalDate.parse(fechaNacimiento, formateador) );
            
            objPaciente.setProcedencia( JOptionPane.showInputDialog("¿Cuál es tu lugar procedencia?") );
            objPaciente.setTipoSangre( JOptionPane.showInputDialog("¿Cuál es tu tipo de Sangre?") );
            objPaciente.setPeso( Float.parseFloat(JOptionPane.showInputDialog("¿Cuál es tu peso?")) );

            String queryInsercion;
            queryInsercion = "insert into tPacientes (DNI, APaterno, AMaterno, Nombres, FechaNacimiento, Procedencia, TipoSangre, Peso)";
            queryInsercion += " values('"+objPaciente.getDNI()+"', '"+objPaciente.getaPaterno()+"', '"+objPaciente.getaMaterno()+"',"
                    + "'"+objPaciente.getNombres()+"', '"+formateador.format( objPaciente.getFechaNacimiento() )+"', '"+objPaciente.getProcedencia()+"',"
                    + " '"+objPaciente.getTipoSangre()+"', '"+objPaciente.getPeso()+"')";
            
            mistatement.executeUpdate(queryInsercion);
            System.out.println("El registro se inserto con éxito");
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.out.println( e.getMessage() );
        }
        
    }

}

