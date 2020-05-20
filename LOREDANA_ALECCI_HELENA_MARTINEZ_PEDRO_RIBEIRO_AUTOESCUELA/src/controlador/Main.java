package controlador;

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
		
		Alumnos a1 = new Alumnos("02597684H", 21, "Helena", 633150683, tipoMatricula.basico);
		Alumnos a2 = new Alumnos("11223344T", 22, "Alvaro", 633150683, tipoMatricula.intermedio);
		Alumnos a3 = new Alumnos("22334455F", 23, "Loredana", 633150683, tipoMatricula.completo);
		Alumnos a4 = new Alumnos("33445566D", 24, "Pedro", 633150683, tipoMatricula.basico);
		Alumnos a5 = new Alumnos("44556677L", 25, "Michael", 633150683, tipoMatricula.intermedio);
		Alumnos a6 = new Alumnos("55667788G", 26, "Jungkook", 633150683, tipoMatricula.completo);
		Alumnos a7 = new Alumnos("66778899K", 27, "Namjoon", 633150683, tipoMatricula.basico);
		Alumnos a8 = new Alumnos("77889900C", 28, "Yoongi", 633150683, tipoMatricula.intermedio);
		Alumnos a9 = new Alumnos("88990011J", 29, "Hoseok", 633150683, tipoMatricula.completo);
		Alumnos a10 = new Alumnos("99001122L", 30, "Jin", 633150683, tipoMatricula.basico);
		Alumnos a11 = new Alumnos("00112233A", 31, "Taehyung", 633150683, tipoMatricula.intermedio);
		Alumnos a12 = new Alumnos("12123434F", 32, "Jimin", 633150683, tipoMatricula.completo);
		Alumnos a13 = new Alumnos("91823764E", 33, "Sofia", 633150683, tipoMatricula.basico);
		
		float beneficios = 0;
		
		Recepcionista.alta(coche1, nochoques);
		Recepcionista.alta(coche2, nochoques);
		Recepcionista.alta(coche3, nochoques);
		
		Recepcionista.alta(pr1, nochoques);
		Recepcionista.alta(pr2, nochoques);
		Recepcionista.alta(pr3, nochoques);
		
		Recepcionista.alta(a1, nochoques);
		Recepcionista.alta(a2, nochoques);
		Recepcionista.alta(a3, nochoques);
		Recepcionista.alta(a4, nochoques);
		Recepcionista.alta(a5, nochoques);
		Recepcionista.alta(a6, nochoques);
		Recepcionista.alta(a7, nochoques);
		Recepcionista.alta(a8, nochoques);
		Recepcionista.alta(a9, nochoques);
		Recepcionista.alta(a10, nochoques);
		Recepcionista.alta(a11, nochoques);
		Recepcionista.alta(a12, nochoques);
		Recepcionista.alta(a13, nochoques);
		
		System.out.format("%s\n", nochoques.toString());
		
		beneficios = Recepcionista.cobros(nochoques);
		
		System.out.format("%s%f\n", "Los beneficios son: ", beneficios);
		
		System.out.format("%s\n", nochoques.toString());
		
		System.out.format("%s\n", "Aprueban");
		Recepcionista.gestionarAprobadosTeoricos(nochoques);
		
		System.out.format("%s\n", nochoques.toString());
		
		System.out.format("%s\n", nochoques.mostrarAlumnosEspera());
		
		System.out.format("%s\n", "Aprueban");
		Recepcionista.gestionarAprobadosTeoricos(nochoques);
		
		System.out.format("%s\n", nochoques.toString());
		
		System.out.format("%s\n", nochoques.mostrarAlumnosEspera());
		
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
		Recepcionista.dar_de_baja_individual(a2, nochoques);
		
		System.out.format("%s\n", "Profe 1");
		System.out.format("%s\n", pr1.mostrarAlumnos());
		
		System.out.format("%s\n", nochoques.toString());
		
		System.out.format("%s\n", "Damos de baja a Javier profe");
		Recepcionista.dar_de_baja_individual(pr1, nochoques);
		
		System.out.format("%s\n", nochoques.toString());
		
		System.out.format("%s\n", "Damos de baja");
		Recepcionista.dar_de_baja_colectiva(nochoques);
		
		System.out.format("%s\n", "Profe 1");
		System.out.format("%s\n", pr1.mostrarAlumnos());
		
		System.out.format("%s\n", nochoques.toString());
		
		Interfaz_principal.ventana_principal(nochoques);
		
	}

}
