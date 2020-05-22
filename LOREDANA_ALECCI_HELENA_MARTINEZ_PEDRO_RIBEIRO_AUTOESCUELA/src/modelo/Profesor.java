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
	 * @param numClases Número de clases que ha impartido.
	 * @param Dni dni del alumno al que ha impartido clases.
	 * @param Autoescuela que se está gestionando
	 */
	public String imparte_clase(Autoescuela auto, int numClases, String dni) {
		
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
		
		return retorno;
		
	}
	
	/**
	 * Método que resta la gasolina de forma proporcional a las clases impartidas.
	 * @param numClases Número de clases dadas.
	 * @param Autoescuela que se está gestionando
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
	 * @param Nombre del arreglo
	 * @param Precio del arreglo
	 * @param Autoescuela que se está gestionando
	 */
	public void necesidad_arreglo(String nombre, float precio, Autoescuela auto) {
		coche.getLista_arreglos().add(new Arreglo(nombre, precio));
		coche.actualizarVehiculoEnAutoescuela(auto);
	}
	
	/**
	 * Método que muestra los alumnos que tiene asignado el profesor
	 * @return Informacion de los alumnos.
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
