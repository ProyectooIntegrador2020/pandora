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
	/**
	 * Método que cierra la conexión
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void cerrar() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
	/**
	 * Método para insertar alumnos
	 * @param dni DNI de alumno
	 * @param edad edad del alumno
	 * @param nombre Nombre del alumno
	 * @param telefono telefono del alumno
	 * @param tipoM tipo de matrícula
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
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
	/**
	 * Método para insertar profesores
	 * @param dni Dni del profesor
	 * @param mat Matrícula
	 * @param edad edad del profesor
	 * @param nombre nombre del profesor
	 * @param telefono telefono del profesor
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
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
	/**
	 * Método que inserta coches
	 * @param mat Matrícula
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void insertarCoche(String mat) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO COCHE (MATRICULA, LITROS_GASOLINA, PRECIO_GASOLINA) VALUES (?, ?, ?)");

		ps.setString(1, mat);
		ps.setFloat(2, 60);
		ps.setFloat(3, 0);
		ps.executeUpdate();

		ps.close();
	}
	/**
	 * Método que inserta arreglos
	 * @param matricula matrícula del vehiculo que se le hace el arreglo
	 * @param nombrearreglo Nombre del arreglo
	 * @param precioArreglo Precio del arreglo
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void insertarArreglo( String matricula, String nombrearreglo, float precioArreglo  ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO ARREGLO VALUES (?, ?, ?, ?)");
		
		int idArreglo;
		idArreglo=Integer.parseInt("A")+ contador ;
		ps.setInt(1,idArreglo );
		ps.setString(2, matricula);
		ps.setString(3, nombrearreglo);
		ps.setFloat(4, precioArreglo);
		ps.executeUpdate();
		ps.close();
		contador++;
	}
	
	/**
	 * Este metodo actualiza el profesor de un alumno
	 * @param dniProfesor dni del profesor
	 * @param dniAlumno dni de nuestro alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void actualizarProfesor(String dniProfesor, String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET PROFESOR_DNIPROFESOR = ? WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.setString(2, dniProfesor);
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este metodo al repostar te actualiza la gasolina
	 * @param Matricula Matrícula del vehiculo
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void actualizarGasolina(String Matricula ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE COCHE SET LITROS_GASOLINA = ? WHERE MATRICULA = ? ");
		
		ps.setString(1, Matricula );
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este metodo actualiza el tipo de examen del alumno
	 * @param dniAlumno dni de el alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void actualizarAlumno(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET TIPOEXAMEN = ? WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este metodo actualiza si el alumno ha pagado
	 * @param dniAlumno Dni del alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void actualizarAlumnoPago(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET PAGADO = ? WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Este metodo borra a un alumno
	 * @param dniAlumno Dni del alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 * 
	 */
	public static void borrarAlumnoIndividual(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM ALUMNO WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este método borra a un alumno si ha aprobado
	 * @param dniAlumno Dni del alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void borrarAlumnoColectivo(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM ALUMNO WHERE TIPOEXAMEN = 2 AND PAGADO = 1 ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este método borra el profesor
	 * @param dniProfesor Dni del profesor
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void borrarProfesor(String dniProfesor ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM PROFESOR WHERE DNIPROFESOR = ? ");
		
		ps.setString(1, dniProfesor );
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este método borra el profesor de un alumno
	 * @param dniAlumno Dni del alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void borrarAlumnoFromProfesor(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM ALUMNO WHERE DNIPROFESOR = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este método actualiza los beneficios
	 * @param Beneficios Beneficios de la autoescuela
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void actualizarPago(float Beneficios ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE AUTOESCUELA SET BENEFICIOS = ? ");

		ps.setFloat(1, Beneficios);
		ps.executeUpdate();

		ps.close();
	}
	/**
	 * Este método hace un select con todos los alumnos
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void selectAlumnos()throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM ALUMNO ");
		
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este método hace un select con todos los profesores
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void selectProfesores()throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM PROFESOR ");
		
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este método hace un select con todos los coches
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */

	public static void selectCoches()throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM COCHE ");
		
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Este método recoge los datos de x alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void selectPersona()throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM ALUMNO WHERE DNI = ? ");
		
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Este método recoge los datos de x profesor
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void selectPersona2()throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM PROFESOR WHERE DNI = ? ");
		
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Este método recoge los datos de x coche
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void selectCocheMatricula()throws SQLException {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM COCHE WHERE MATRICULA = ? ");
		
		ps.executeUpdate();
		ps.close();
	}
}
