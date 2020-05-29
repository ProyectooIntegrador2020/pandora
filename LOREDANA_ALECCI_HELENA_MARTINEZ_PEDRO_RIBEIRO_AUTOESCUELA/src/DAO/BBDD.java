package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.tipos_matricula_examen.tipoMatricula;

/**
 * Esta clase contiene las bases de datos, la cual se va a encargar de insertar, actualizar o borrar tablas.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */

public class BBDD {
	
	private static String bd;
	private static String login;
	private static String password;
	private static String url;
	static Connection connection;
	private static int contador;
	
	static {
		bd = "XE";
		login = "ProyectoAuto";
		password = "autoproyecto";
		url = "jdbc:oracle:thin:@localhost:1521:"+bd;
		connection = null;
		contador = 1;
	}
	
	/**
	 * Método que se encarga de conectar la base de datos con java
	 * @throws SqlException se lanza esta excepción siempre y cuando se trate con bases de datos
	 * @throws ClassNotFoundException cuando una aplicación intenta cargar en una clase a través del método forName
	 */
	
	public static void conectar() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection(url, login, password);
		if (connection != null)
			System.out.format("%s\n", "Conexión realizada correctamente.");
	}
	
	public static void cerrar() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
	
	public static void insertarAlumns(String dni, int edad, String nombre, int telefono, int tipoM) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO ALUMNO (DNIALUMNO, EDAD, NOMBRE, TELEFONO, TIPOEXAMEN, TIPOMATRICULA, CLASESDAR) VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		ps.setString(1, dni);
		ps.setInt(2, edad);
		ps.setString(3, nombre);
		ps.setInt(4, telefono);
		ps.setInt(5, 1);
		ps.setInt(6, tipoM);
		ps.setInt(7, 0);
		ps.executeUpdate();
		
		ps.close();
	}
	
	public static void insertarProfe(String dni, String mat, int edad, String nombre, int telefono) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO PROFESOR (DNIPROFESOR, MATRICULA, EDAD, NOMBRE, TELEFONO) VALUES (?, ?, ?, ?, ?)");
		
		ps.setString(1, dni);
		ps.setString(2, mat);
		ps.setInt(3, edad);
		ps.setString(4, nombre);
		ps.setInt(5, telefono);
		ps.executeUpdate();
		
		ps.close();
	}
	
	public static void insertarCoche(String mat) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO COCHE (MATRICULA, LITROS_GASOLINA, PRECIO_GASOLINA) VALUES (?, ?, ?)");

		ps.setString(1, mat);
		ps.setFloat(2, 60);
		ps.setFloat(3, 0);
		ps.executeUpdate();

		ps.close();
	}
	
	public static void insertarArreglo(int idArreglo, String matricula, String nombrearreglo, float precioArreglo  ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO ARREGLO VALUES (?, ?, ?, ?)");
		idArreglo=Integer.parseInt("A")+ contador ;
		ps.setInt(1,idArreglo );
		ps.setString(2, matricula);
		ps.setString(3, nombrearreglo);
		ps.setFloat(4, precioArreglo);
		ps.executeUpdate();
		ps.close();
		contador++;
	}

	
	public static void actualizarProfesor(String dniProfesor, String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET PROFESOR_DNIPROFESOR = ? WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.setString(2, dniProfesor);
		ps.executeUpdate();
		ps.close();
	}
	
	public static void actualizarProfesorVacio(String dniProfesor, String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET DNIALUMNO = ? WHERE PROFESOR_DNIPROFESOR = NULL ");
		
		ps.setString(1, dniAlumno );
		ps.setString(2, dniProfesor);
		ps.executeUpdate();
		ps.close();
	}
	
	public static void actualizarCoche(String Matricula ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE COCHE SET MATRICULA = ? WHERE MATRICULA = ? ");
		
		ps.setString(1, Matricula );
		ps.executeUpdate();
		ps.close();
	}
	
	public static void actualizarGasolina(String Matricula ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE COCHE SET LITROS_GASOLINA = ? WHERE MATRICULA = ? ");
		
		ps.setString(1, Matricula );
		ps.executeUpdate();
		ps.close();
	}
	
	public static void actualizarAlumno(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET TIPOEXAMEN = ? WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	
	public static void actualizarAlumnoPago(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET PAGADO = ? WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	
	public static void borrarAlumnoIndividual(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM ALUMNO WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	
	public static void borrarAlumnoColectivo(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM ALUMNO WHERE TIPOEXAMEN = 2 AND PAGADO = 1 ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	
	public static void borrarProfesor(String dniProfesor ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM PROFESOR WHERE DNIPROFESOR = ? ");
		
		ps.setString(1, dniProfesor );
		ps.executeUpdate();
		ps.close();
	}
	
	public static void borrarAlumnoFromProfesor(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM ALUMNO WHERE DNIPROFESOR = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	
	public static void actualizarPago(int Beneficios ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE AUTOESCUELA SET BENEFICIOS = ? ");

		ps.setInt(1, Beneficios);
		ps.executeUpdate();

		ps.close();
	}
	
	
}
