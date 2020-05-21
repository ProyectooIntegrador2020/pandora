package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ConcurrentModificationException;

import Vista.Interfaz_principal;
import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Autoescuela nochoques = new Autoescuela();
		
		Coches coche1 = new Coches("JYX2345");
		Coches coche2 = new Coches("HMD9876");
		Coches coche3 = new Coches("EWR6574");
		
		Profesor pr1 = new Profesor("12345678T", 45, "Javier", 633987654, coche1);
		Profesor pr2 = new Profesor("09876543R", 34, "Rufina", 765412309, coche2);
		Profesor pr3 = new Profesor("56748932P", 28, "Paco", 678984209, coche3);
		
		//Alumnos a1 = new Alumnos("aa", 22, "PP", 45, tipoMatricula.basico);
		
		Recepcionista.alta(coche1, nochoques);
		Recepcionista.alta(coche2, nochoques);
		Recepcionista.alta(coche3, nochoques);
		
		Recepcionista.alta(pr1, nochoques);
		Recepcionista.alta(pr2, nochoques);
		Recepcionista.alta(pr3, nochoques);
		
		try {
			Recepcionista.alumnosBase(nochoques);
			
			//Recepcionista.alta(a1, nochoques);
			
			System.out.format("%s\n", "INFO AUTOESCUELA");
			System.out.format("%s\n", nochoques.toString());
			
			nochoques.pago_personal(Recepcionista.cobros(nochoques));
			
			System.out.format("%s\n", "INFO AUTOESCUELA");
			System.out.format("%s\n", nochoques.toString());
			
			System.out.format("%s\n", "Aprueban");
			Recepcionista.gestionarAprobadosTeoricos(nochoques);
			
			System.out.format("%s\n", "INFO AUTOESCUELA");
			System.out.format("%s\n", nochoques.toString());
			
			//System.out.format("%s\n", nochoques.mostrarAlumnosEspera());
			
			System.out.format("%s\n", "Aprueban");
			Recepcionista.gestionarAprobadosTeoricos(nochoques);
			
			System.out.format("%s\n", "INFO AUTOESCUELA");
			System.out.format("%s\n", nochoques.toString());
			
			//System.out.format("%s\n", nochoques.mostrarAlumnosEspera());
			
			System.out.format("%s\n", "Asignamos");
			Recepcionista.asignar_alumno_profesor(nochoques);
			
			System.out.format("%s\n", "lista de espera:");
			System.out.format("%s\n", nochoques.mostrarAlumnosEspera());
			
			System.out.format("%s\n", "Profe 1");
			System.out.format("%s\n", pr1.mostrarAlumnos());
			
			System.out.format("%s\n", "Profe 2");
			System.out.format("%s\n", pr2.mostrarAlumnos());
			
			System.out.format("%s\n", "Profe 3");
			System.out.format("%s\n", pr3.mostrarAlumnos());
			
			System.out.format("%s\n", "Damos de baja a Alvaro");
			Recepcionista.dar_de_baja_individual("13608795J", nochoques);
			
			System.out.format("%s\n", "Profe 1");
			System.out.format("%s\n", pr1.mostrarAlumnos());
			
			System.out.format("%s\n", "Profe 2");
			System.out.format("%s\n", pr2.mostrarAlumnos());
			
			System.out.format("%s\n", "INFO AUTOESCUELA");
			System.out.format("%s\n", nochoques.toString());
			
			System.out.format("%s\n", "Damos de baja a Javier profe");
			Recepcionista.dar_de_baja_individual("12345678T", nochoques);
			
			System.out.format("%s\n", "INFO AUTOESCUELA");
			System.out.format("%s\n", nochoques.toString());
			
			System.out.format("%s\n", "Profe 1");
			System.out.format("%s\n", pr1.mostrarAlumnos());
			
			System.out.format("%s\n", "Profe 2");
			System.out.format("%s\n", pr2.mostrarAlumnos());
			
			System.out.format("%s\n", "Profe 3");
			System.out.format("%s\n", pr3.mostrarAlumnos());
			
			System.out.format("%s\n", "Damos de baja");
			Recepcionista.dar_de_baja_colectiva(nochoques);
			
			System.out.format("%s\n", "INFO AUTOESCUELA");
			System.out.format("%s\n", nochoques.toString());
			
		} catch (FileNotFoundException fnfe) {
			System.out.format("%s\n", "El archivo no fue encontrado.");
		} catch (NullPointerException npe) {
			System.out.format("%s\n", "Intentaste acceder a un nulo.");
		} catch (ConcurrentModificationException cme) {
			System.out.format("%s\n", "Has intentado modificar una colección que estaba siendo recorrida.");
		} catch (IOException ioe) {
			System.out.format("%s\n", "Hubo un problema manejando ficheros.");
		} catch (IllegalArgumentException iae) {
			System.out.format("%s\n", "Has intentado introducir un dato no válido.");
		} catch (Exception e) {
			System.out.format("%s\n", "Se produjo un error.");
			e.getStackTrace();
		}
		
		//Interfaz_principal.ventana_principal(nochoques);
		
	}

}
