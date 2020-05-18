package modelo;

import java.util.HashSet;

/**
 * Clase que almacena a los alumnos asignados a cada profesor, su coche asignado y que contiene las funcionalidades de
 * impartir clase y añadir arreglos al coche asignado.
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 *
 */
public class Profesor extends Persona {
	
	private HashSet<Alumnos> lista_alumnos_prac;
	private Coches coche;

	public Profesor(String dni, int edad, String nombre, int num, Coches coche) {
		super(dni, edad, nombre, num);
		// TODO Auto-generated constructor stub
		this.lista_alumnos_prac = new HashSet<Alumnos>();
		this.coche = new Coches(coche);
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
	 * Método que añade una clase impartida al alumnno correspondiente.
	 */
	public void imparte_clase() {
		
	}
	
	/**
	 * Método que permite añadir un arreglo necesario al coche asignado del profesor.
	 */
	public void necesidad_arreglo() {
		
	}
	
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
