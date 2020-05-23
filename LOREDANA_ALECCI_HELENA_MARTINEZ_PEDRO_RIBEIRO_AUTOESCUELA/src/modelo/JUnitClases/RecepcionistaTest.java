package modelo.JUnitClases;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;

public class RecepcionistaTest {
	
	static Autoescuela nochoques;
	static Autoescuela nulo;
	static HashSet<Alumnos> esperado;
	static Alumnos a1;
	static Alumnos a2;
	static Alumnos a3;
	static Coches coche1;
	static Coches coche2;
	static Coches coche3;
	static Profesor pr1;
	static Profesor pr2;
	static Profesor pr3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nochoques = new Autoescuela();
		nulo = null;
		esperado = new HashSet<Alumnos>();
		
		Iterator<Coches> it;
		
		Coches aux;
		
		a1 = new Alumnos("05279864T", 21, "Helena", 644321387, tipoMatricula.basico);
		
		coche1 = new Coches("JYX2345");
		coche2 = new Coches("HMD9876");
		coche3 = new Coches("EWR6574");
		
		pr1 = new Profesor("12345678T", 45, "Javier", 633987654, coche1);
		pr2 = new Profesor("09876543R", 34, "Rufina", 765412309, coche2);
		pr3 = new Profesor("56748932P", 28, "Paco", 678984209, coche3);
		
		Recepcionista.alta(coche1, nochoques);
		Recepcionista.alta(coche2, nochoques);
		Recepcionista.alta(coche3, nochoques);
		
		Recepcionista.alta(pr1, nochoques);
		Recepcionista.alta(pr2, nochoques);
		Recepcionista.alta(pr3, nochoques);
		
		Recepcionista.alta(a1, nochoques);
		
		Recepcionista.cobros(nochoques);
		
		a2 = new Alumnos("11223344V", 21, "Alvaro", 673874987, tipoMatricula.intermedio);
		a3 = new Alumnos("99887766J", 34, "Min Yoongi", 632876198, tipoMatricula.completo);
		
		Recepcionista.alta(a2, nochoques);
		Recepcionista.alta(a3, nochoques);
		
		pr1.getLista_alumnos_prac().add(a1);
		pr1.imparte_clase(nochoques, 5, "05279864T");
		pr1.imparte_clase(nochoques, 30, "05279864T");
		
		pr1.getCoche().repostar(nochoques);
	}

	@Test (expected = java.lang.NullPointerException.class)
	public void testAlumnosBase() {
		try {
			Recepcionista.alumnosBase(nulo);
		} catch (IOException ioe) {
			ioe.getStackTrace();
		}
	}

	/*@Test
	public void testAsignar_matricula() {
		PARAMETRIZADA
	}*/

	@Test
	public void testAltaPersonaAutoescuela() {
		Alumnos a = new Alumnos("12345678H", 30, "Aragorn", 999888777, tipoMatricula.basico);
		
		Recepcionista.alta(a, nochoques);
		
		assertTrue(nochoques.getLista_alumnos().contains(a));
		
		nochoques.getLista_alumnos().remove(a);
	}
	
	@Test
	public void testAltaPersonaAutoescuela2() {
		Profesor a = new Profesor("12345678H", 30, "Aragorn", 999888777, coche1);
		
		Recepcionista.alta(a, nochoques);
		
		assertTrue(nochoques.getLista_profesores().contains(a));
		
		nochoques.getLista_profesores().remove(a);
	}

	@Test
	public void testAltaCochesAutoescuela() {
		Coches c = new Coches("XDT9876");
		Recepcionista.alta(c, nochoques);
		assertTrue(nochoques.getLista_vehiculos().contains(c));
		
		nochoques.getLista_vehiculos().remove(c);
	}

	@Test
	public void testCobros() {
		assertEquals(1313.5, Recepcionista.cobros(nochoques), 0.10);
	}

	@Test
	public void testAsignar_alumno_profesor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGestionarAprobadosTeoricos() {
		fail("Not yet implemented");
	}

	@Test
	public void testActualizarAlumnosAutoescuelaExamen() {
		fail("Not yet implemented");
	}

	@Test
	public void testActualizarAlumnosAutoescuelaPago() {
		fail("Not yet implemented");
	}

	@Test
	public void testDar_de_baja_colectiva() {
		fail("Not yet implemented");
	}

	@Test
	public void testDar_de_baja_individual() {
		fail("Not yet implemented");
	}

	@Test
	public void testReubicarAlumnosEnPrac() {
		fail("Not yet implemented");
	}

	@Test
	public void testDar_de_baja_individual_alumno() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminaAlumnoDeProfe() {
		fail("Not yet implemented");
	}

}
