package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.tipos_matricula_examen.tipoMatricula;

public class BBDD {
	
	private static String bd;
	private static String login;
	private static String password;
	private static String url;
	static Connection connection;
	
	static {
		bd = "XE";
		login = "ProyectoAuto";
		password = "autoproyecto";
		url = "jdbc:oracle:thin:@localhost:1521:"+bd;
		connection = null;
	}
	
	public static void conectar() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection(url, login, password);
		if (connection != null)
			System.out.format("%s\n", "Conexi�n realizada correctamente.");
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

}
