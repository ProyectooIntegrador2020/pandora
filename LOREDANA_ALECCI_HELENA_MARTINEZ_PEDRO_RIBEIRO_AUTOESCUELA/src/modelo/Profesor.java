package modelo;

import java.sql.SQLException;
import java.util.HashSet;

import DAO.BBDD;

/**
 * Clase que almacena a los alumnos asignados a cada profesor, su coche asignado y que contiene las funcionalidades de
 * impartir clase y añadir arreglos al coche asignado.
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 *
 */
public class Profesor extends Persona {
	
	/**
	 * Lista de alumnos que tiene asignado el profesor.
	 */
	private HashSet<Alumnos> lista_alumnos_prac;
	/**
	 * Coche que usa el profesor para dar clases.
	 */
	private Coches coche;

	/**
	 * Cosntructor principal para crear un objeto de tipo Profesor.
	 * @param dni String con el DNI del profesor.
	 * @param edad int con la edad del profesor
	 * @param nombre String con el nombre del profesor
	 * @param num int con el número de teléfono del profesor
	 * @param coche un objeto de tipo Coches que es el que va a usar el profesor.
	 */
	public Profesor(String dni, int edad, String nombre, int num, Coches coche) {
		super(dni, edad, nombre, num);
		// TODO Auto-generated constructor stub
		this.lista_alumnos_prac = new HashSet<Alumnos>();
		this.coche = new Coches(coche);
	}
	
	/**
	 * Constructor de copia de la clase Profesor.
	 * @param e un objeto tipo Profesor del que se copiará la información.
	 */
	public Profesor(Profesor e) {
		super(e);
		// TODO Auto-generated constructor stub
		this.lista_alumnos_prac = e.getLista_alumnos_prac();
		this.coche = e.getCoche();
	}
	
	public HashSet<Alumnos> getLista_alumnos_prac() {
		return lista_alumnos_prac;
	}

	public void setLista_alumnos_prac(HashSet<Alumnos> lista_alumnos_prac) {
		this.lista_alumnos_prac = lista_alumnos_prac;
	}



	public Coches getCoche() {
		return coche;
	}



	public void setCoche(Coches coche) {
		this.coche = coche;
	}

	
	/**
	 * Método que añade un numero de clases impartidas al alumnno correspondiente y anota cuanta gasolina ha gastado.
	 * @param auto Autoescuela que se está gestionando
	 * @param numClases int Número de clases que ha impartido.
	 * @param dni String dni del alumno al que ha impartido clases.
	 * @return String con el resultado de la operación.
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public String imparte_clase(Autoescuela auto, int numClases, String dni) throws SQLException {
		
		boolean hecho = false;
		String retorno = "";
		
		//Si el coche no tiene gasolina no se imparten clases.
		if (coche.getLitros_gasolina() == 0)
			return "No hay gasolina para dar clases.";
		
		//Recorre la lista de alumnos para encontrar al que dio clases y restarselas.
		for (Alumnos a: lista_alumnos_prac) {
			if (a.getDni().equalsIgnoreCase(dni)) {
				//Si no se han podido restar clases al alumno se sale del bucle y se cancela el resto de la operacion.
				if (!a.restarClases(numClases))
					break;
				retorno += "Se han impartido " + numClases + " clases al alumno/a " + a.getNombre();
				hecho = true;
				break;
			}
		}
		//Si no se encontró al alumno no se registran las clases.
		if (!hecho)
			return "El alumno que introdujiste no existe o no le quedan clases.";
		
		restarGasolina(numClases, auto);
		BBDD.actualizarGasolina(coche.getMatricula());
		return retorno;
		
	}
	
	/**
	 * Método que resta la gasolina de forma proporcional a las clases impartidas.
	 * @param numClases Número de clases dadas.
	 * @param auto Autoescuela que se está gestionando
	 */
	public void restarGasolina(int numClases, Autoescuela auto) {
		//Si se encontró se gastará unos litros de gasolina proporcionales al numero de clases impartidas.
		if (numClases <= 5)
			coche.setLitros_gasolina(coche.getLitros_gasolina()-15);
		else if (numClases > 5 && numClases <= 10)
			coche.setLitros_gasolina(coche.getLitros_gasolina()-30);
		else if (numClases > 10) {
			coche.setLitros_gasolina(coche.getLitros_gasolina()-50);
		}
		coche.actualizarVehiculoEnAutoescuela(auto);
	}
	
	/**
	 * Método que permite añadir un arreglo necesario al coche asignado del profesor.
	 * @param nombre Nombre del arreglo
	 * @param precio Precio del arreglo
	 * @param auto Autoescuela que se está gestionando
	 * @throws SQLException Una excepción que proporciona información sobre un error de acceso a la base de datos
	 */
	public void necesidad_arreglo(String nombre, float precio, Autoescuela auto) throws SQLException {
		coche.getLista_arreglos().add(new Arreglo(nombre, precio));
		coche.actualizarVehiculoEnAutoescuela(auto);
		
		BBDD.insertarArreglo(coche.getMatricula(), nombre, precio);
	}
	
	/**
	 * Método que muestra los alumnos que tiene asignado el profesor
	 * @return String Informacion de los alumnos.
	 */
	public String mostrarAlumnos() {
		String cadena = "\n";
		
		for (Alumnos a: this.lista_alumnos_prac) {
			cadena += a.toString() + "\n";
		}
		return cadena;
	}

	@Override
	public String toString() {
		return "Profesor " + super.toString() + ", " + coche.toString() + "]";
	}
	
}
