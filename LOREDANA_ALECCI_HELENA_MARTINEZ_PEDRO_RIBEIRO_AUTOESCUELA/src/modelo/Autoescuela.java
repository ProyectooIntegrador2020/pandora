package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * Esta clase es la encargada de repartir el sueldo a los trabajadores, de almacenar sus alumnos, trabajadores y vehículos
 * y de almacenar los beneficios sobrantes.
 * 
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class Autoescuela implements tipos_matricula_examen {
	
	private HashSet<Profesor> lista_profesores;
	private HashSet<Alumnos> lista_alumnos;
	private HashSet<Coches> lista_vehiculos;
	private LinkedHashSet<Alumnos> lista_alum_espera;
	private float beneficios;
	
	public Autoescuela() {
		this.lista_profesores = new HashSet<Profesor>();
		this.lista_alumnos = new HashSet<Alumnos>();
		this.lista_vehiculos = new HashSet<Coches>();
		this.lista_alum_espera = new LinkedHashSet<Alumnos>();
		this.beneficios = 0f;
	}
	
	/**
	 * Método encargado de repartir el beneficio a los trabajadores.
	 */
	public void pago_personal() {
		
	}
	
	/**
	 * Método que selecciona a los alumnos pendientes de cobrar.
	 * @return ArrayList Una lista de alumnos pendiente de cobro.
	 * @see <a href="https://www.w3schools.com/java/java_arraylist.asp">ArrayList de Java</a>
	 */
	public ArrayList<Alumnos> filtrarAlumnos() {
		//ArrayList que va a alojar los alumnos que no han pagado aun.
		ArrayList<Alumnos> sinPagar = new ArrayList<Alumnos>();
		
		//Se recorre la lista de alumnos de la autoescuela y si el alumno no ha pagado se añade al ArrayList.
		for (Alumnos a: lista_alumnos)
			if (!a.isPagado())
				sinPagar.add(a);
			
		return sinPagar;
	}
	
	/**
	 * Método sobrecargado que filtra los alumnos por el tipo de examen que tienen pendiente.
	 * @param tipoExamen opción enum por la que quieres seleccionar.
	 * @return listaAlumnos Un ArrayList de Alumnos solo con un tipo de examen.
	 * @see <a href="https://www.w3schools.com/java/java_arraylist.asp">ArrayList de Java</a>
	 */
	public ArrayList<Alumnos> filtrarAlumnos(tipoExamen tipo) {
		//ArrayList que va a almacenar los alumnos con el examen practico pendiente.
		ArrayList<Alumnos> pracPendiente = new ArrayList<Alumnos>();
		
		//Se recorre la lista de alumnos de la autoescuela y comprueba si el alumno tiene pendiente el examen
		// practico. Si es así, se le añade al arraylist.
		for (Alumnos a: lista_alumnos)
			if (a.getExamen() == tipo)
				pracPendiente.add(a);
		
		return pracPendiente;
	}
	
	/**
	 * Método que recibe una lista de alumnos elegibles para ser aprobados y genera un número aleatorio de aprobados.
	 * @param tipoExamen filtro para crear una lista de alumnos elegibles.
	 * @return alumnos ArrayList de alumnos aprobados.
	 * @see <a href="https://www.w3schools.com/java/java_arraylist.asp">ArrayList de Java</a>
	 */
	public ArrayList<Alumnos> recibirAprobados(tipoExamen tipo) {
		//ArrayList para alojar los alumnos que son elegibles para esta operación.
		ArrayList<Alumnos> alumnos = new ArrayList<Alumnos>();
		//ArrayList que contendrá los alumnos aprobados.
		ArrayList<Alumnos> alumnosAprobados = new ArrayList<Alumnos>();
		
		//Generador de números.
		Random generator = new Random();
		
		//Recogemos los alumnos que son elegibles para aprobar.
		alumnos.addAll(filtrarAlumnos(tipo));
		
		//Seleccionamos una cantidad de alumnos que van a aprobar.
		int numAprobados = generator.nextInt(alumnos.size());
		
		//Tantas veces como la cantidad de alumnos que van a aprobar, se genera un numero aleatorio que no sobrepase la
		// cantidad de alumnos elegibles. Ese numero indica la posición del alumno en la lista, se coge ese alumno y se
		// pasa a la lista de aprobados definitivos.
		for (int i = 0; i<numAprobados; i++)
			alumnosAprobados.add(alumnos.get(generator.nextInt(alumnos.size())));
		
		return alumnosAprobados;
	}
	
	/**
	 * Método que muestra por pantalla la lista de alumnos de la autoescuela.
	 * @return String Contenido de la lista de alumnos.
	 */
	public String mostrarAlumnos() {
		String cadena = "\n";
		
		for (Alumnos a: this.lista_alumnos) {
			cadena += a.toString() + "\n";
		}
		return cadena;
	}
	
	/**
	 * Método que muestra por pantalla la lista de coches de la autoescuela.
	 * @return String Contenido de la lista de coches.
	 */
	public String mostrarCoches() {
		String cadena = "\n";
		
		for (Coches c: this.lista_vehiculos) {
			cadena += c.toString() + "\n";
		}
		return cadena;
	}
	
	/**
	 * Método que muestra por pantalla la lista de profesores de la autoescuela.
	 * @return String Contenido de la lista de profesores.
	 */
	public String mostrarProfes() {
		String cadena = "\n";
		
		for (Profesor p: this.lista_profesores) {
			cadena += p.toString() + "\n";
		}
		return cadena;
	}
	
	/**
	 * Método que muestra por pantalla la lista de alumnos en espera.
	 * @return String Contenido de la lista de alumnos en espera.
	 */
	public String mostrarAlumnosEspera() {
		String cadena = "";

		for (Alumnos a: this.lista_alum_espera) {
			cadena += a.toString() + "\n";
		}
		return cadena;
	}

	public HashSet<Profesor> getLista_profesores() {
		return lista_profesores;
	}

	public void setLista_profesores(HashSet<Profesor> lista_profesores) {
		this.lista_profesores = lista_profesores;
	}

	public HashSet<Alumnos> getLista_alumnos() {
		return lista_alumnos;
	}

	public void setLista_alumnos(HashSet<Alumnos> lista_alumnos) {
		this.lista_alumnos = lista_alumnos;
	}

	public HashSet<Coches> getLista_vehiculos() {
		return lista_vehiculos;
	}

	public void setLista_vehiculos(HashSet<Coches> lista_vehiculos) {
		this.lista_vehiculos = lista_vehiculos;
	}

	public float getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(float beneficios) {
		this.beneficios = beneficios;
	}

	public LinkedHashSet<Alumnos> getLista_alum_espera() {
		return lista_alum_espera;
	}

	public void setLista_alum_espera(LinkedHashSet<Alumnos> lista_alum_espera) {
		this.lista_alum_espera = lista_alum_espera;
	}

	@Override
	public String toString() {
		return "Autoescuela [beneficios=" + beneficios + ", " + "Alumnos matriculados: " + mostrarAlumnos() + "Profesores contratados: " + mostrarProfes() + "Coches en propiedad: " + mostrarCoches() + "Alumnos en lista de espera: " + mostrarAlumnosEspera();
	}
	
}
