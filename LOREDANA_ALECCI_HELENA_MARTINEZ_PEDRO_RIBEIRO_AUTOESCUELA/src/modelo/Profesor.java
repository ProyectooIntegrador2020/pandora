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
	 * Método que añade un numero de clases impartidas al alumnno correspondiente y anota cuanta gasolina ha gastado.
	 * @param numClases Número de clases que ha impartido.
	 * @param Dni dni del alumno al que ha impartido clases.
	 */
	public String imparte_clase(int numClases, String dni) {
		
		boolean hecho = false;
		String retorno = "";
		
		//Si el coche no tiene gasolina no se imparten clases.
		if (coche.getLitros_gasolina() == 0)
			return "No hay gasolina para dar clases.";
		
		//Recorre la lista de alumnos para encontrar al que dio clases y restarselas.
		for (Alumnos a: lista_alumnos_prac) {
			if (a.getDni().equalsIgnoreCase(dni)) {
				a.restarClases(numClases);
				retorno += "Se han impartido " + numClases + " clases al alumno/a " + a.getNombre();
				hecho = true;
				break;
			}
		}
		//Si no se encontró al alumno no se registran las clases.
		if (!hecho)
			return "El alumno que introdujiste no existe. No se han registrado las clases.";
		
		restarGasolina(numClases);
		
		return retorno;
		
	}
	
	/**
	 * Método que resta la gasolina de forma proporcional a las clases impartidas.
	 * @param numClases Número de clases dadas.
	 */
	public void restarGasolina(int numClases) {
		//Si se encontró se gastará unos litros de gasolina proporcionales al numero de clases impartidas.
		if (numClases <= 5)
			coche.setLitros_gasolina(coche.getLitros_gasolina()-15);
		else if (numClases > 5 && numClases <= 10)
			coche.setLitros_gasolina(coche.getLitros_gasolina()-30);
		else if (numClases > 10) {
			coche.setLitros_gasolina(coche.getLitros_gasolina()-50);
		}
	}
	
	/**
	 * Método que permite añadir un arreglo necesario al coche asignado del profesor.
	 * @param Nombre del arreglo
	 * @param Precio del arreglo
	 */
	public void necesidad_arreglo(String nombre, float precio) {
		coche.getLista_arreglos().add(new Arreglo(nombre, precio));
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
