package controlador;

import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Autoescuela Nochoques = new Autoescuela();
		
		Recepcionista r1 = new Recepcionista("54768392G", 33, "Pepa", 678123456);
		
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
		
		r1.alta(coche1, Nochoques);
		r1.alta(coche2, Nochoques);
		r1.alta(coche3, Nochoques);
		
		r1.alta(pr1, Nochoques);
		r1.alta(pr2, Nochoques);
		r1.alta(pr3, Nochoques);
		
		r1.alta(a1, Nochoques);
		r1.alta(a2, Nochoques);
		r1.alta(a3, Nochoques);
		r1.alta(a4, Nochoques);
		r1.alta(a5, Nochoques);
		r1.alta(a6, Nochoques);
		r1.alta(a7, Nochoques);
		r1.alta(a8, Nochoques);
		r1.alta(a9, Nochoques);
		r1.alta(a10, Nochoques);
		r1.alta(a11, Nochoques);
		r1.alta(a12, Nochoques);
		r1.alta(a13, Nochoques);
		
		System.out.format("%s\n", Nochoques.toString());
		
		beneficios = r1.cobros(Nochoques);
		
		System.out.format("%s%f\n", "Los beneficios son: ", beneficios);
		
		System.out.format("%s\n", Nochoques.toString());
		
		System.out.format("%s\n", "Aprueban");
		r1.gestionarAprobadosTeoricos(Nochoques);
		
		System.out.format("%s\n", Nochoques.toString());
		
		System.out.format("%s\n", r1.mostrarAlumnos());
		
		System.out.format("%s\n", "Aprueban");
		r1.gestionarAprobadosTeoricos(Nochoques);
		
		System.out.format("%s\n", Nochoques.toString());
		
		System.out.format("%s\n", r1.mostrarAlumnos());
		
		System.out.format("%s\n", "Asignamos");
		r1.asignar_alumno_profesor(Nochoques);
		
		System.out.format("%s\n", "lista de espera:");
		System.out.format("%s\n", r1.mostrarAlumnos());
		
		System.out.format("%s\n", "Profe 1");
		System.out.format("%s\n", pr1.mostrarAlumnos());
		
		System.out.format("%s\n", "Profe 2");
		System.out.format("%s\n", pr2.mostrarAlumnos());
		
		System.out.format("%s\n", "Profe 3");
		System.out.format("%s\n", pr3.mostrarAlumnos());
		
		System.out.format("%s\n", "Damos de baja a Alvaro");
		r1.dar_de_baja_individual(a2, Nochoques);
		
		System.out.format("%s\n", "Profe 1");
		System.out.format("%s\n", pr1.mostrarAlumnos());
		
		System.out.format("%s\n", Nochoques.toString());
		
		System.out.format("%s\n", "Damos de baja a Javier profe");
		r1.dar_de_baja_individual(pr1, Nochoques);
		
		System.out.format("%s\n", Nochoques.toString());
		
		System.out.format("%s\n", "Damos de baja");
		r1.dar_de_baja_colectiva(Nochoques);
		
		System.out.format("%s\n", "Profe 1");
		System.out.format("%s\n", pr1.mostrarAlumnos());
		
		System.out.format("%s\n", Nochoques.toString());
		
	}

}
