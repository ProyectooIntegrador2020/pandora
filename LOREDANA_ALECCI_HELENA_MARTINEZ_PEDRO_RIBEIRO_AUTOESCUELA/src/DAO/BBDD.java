package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;
import oracle.jdbc.internal.OracleConnection.ReplayOperation;

/**
 * Esta clase contiene las bases de datos, la cual se va a encargar de insertar, actualizar y borrar tablas.
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
		url = "jdbc:oracle:thin:@localhost:1521:"+ bd;
		connection = null;
		contador = 130;
	}
	
	/**
	 * Método que se encarga de conectar la base de datos con java
	 * @throws ClassNotFoundException cuando una aplicación intenta cargar en una clase a través del método forName
	 * @throws SqlException se lanza esta excepción siempre y cuando se trate con bases de datos
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
		PreparedStatement ps = connection.prepareStatement("INSERT INTO ALUMNO (DNIALUMNO, EDAD, NOMBRE, TELEFONO, TIPOEXAMEN, TIPOMATRICULA, CLASESDAR, PAGADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		
		ps.setString(1, dni);
		ps.setInt(2, edad);
		ps.setString(3, nombre);
		ps.setInt(4, telefono);
		ps.setInt(5, 1);
		ps.setInt(6, tipoM);
		ps.setInt(7, 0);
		//0 no 1 si
		ps.setInt(8, 0);
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
		ps.setFloat(2, 60f);
		ps.setFloat(3, 0f);
		ps.executeUpdate();

		ps.close();
	}
	/**
	 * Método que inserta arreglos
	 * @param matricula matrícula del vehiculo que se le hace el arreglo
	 * @param nombrearreglo Nombre del arreglo
	 * @param precioArreglo Precio del arreglo
	 * @return El id del arreglo
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static int insertarArreglo(String matricula, String nombrearreglo, float precioArreglo  ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO ARREGLO VALUES (?, ?, ?, ?)");
		
		int idArreglo;
		idArreglo= contador ;
		ps.setInt(1,idArreglo );
		ps.setString(2, matricula);
		ps.setString(3, nombrearreglo);
		ps.setFloat(4, precioArreglo);
		ps.executeUpdate();
		ps.close();
		contador++;
		
		return idArreglo;
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
	 * @param litros nueva cantidad de litros que tiene el coche
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void actualizarGasolina(String Matricula, float litros ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE COCHE SET LITROS_GASOLINA = ? WHERE MATRICULA = ? ");
		
		ps.setFloat(1, litros );
		ps.setString(2, Matricula );
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Este metodo al repostar te actualiza el precio y los litro
	 * @param Matricula Matrícula del vehiculo
	 * @param litros nueva cantidad de listros que tiene el coche
	 * @param precio El precio que se ha gastado en repostar
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void actualizarPrecioGasol(String Matricula, float litros, float precio ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE COCHE SET LITROS_GASOLINA = ? , PRECIO_GASOLINA = ? WHERE MATRICULA = ? ");
		
		ps.setFloat(1, litros );
		ps.setFloat(2, precio );
		ps.setString(3, Matricula );
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Este metodo actualiza el tipo de examen del alumno
	 * @param dniAlumno dni de el alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void actualizarAlumno(String dniAlumno) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET TIPOEXAMEN = ? WHERE DNIALUMNO = ? ");
		ps.setInt(1, 2);
		ps.setString(2, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Este metodo actualiza el profesor a un alumno
	 * @param dniAlumno dni de el alumno
	 * @param dniprofesor dni de el profesor
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void actualizarAlumnoProfesor(String dniAlumno, String dniprofesor) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET PROFESOR_DNIPROFESOR = ? WHERE DNIALUMNO = ? ");
		ps.setString(1, dniprofesor);
		ps.setString(2, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este metodo actualiza el número de clases del alumno
	 * @param dniAlumno dni de el alumno
	 * @param numclases número de clases que le corresponden
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos

	 */
	public static void actualizarAlumnoClases(String dniAlumno, int numclases) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET CLASESDAR = ? WHERE DNIALUMNO = ? ");
		ps.setInt(1, numclases);
		ps.setString(2, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Este metodo actualiza si el alumno ha pagado
	 * @param dniAlumno Dni del alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void actualizarAlumnoPago(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET PAGADO = 1 WHERE DNIALUMNO = ? ");
		
		ps.setString(1, dniAlumno );
		ps.executeUpdate();
		ps.close();
	}
	
	/**
	 * Este metodo actualiza si el alumno ha pagado o no
	 * @param dniAlumno Dni del alumno
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void actualizarAlumnoNoPago(String dniAlumno ) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE ALUMNO SET PAGADO = 0 WHERE DNIALUMNO = ? ");
		
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
	 * Este método que actualiza el profesor en el coche
	 * @param dniProfesor del profesor al que se le asigna ese coche
	 * @param matricula la matrícula del coche 
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void actualizarProfesorCoche(String dniProfesor, String matricula) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE COCHE SET PROFESOR_DNIPROFESOR = ? WHERE MATRICULA = ? ");

		ps.setString(1, dniProfesor);
		ps.setString(2, matricula);
		ps.executeUpdate();

		ps.close();
	}
	
	/**
	 * Este método que actualiza id del arreglo en el coche
	 * @param id del arreglo de ese coche
	 * @param matricula la matrícula del coche 
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static void actualizarArregloCoche(int id, String matricula) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("UPDATE COCHE SET ARREGLO_IDARREGLO = ? WHERE MATRICULA = ? ");

		ps.setInt(1, id);
		ps.setString(2, matricula);
		ps.executeUpdate();

		ps.close();
	}
	
	/**
	 * Método que se usará en la interfaz para mostrar los datos de los alumnos: guarda los datos.
	 * @param a Autoescuela
	 * @return un arrayList de alumnos
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static ArrayList<Alumnos> obtenerAlumnos(Autoescuela a) throws SQLException{
		Alumnos al;
		ArrayList<Alumnos> lista = new ArrayList<Alumnos>();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM ALUMNO");
		ResultSet resul = ps.executeQuery();
		while(resul.next()) {
			al = new Alumnos(resul.getString(1), resul.getInt(2), resul.getString(3), resul.getInt(4), Recepcionista.asignar_matricula(resul.getInt (6)) );
			lista.add(al);
		}
		
		ps.close();
		return lista;
	}

	
	/**
	 * Método que se usará en la interfaz para mostrar los datos de los coches: guarda los datos.
	 * @param a Autoescuela
	 * @return un arrayList de alumnos
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public static ArrayList<Coches> obtenerCoches(Autoescuela a) throws SQLException{
		Coches c;
		ArrayList<Coches> lista = new ArrayList<Coches>();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM COCHE");
		ResultSet resul = ps.executeQuery();
		while(resul.next()) {
			c = new Coches(resul.getString(1));
			lista.add(c);
		}
		
		ps.close();
		return lista;
	}
}
