package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Clase que almacena los alumnos que gestiona el recepcionista y contiene sus principales funciones.
 * @author Helena Mart�nez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class Recepcionista implements tipos_matricula_examen {
	
	/**
	 * M�todo que da de alta a una persona (puede ser alumno, trabajador o recepcionista).
	 * @param persona Persona que se va a dar de alta. Es un objeto de tipo Persona.
	 * @param Autoescuela Autoescuela donde se va a dar de alta.
	 */
	public static void alta(Persona p, Autoescuela a) {
		//Chequea si la persona a dar de alta es profesor o alumno y lo a�ade a los HashList de la autoescuela.
		if (p instanceof Alumnos)
			a.getLista_alumnos().add((Alumnos)p);
		else if (p instanceof Profesor)
			a.getLista_profesores().add((Profesor)p);
	}
	
	/**
	 * M�todo que da de alta un coche en la autoescuela.
	 * @param coche Coche que se va a dar de alta. Es un objeto de tipo Coche.
	 * @param Autoescuela Autoescuela donde se va a dar de alta.
	 */
	public static void alta(Coches c, Autoescuela a) {
		//Chequea si la persona a dar de alta es profesor o alumno y lo a�ade a los HashList de la autoescuela.
		a.getLista_vehiculos().add(c);
	}
	
	/**
	 * M�todo que cobra a todos los alumnos pendientes de cobro.
	 * @param Autoescuela La autoescuela que contiene los alumnos.
	 * @return true si ha cobrado satisfactoriamente, false si ha ocurrido alg�n problema.
	 */
	public static float cobros(Autoescuela auto) {
		ArrayList<Alumnos> sinPagar = new ArrayList<Alumnos>();
		
		float cobros = 0;
		
		sinPagar.addAll(auto.filtrarAlumnos());
		
		//Recorre la lista de alumnos matriculados que tiene la autoescuela con un foreach.
		for (Alumnos a: sinPagar) {
			//Creo una variable de tipo "tipoMatricula" que alojar� el tipo de matricula que tiene cada alumno.
			tipoMatricula tipoM = a.getMatricula_pagos();
			
			//Dependiendo del tipo de matricula se cobra un precio u otro.
			switch(tipoM) {
				case basico:
					cobros+=200;
				case intermedio:
					cobros+=400;
				case completo:
					cobros+=800;
				default:
					cobros+=200;
			}
		}
		
		actualizarAlumnosAutoescuelaPago(sinPagar, auto);
		
		//Le paso los cobros totales al m�todo que calcular� el beneficio final de la autoescuela
		return pagos_arreglos(cobros, auto);
	}
	
	/**
	 * M�todo que calcula los gastos totales y se los resta al dinero recaudado de los cobros.
	 * @param cobros Dinero cobrado previamente de los alumnos de la autoescuela.
	 * @param Autoescuela Autoescuela que se est� gestionando.
	 * @return beneficio de la autoescuela.
	 */
	public static float pagos_arreglos(float cobros, Autoescuela a) {
		float pagos = 0;
		
		//Recorro la lista de coches que tiene la autoescuela.
		for (Coches c: a.getLista_vehiculos()) {
			//Recorro la lista de arreglos que tiene cada coche.
			for (Arreglo arr: c.getLista_arreglos()) {
				//Por cada arreglo se va a coger el precio y se va a acumular en "pagos".
				pagos+= arr.getPrecio();
			}
			//Por cada coche se va a coger el precio de la gasolina repostada y se va a acumular en "pagos".
			pagos+=c.getPrecio_gasolina();
		}
		
		//Devuelve el resultado de restar los pagos a los cobros, es decir, el beneficio final.
		return cobros-pagos;
	}
	
	/**
	 * M�todo que asigna alumnos previamente en lista de espera a un profesor.
	 * @param Autoescuela Autoescuela donde est�n las listas de profesores.
	 * @return true si se ha podido asignar, false si no se ha podido asignar.
	 */
	public static boolean asignar_alumno_profesor(Autoescuela a) {
		//Guarda el tama�o de la lista de espera al inicio de la operacion.
		int tamanioIni = a.getLista_alum_espera().size();
		//Crea un iterador para recorrer la lista de espera.
		Iterator<Alumnos> it = a.getLista_alum_espera().iterator();
		
		//Se recorre la lista de profesores de la autoescuela
		for (Profesor p: a.getLista_profesores()) {
			//Chequea si el profesor tiene menos de 10 alumnos asignados. 10 ser�a el maximo de alumnos.
			if (p.getLista_alumnos_prac().size() < 10) {
				//Coge a los primeros en la lista de espera y los asigna a ese profesor hasta que no pueda coger m�s.
				while (it.hasNext() && (p.getLista_alumnos_prac().size() < 10)) {
					p.getLista_alumnos_prac().add(it.next());
					it.remove();
				}
			}
			//Si la lista de espera se ha quedado vac�a, se sale del bucle para que no siga comprobando el hueco del resto
			// de profesores.
			if (a.getLista_alum_espera().size() == 0)
				break;
		}
		
		//Si el tama�o de la lista de espera no ha cambiado significa que no se ha podido asignar ningun alumno y 
		// devuelve false. Si s�, devuelve true.
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
		
		//Recorremos el arraylist de aprobados y se da la orden de cambiar el tipo de examen al que le tocar�a examinarse
		// ahora, y se le a�ade a la lista de espera..
		for (Alumnos a: aprobados) {
			a.setExamen(tipoExamen.practico);
			//lista_alum_espera.add(a);
			auto.getLista_alum_espera().add(a);
		}
		//Actualizamos los datos en la lista general de la autoescuela
		actualizarAlumnosAutoescuelaExamen(aprobados, auto);
	}
	
	/**
	 * M�todo que actualiza el tipo de examen que tienen pendientes los alumnos en la lista general de la autoescuela.
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
	 * M�todo que actualiza el estado de pago de los alumnos en la lista general de la autoescuela.
	 * @param alumnosList Lista de alumnos que han sido cobrados.
	 * @param auto Autoescuela donde est�n los alumnos.
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
	 * M�todo que comprueba cu�ntos alumnos han aprobado y da de baja a aquellos que lo han hecho.
	 * @param alumnos_totales Lista total de los alumnos matriculados en la autoescuela.
	 * @return true si se han dado de baja correctamente, false si ha habido alg�n error.
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
	 * M�todo que da de baja a una persona en individual, pudiendo elegir si profesor o alumno.
	 * @param dni String con el dni de la persona a dar de baja
	 * @param autoescuela Autoescuela que estamos gestionando
 	 * @return true si se ha dado de baja correctamente, false si ocurri� algun problema.
	 */
	public static boolean dar_de_baja_individual(Persona p, Autoescuela auto) {
		if (p instanceof Alumnos) {
			return dar_de_baja_individual_alumno(p.getDni(), auto);
		} else {
			Profesor pr = (Profesor)p;
			return auto.getLista_profesores().remove(pr);
		}
			
	}
	
	/**
	 * Metodo que da de baja a un alumno individual.
	 * @param dni Dni del alumno a dar de baja
	 * @param auto Autoescuela del alumno
	 * @return True si se ha dado de baja correctamente, false si ocurrio algun problema.
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
			}
		}
		return retorno;
	}
	
	/**
	 * Metodo que elimina un alumno de su profesor asignado.
	 * @param auto Autoescuela del alumno
	 * @param alum Alumno a borrar
	 * @return True si se ha encontrado y borrado al alumno, False si no existe ese alumno en ningun profesor.
	 */
	public static boolean eliminaAlumnoDeProfe(Autoescuela auto, Alumnos alum) {
		//Iterador que recorre la lista general de alumnos de la autoescuela.
		Iterator<Alumnos> it = null;
		boolean hecho = false;
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
