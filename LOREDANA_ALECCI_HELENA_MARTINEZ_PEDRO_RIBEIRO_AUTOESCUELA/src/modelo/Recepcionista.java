package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import DAO.BBDD;
import modelo.tipos_matricula_examen.tipoMatricula;

/**
 * Clase que almacena los alumnos que gestiona el recepcionista y contiene sus principales funciones.
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class Recepcionista implements tipos_matricula_examen {
	
	/**
	 * Método que matricula unos alumnos por defecto al iniciar la aplicación cogiendo los datos de un fichero de
	 * texto.
	 * @param a Autoescuela donde se van a matricular.
	 * @throws FileNotFoundException Si no se encuentra el fichero a leer
	 * @throws IOException Si se produce algún otro error manejando el fichero.
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 */
	public static void alumnosBase(Autoescuela a) throws FileNotFoundException, IOException, NumberFormatException, SQLException {
		File alumnos = new File("alumnosIniciales.txt");
		FileReader fr = new FileReader(alumnos);
		BufferedReader bfr = new BufferedReader(fr);
		
		//Variable que va a almacenar cada línea del fichero.
		String leido = "";
		//Lista de palabras para almacenar uno por uno cada dato a usar.
		List<String> splits;
		
		//Mientras haya lienas que leer:
		while ((leido = bfr.readLine())!= null) {
			//Separamos la linea por palabras usando la coma "," como separador y las almacenamos en la lista.
			splits = Arrays.asList(leido.split(","));
			//Damos de alta el alumno creando cada alumno directamente en los parametros del metodo.
			alta(new Alumnos(splits.get(0), Integer.parseInt(splits.get(1)), splits.get(2), Integer.parseInt(splits.get(3)), asignar_matricula(Integer.parseInt(splits.get(4)))), a);
			//BBDD.insertarAlumns(splits.get(0), Integer.parseInt(splits.get(1)), splits.get(2), Integer.parseInt(splits.get(3)), Integer.parseInt(splits.get(4)));
		}
		
		bfr.close();
		fr.close();
	}
	
	/**
	 * Método que asigna un tipo de matrícula según un numero.
	 * @param numero int que indica que tipo de matricula asignamos.
	 * @return tipoMatricula del tipo enum "tipoMatricula".
	 */
	public static tipoMatricula asignar_matricula(int numero) {
		tipoMatricula tm = null;
		switch (numero) {
		case 1:
			tm=tipoMatricula.basico;
			break;
		case 2:
			tm=tipoMatricula.intermedio;
			break;
		case 3:
			tm=tipoMatricula.completo;
			break;
		default:
			break;
		}
		return tm;
	}
	
	/**
	 * Método que devuelve el numero correspondiente al tipo de matricula con el fin de usarlo para la BBDD.
	 * @param tipo opción enum de tipoMatricula.
	 * @return int que equivale a la opción del tipo de matricula.
	 */
	public static int enumAint(tipoMatricula tipo) {
		switch (tipo) {
			case basico:
				return 1;
			case intermedio:
				return 2;
			case completo:
				return 3;
			default:
				return 1;
		}
	}
	
	/**
	 * Método que da de alta a una persona (puede ser alumno, trabajador o recepcionista).
	 * @param p Persona que se va a dar de alta. Es un objeto de tipo Persona.
	 * @param a Autoescuela donde se va a dar de alta.
	 * @throws SQLException 
	 */
	public static void alta(Persona p, Autoescuela a) throws SQLException {
		//Chequea si la persona a dar de alta es profesor o alumno y lo añade a los HashList de la autoescuela.
		if (p instanceof Alumnos) {
			Alumnos al = (Alumnos)p;
			a.getLista_alumnos().add(al);
			//BBDD.insertarAlumns(al.getDni(), al.getEdad(), al.getNombre(), al.getNum_tel(), enumAint(al.getMatricula_pagos()));
		}
		else if (p instanceof Profesor) {
			Profesor prf = (Profesor)p;
			a.getLista_profesores().add(prf);
			//BBDD.insertarProfe(prf.getDni(), prf.getCoche().getMatricula(), prf.getEdad(), prf.getNombre(), prf.getNum_tel());
		}
	}
	
	/**
	 * Método que da de alta un coche en la autoescuela.
	 * @param c Coche que se va a dar de alta. Es un objeto de tipo Coche.
	 * @param a Autoescuela donde se va a dar de alta.
	 * @throws SQLException 
	 */
	public static void alta(Coches c, Autoescuela a) throws SQLException {
		//Chequea si la persona a dar de alta es profesor o alumno y lo añade a los HashList de la autoescuela.
		a.getLista_vehiculos().add(c);
		//BBDD.insertarCoche(c.getMatricula());
	}
	
	/**
	 * Método que cobra a todos los alumnos pendientes de cobro.
	 * @param auto La autoescuela que contiene los alumnos.
	 * @return beneficios float con los cobros después de haberle quitado los gastos de arreglos y gasolina
	 */
	public static float cobros(Autoescuela auto) {
		ArrayList<Alumnos> sinPagar = new ArrayList<Alumnos>();
		
		float cobros = 0;
		
		sinPagar.addAll(auto.filtrarAlumnos());
		
		//Recorre la lista de alumnos matriculados que tiene la autoescuela con un foreach.
		for (Alumnos a: sinPagar) {
			//Creo una variable de tipo "tipoMatricula" que alojará el tipo de matricula que tiene cada alumno.
			tipoMatricula tipoM = a.getMatricula_pagos();
			
			//Dependiendo del tipo de matricula se cobra un precio u otro.
			switch(tipoM) {
				case basico:
					cobros+=200;
					a.setClases_por_dar(10);
					break;
				case intermedio:
					cobros+=400;
					a.setClases_por_dar(15);
					break;
				case completo:
					cobros+=800;
					a.setClases_por_dar(25);
					break;
				default:
					cobros+=200;
					break;
			}
		}
		
		actualizarAlumnosAutoescuelaPago(sinPagar, auto);
		
		//Le paso los cobros totales al método que calculará el beneficio final de la autoescuela
		return pagos_arreglos(cobros, auto);
	}
	
	/**
	 * Método que calcula los gastos totales y se los resta al dinero recaudado de los cobros.
	 * @param cobros Dinero cobrado previamente de los alumnos de la autoescuela.
	 * @param a Autoescuela que se está gestionando.
	 * @return beneficios flaot con los beneficios de la autoescuela.
	 */
	public static float pagos_arreglos(float cobros, Autoescuela a) {
		float pagos = 0;
		Iterator<Arreglo> it;
		
		//Recorro la lista de coches que tiene la autoescuela.
		for (Coches c: a.getLista_vehiculos()) {
			it = c.getLista_arreglos().iterator();
			//Recorro la lista de arreglos que tiene cada coche.
			while (it.hasNext()) {
				//Por cada arreglo se va a coger el precio y se va a acumular en "pagos".
				pagos+= it.next().getPrecio();
				it.remove();
			}
			//Por cada coche se va a coger el precio de la gasolina repostada y se va a acumular en "pagos".
			pagos+=c.getPrecio_gasolina();
			c.setPrecio_gasolina(0);
			//Actualizamos el estado del coche dentro del profesor.
			for (Profesor p: a.getLista_profesores()) {
				if (p.getCoche().equals(c))
					p.setCoche(new Coches(c));
			}
		}
		
		//Devuelve el resultado de restar los pagos a los cobros, es decir, el beneficio final.
		return cobros-pagos;
	}
	
	/**
	 * Método que asigna alumnos previamente en lista de espera a un profesor.
	 * @param a Autoescuela donde están las listas de profesores.
	 * @return true si se ha podido asignar, false si no se ha podido asignar.
	 */
	public static boolean asignar_alumno_profesor(Autoescuela a) {
		//Guarda el tamaño de la lista de espera al inicio de la operacion.
		int tamanioIni = a.getLista_alum_espera().size();
		//Crea un iterador para recorrer la lista de espera.
		Iterator<Alumnos> it = a.getLista_alum_espera().iterator();
		
		//Se recorre la lista de profesores de la autoescuela
		for (Profesor p: a.getLista_profesores()) {
			//Chequea si el profesor tiene menos de 10 alumnos asignados. 10 sería el maximo de alumnos.
			if (p.getLista_alumnos_prac().size() < 10) {
				//Coge a los primeros en la lista de espera y los asigna a ese profesor hasta que no pueda coger más.
				while (it.hasNext() && (p.getLista_alumnos_prac().size() < 10)) {
					p.getLista_alumnos_prac().add(it.next());
					it.remove();
				}
			}
			//Si la lista de espera se ha quedado vacía, se sale del bucle para que no siga comprobando el hueco del resto
			// de profesores.
			if (a.getLista_alum_espera().size() == 0)
				break;
		}
		
		//Si el tamaño de la lista de espera no ha cambiado significa que no se ha podido asignar ningun alumno y 
		// devuelve false. Si sí, devuelve true.
		if (tamanioIni == a.getLista_alum_espera().size())
			return false;
		else
			return true;
	}
	
	/**
	 * Metodo que recibe los alumnos aprobados del teorico y les cambia el tipo de examen pendiente a practico.
	 * @param auto Autoescuela.
	 */
	public static void gestionarAprobadosTeoricos(Autoescuela auto) {
		//ArrayList que aloja la lista de alumnos aprobados.
		ArrayList<Alumnos> aprobados = new ArrayList<Alumnos>();

		//Copiamos el contenido del arraylist recibido al declarado arriba.
		aprobados.addAll(auto.recibirAprobados(tipoExamen.teorico));
		
		//Recorremos el arraylist de aprobados y se da la orden de cambiar el tipo de examen al que le tocaría examinarse
		// ahora, y se le añade a la lista de espera..
		for (Alumnos a: aprobados) {
			a.setExamen(tipoExamen.practico);
			//lista_alum_espera.add(a);
			auto.getLista_alum_espera().add(a);
		}
		//Actualizamos los datos en la lista general de la autoescuela
		actualizarAlumnosAutoescuelaExamen(aprobados, auto);
	}
	
	/**
	 * Método que actualiza el tipo de examen que tienen pendientes los alumnos en la lista general de la autoescuela.
	 * @param alumnosList Lista de alumnos aprobados.
	 * @param auto Autoescuela que se va a actualizar.
	 */
	public static void actualizarAlumnosAutoescuelaExamen(ArrayList<Alumnos> alumnosList, Autoescuela auto) {
		//Iterador que recorre la lista general de alumnos de la autoescuela.
		Iterator<Alumnos> it = auto.getLista_alumnos().iterator();
		
		//Variable que almacena el alumno temporalmente
		Alumnos alum = null;
		
		//Variable para comprobar si se hizo el cambio
		boolean hecho = false;
		
		//Mientras que queden alumnos por recorrer en la lista general:
		while (it.hasNext()) {
			//Almacenamos el alumno actual en alum
			alum = it.next();
			//Nos recorremos el arrayList de alumnos aprobados
			for (Alumnos a: alumnosList) {
				//Si el alumno aprobado es el mismo que el alumno de la lista general
				if (a.equals(alum)) {
					//Se actualiza el tipo de examen que tiene pendiente
					alum.setExamen(tipoExamen.practico);
					//Se registra que se ha hecho un cambio
					hecho = true;
				}
				//Si se hizo el cambio se sale del bucle que recorre la lista de aprobados
				if (hecho)
					break;
			}
			//Resetea el booleano para que el proceso siga funcionando.
			hecho = false;
		}
	}
	
	/**
	 * Método que actualiza el estado de pago de los alumnos en la lista general de la autoescuela.
	 * @param alumnosList Lista de alumnos que han sido cobrados.
	 * @param auto Autoescuela donde están los alumnos.
	 */
	public static void actualizarAlumnosAutoescuelaPago(ArrayList<Alumnos> alumnosList, Autoescuela auto) {
		//Iterador que recorre la lista general de alumnos de la autoescuela.
		Iterator<Alumnos> it = auto.getLista_alumnos().iterator();
		
		//Variable que almacena el alumno temporalmente
		Alumnos alum = null;
		
		//Variable para comprobar si se hizo el cambio
		boolean hecho = false;
		
		//Mientras que queden alumnos por recorrer en la lista general:
		while (it.hasNext()) {
			//Almacenamos el alumno actual en alum
			alum = it.next();
			//Nos recorremos el arrayList de alumnos aprobados
			for (Alumnos a: alumnosList) {
				//Si el alumno aprobado es el mismo que el alumno de la lista general
				if (a.equals(alum)) {
					//Se actualiza el tipo de examen que tiene pendiente
					alum.setPagado(true);
					alum.setClases_por_dar(a.getClases_por_dar());
					//Se registra que se ha hecho un cambio
					hecho = true;
				}
				//Si se hizo el cambio se sale del bucle que recorre la lista de aprobados
				if (hecho)
					break;
			}
			//Resetea el booleano para que el proceso siga funcionando.
			hecho = false;
		}
	}
	
	/**
	 * Método que comprueba cuántos alumnos han aprobado y da de baja a aquellos que lo han hecho.
	 * @param auto Autoescuela de donde se saca la info de los aprobados.
	 * @return true si se han dado de baja correctamente, false si ha habido algún error.
	 */
	public static boolean dar_de_baja_colectiva(Autoescuela auto) {
		//ArrayList que aloja la lista de alumnos aprobados.
		ArrayList<Alumnos> aprobados = new ArrayList<Alumnos>();
		
		//Copiamos el contenido del arraylist recibido al declarado arriba.
		aprobados.addAll(auto.recibirAprobados(tipoExamen.practico));
		
		//Recorremos el arraylist de aprobados y se da la orden de borrar uno por uno los alumnos de
		// la lista de alumnos de la autoescuela que coincidan con los alumnos de "aprobados".
		for (Alumnos a: aprobados) {
			auto.getLista_alumnos().remove(a);
			eliminaAlumnoDeProfe(auto, a);
		}
		
		return true;
	}
	
	/**
	 * Método que da de baja a una persona en individual, pudiendo elegir si profesor o alumno.
	 * @param dni String con el dni de la persona a dar de baja
	 * @param auto Autoescuela que estamos gestionando
 	 * @return true si se ha dado de baja correctamente, false si ocurrió algun problema.
	 */
	public static boolean dar_de_baja_individual(String dni, Autoescuela auto) {
		//Iterador para la lista de profesores por si necesitamos usarlo
		Iterator<Profesor> it = auto.getLista_profesores().iterator();
		
		Profesor aux = null;
		
		//Recorremos la lista de alumnos para ver si hay coincidencias con el dni pasado por parametro.
		for (Alumnos a: auto.getLista_alumnos()) {
			//Si coincide se procede a dar de baja al alumno.
			if (a.getDni().equalsIgnoreCase(dni))
				return dar_de_baja_individual_alumno(dni, auto);
		}
		
		//Si no hay coincidencias con un alumno, debe ser un profesor. Se recorre la lista de profesores con un iterator
		// Porque si no no se podria borrar un elemento de una lista que se está recorriendo.
		while (it.hasNext()) {
			aux = new Profesor(it.next());
			if (aux.getDni().equalsIgnoreCase(dni)) {
				it.remove();
				reubicarAlumnosEnPrac(aux, auto);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Método que reparte los alumnos de un profesor entre el resto de profesores.
	 * @param profe Profesor a ser borrado
	 * @param auto Autoescuela gestionada.
	 */
	public static void reubicarAlumnosEnPrac(Profesor profe, Autoescuela auto) {
		Iterator<Alumnos> it = profe.getLista_alumnos_prac().iterator();
		//Se recorre la lista de profesores de la autoescuela
		for (Profesor p: auto.getLista_profesores()) {
			//Chequea si el profesor tiene menos de 10 alumnos asignados. 10 sería el maximo de alumnos.
			if (p.getLista_alumnos_prac().size() < 10) {
				//Coge a los primeros en la lista de espera y los asigna a ese profesor hasta que no pueda coger más.
				while (it.hasNext() && (p.getLista_alumnos_prac().size() < 10)) {
					p.getLista_alumnos_prac().add(it.next());
					it.remove();
				}
			}
			//Si la lista de alumnos del profe se ha quedado vacía, se sale del bucle para que no siga comprobando el hueco del resto
			// de profesores.
			if (profe.getLista_alumnos_prac().size() == 0)
				break;
		}
		//Si ya no hay profes libres el resto de alumnos se les ponen en esperas.
		if (profe.getLista_alumnos_prac().size() > 0) {
			while (it.hasNext()) {
				auto.getLista_alum_espera().add(it.next());
				it.remove();
			}
		}
	}
	
	/**
	 * Metodo que da de baja a un alumno individual.
	 * @param dni Dni del alumno a dar de baja
	 * @param auto Autoescuela del alumno
	 * @return true si se ha dado de baja correctamente, false si ocurrio algun problema.
	 */
	public static boolean dar_de_baja_individual_alumno(String dni, Autoescuela auto) {
		//Iterador que recorre la lista general de alumnos de la autoescuela.
		Iterator<Alumnos> it = auto.getLista_alumnos().iterator();
		
		Alumnos alum = null;
		
		boolean retorno = false;
		
		while (it.hasNext()) {
			alum = it.next();
			if (alum.getDni().equalsIgnoreCase(dni)) {
				//Si el alumno tiene pendiente el examen practico se intenta eliminar de su profesor asignado
				if (alum.getExamen()==tipoExamen.practico)
					if (eliminaAlumnoDeProfe(auto, alum))
						retorno = true;
					else {
						//Si no tiene profesor asignado debe estar en la lista de espera.
						auto.getLista_alum_espera().remove(alum);
						retorno = true;
					}
				// Se le elimina de la lista general de la autoescuela.
				it.remove();
				retorno = true;
			}
		}
		return retorno;
	}
	
	/**
	 * Metodo que elimina un alumno de su profesor asignado.
	 * @param auto Autoescuela del alumno
	 * @param alum Alumno a borrar
	 * @return true si se ha encontrado y borrado al alumno, False si no existe ese alumno en ningun profesor.
	 */
	public static boolean eliminaAlumnoDeProfe(Autoescuela auto, Alumnos alum) {
		//Iterador que recorre la lista general de alumnos de la autoescuela.
		//Iterator<Alumnos> it = null;
		//boolean hecho = false;
		//Se recorre la lista de profesores de la autoescuela
		for (Profesor p: auto.getLista_profesores()) {
			//Si contiene al alumno lo borra.
			if (p.getLista_alumnos_prac().contains(alum)) {
				p.getLista_alumnos_prac().remove(alum);
				return true;
			}
		}
		return false;
	}
	
}
