package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * Esta clase es la encargada de repartir el sueldo a los trabajadores, de almacenar sus alumnos, trabajadores y veh�culos
 * y de almacenar los beneficios sobrantes.
 * 
 * @author Helena Mart�nez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class Autoescuela implements tipos_matricula_examen {
	
	/**
	 * Atributo que almacena los profesores que est�n actualmente dados de alta en la autoescuela.
	 */
	private HashSet<Profesor> lista_profesores;
	/**
	 * Atributo que almacena el total de alumnos matriculados en la autoescuela.
	 */
	private HashSet<Alumnos> lista_alumnos;
	/**
	 * Atributo que almacena los coches de los que dispone la autoescuela.
	 */
	private HashSet<Coches> lista_vehiculos;
	/**
	 * Lista de espera que almacena los alumnos que a�n no han sido asignados a un profesor. Todo aquel que est� en esta
	 * lista deber� tener pendiente el examen pr�ctico.
	 */
	private LinkedHashSet<Alumnos> lista_alum_espera;
	/**
	 * Beneficios de la autoescuela tras haber pagado a los profesores.
	 */
	private float beneficios;
	
	/**
	 * Constructor principal de la autoescuela.
	 */
	public Autoescuela() {
		this.lista_profesores = new HashSet<Profesor>();
		this.lista_alumnos = new HashSet<Alumnos>();
		this.lista_vehiculos = new HashSet<Coches>();
		this.lista_alum_espera = new LinkedHashSet<Alumnos>();
		this.beneficios = 0f;
	}
	
	/**
	 * M�todo encargado de repartir el beneficio a los trabajadores, y en caso de que sobre se almacena para el mes
	 * siguiente.
	 * @param cobros float con los cobros finales a repartir entre profesores.
	 */
	public void pago_personal(float cobros) {
		//Dinero a repartir este mes es los cobros recientes m�s los beneficios acumulados.
		float beneficios = cobros + this.beneficios;
		//Si tenemos para pagarle el sueldo completo a cada profesor, lo pagamos y el resto se almacena.
		if ((beneficios/lista_profesores.size()) >= 2000) {
			beneficios -= 2000*lista_profesores.size();
			this.beneficios = beneficios;
		} else //Si no hay suficiente para todos, se entiende que se reparte lo que haya entre los profesores y los beneficios se quedan a 0.
			this.beneficios = 0;
	}
	
	/**
	 * M�todo que selecciona a los alumnos pendientes de cobrar.
	 * @return ArrayList Una lista de alumnos pendiente de cobro.
	 * @see <a href="https://www.w3schools.com/java/java_arraylist.asp">ArrayList de Java</a>
	 */
	public ArrayList<Alumnos> filtrarAlumnos() {
		//ArrayList que va a alojar los alumnos que no han pagado aun.
		ArrayList<Alumnos> sinPagar = new ArrayList<Alumnos>();
		
		//Se recorre la lista de alumnos de la autoescuela y si el alumno no ha pagado se a�ade al ArrayList.
		for (Alumnos a: lista_alumnos)
			if (!a.isPagado())
				sinPagar.add(a);
			
		return sinPagar;
	}
	
	/**
	 * M�todo sobrecargado que filtra los alumnos por el tipo de examen que tienen pendiente.
	 * @param tipo tipoExamen con opci�n enum por la que quieres seleccionar.
	 * @return listaAlumnos Un ArrayList de Alumnos solo con un tipo de examen.
	 * @see <a href="https://www.w3schools.com/java/java_arraylist.asp">ArrayList de Java</a>
	 */
	public ArrayList<Alumnos> filtrarAlumnos(tipoExamen tipo) {
		//ArrayList que va a almacenar los alumnos con el examen practico pendiente.
		ArrayList<Alumnos> pracPendiente = new ArrayList<Alumnos>();
		
		//Se recorre la lista de alumnos de la autoescuela y comprueba si el alumno tiene pendiente el examen
		// practico. Si es as�, se le a�ade al arraylist.
		for (Alumnos a: lista_alumnos)
			if (a.getExamen() == tipo)
				pracPendiente.add(a);
		
		return pracPendiente;
	}
	
	/**
	 * M�todo que recibe una lista de alumnos elegibles para ser aprobados y genera un n�mero aleatorio de aprobados.
	 * @param tipo tipoExamen filtro para crear una lista de alumnos elegibles.
	 * @return ArrayList de alumnos aprobados.
	 * @see <a href="https://www.w3schools.com/java/java_arraylist.asp">ArrayList de Java</a>
	 */
	public ArrayList<Alumnos> recibirAprobados(tipoExamen tipo) {
		//ArrayList para alojar los alumnos que son elegibles para esta operaci�n.
		ArrayList<Alumnos> alumnos = new ArrayList<Alumnos>();
		//ArrayList que contendr� los alumnos aprobados.
		ArrayList<Alumnos> alumnosAprobados = new ArrayList<Alumnos>();
		
		//Generador de n�meros.
		Random generator = new Random();
		
		//Recogemos los alumnos que son elegibles para aprobar.
		alumnos.addAll(filtrarAlumnos(tipo));
		
		//Seleccionamos una cantidad de alumnos que van a aprobar.
		int numAprobados = generator.nextInt(alumnos.size());
		
		//Tantas veces como la cantidad de alumnos que van a aprobar, se genera un numero aleatorio que no sobrepase la
		// cantidad de alumnos elegibles. Ese numero indica la posici�n del alumno en la lista, se coge ese alumno y se
		// pasa a la lista de aprobados definitivos.
		for (int i = 0; i<numAprobados; i++)
			alumnosAprobados.add(alumnos.get(generator.nextInt(alumnos.size())));
		
		return alumnosAprobados;
	}
	
	/**
	 * M�todo que muestra por pantalla la lista de alumnos de la autoescuela.
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
	 * M�todo que muestra por pantalla la lista de coches de la autoescuela.
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
	 * M�todo que muestra por pantalla la lista de profesores de la autoescuela.
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
	 * M�todo que muestra por pantalla la lista de alumnos en espera.
	 * @return String Contenido de la lista de alumnos en espera.
	 */
	public String mostrarAlumnosEspera() {
		String cadena = "\n";

		for (Alumnos a: this.lista_alum_espera) {
			cadena += a.toString() + "\n";
		}
		return cadena;
	}
	
	/**
	 * M�todo que muestra la info de un alumno, un profesor o un coche en particular.
	 * @param id Dni o matr�cula de coche.
	 * @return Informaci�n del elemento requerido o un mensaje de error si no hay coincidencias.
	 */
	public String mostrarInfoIndividual(String id) {
		
		for (Alumnos a: lista_alumnos)
			if (a.getDni().equalsIgnoreCase(id))
				return a.toString();
		for (Profesor p: lista_profesores)
			if (p.getDni().equalsIgnoreCase(id))
				return p.toString();
		for (Coches c: lista_vehiculos)
			if (c.getMatricula().equalsIgnoreCase(id))
				return c.toString();
		
		return "No existen datos asignados a esa id.";
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
