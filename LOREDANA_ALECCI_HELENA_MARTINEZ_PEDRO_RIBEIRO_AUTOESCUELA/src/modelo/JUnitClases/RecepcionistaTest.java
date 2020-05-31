package modelo.JUnitClases;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoExamen;
import modelo.tipos_matricula_examen.tipoMatricula;

/**
 * Clase de pruebas de la clase Recepcionista.
 * @author Helena Martinez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
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
		//Creo una autoescuela para realizar pruebas.
		nochoques = new Autoescuela();
		//Una autoescuela nula para generar excepciones.
		nulo = null;
		//Un hashset listo por si quiero usarlo de comparación con otro.
		esperado = new HashSet<Alumnos>();
		
		/*Iterator<Coches> it;
		
		Coches aux;*/
		
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
		
		//Gasto las clases al alumno 1 para que se resetee su estado de pago y poder calcular bien el resultado esperado
		// de los cobros, a la vez qye consigo probar que los gastos se restan.
		pr1.getLista_alumnos_prac().add(a1);
		pr1.imparte_clase(nochoques, 5, "05279864T");
		pr1.imparte_clase(nochoques, 30, "05279864T");
		
		//Creo un gasto para probar el pago_arreglos.
		pr1.getCoche().repostar(nochoques);
		
		
	}
	
	@Test
	public void testCobros() throws SQLException {
		assertEquals(1313.5, Recepcionista.cobros(nochoques), 0.10);
	}

	@Test (expected = java.lang.NullPointerException.class)
	public void testAlumnosBase() throws NumberFormatException, SQLException {
		try {
			Recepcionista.alumnosBase(nulo);
		} catch (IOException ioe) {
			ioe.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/*@Test
	public void testAsignar_matricula() {
		PARAMETRIZADA
	}*/

	@Test
	public void testAltaPersonaAutoescuela() throws SQLException {
		Alumnos a = new Alumnos("12345678H", 30, "Aragorn", 999888777, tipoMatricula.basico);
		
		Recepcionista.alta(a, nochoques);
		
		assertTrue(nochoques.getLista_alumnos().contains(a));
		
		nochoques.getLista_alumnos().remove(a);
	}
	
	@Test
	public void testAltaPersonaAutoescuela2() throws SQLException {
		Profesor a = new Profesor("12345678H", 30, "Aragorn", 999888777, coche1);
		
		Recepcionista.alta(a, nochoques);
		
		assertTrue(nochoques.getLista_profesores().contains(a));
		
		nochoques.getLista_profesores().remove(a);
	}

	@Test
	public void testAltaCochesAutoescuela() throws SQLException {
		Coches c = new Coches("XDT9876");
		Recepcionista.alta(c, nochoques);
		assertTrue(nochoques.getLista_vehiculos().contains(c));
		
		nochoques.getLista_vehiculos().remove(c);
	}

	@Test
	public void testAsignar_alumno_profesor() throws SQLException {
		//Cambio manualmente el estado del examen de los alumnos y los añado a la lista de espera.
		a2.setExamen(tipoExamen.practico);
		a3.setExamen(tipoExamen.practico);
		nochoques.getLista_alum_espera().add(a2);
		nochoques.getLista_alum_espera().add(a3);
		assertTrue(Recepcionista.asignar_alumno_profesor(nochoques));
	}

	@Test
	public void testDar_de_baja_individual() throws SQLException {
		//Creo un alumno temporal para luego darle de baja, para que no me altere los calculos de cobros.
		Alumnos aPrueba = new Alumnos("01010101T", 22, "Aloja", 666666666, tipoMatricula.basico);
		Recepcionista.alta(aPrueba, nochoques);
		//Baja de alumno
		assertTrue(Recepcionista.dar_de_baja_individual("01010101T", nochoques));
	}
	
	@Test
	public void testDar_de_baja_individual2() throws SQLException {
		//Baja de profesor.
		assertTrue(Recepcionista.dar_de_baja_individual("12345678T", nochoques));
		for (Profesor p: nochoques.getLista_profesores()) {
			if (p.equals(pr2)) {
				//Compruebo de paso el metodo reubicarAlumnosEnPrac
				assertEquals(2, p.getLista_alumnos_prac().size());
			}
		}
	}

	@Test
	public void testEliminaAlumnoDeProfe() {
		pr1.getLista_alumnos_prac().add(a2);
		
		Recepcionista.eliminaAlumnoDeProfe(nochoques, a2);
		
		assertFalse(pr1.getLista_alumnos_prac().contains(a2));
	}
	
	//NOTA 1.: Este metodo pasa la prueba *A VECES* porque depende de un número aleatorio de bajas. Si el número sale 0,
	// no se producen bajas, por lo tanto la comprobación da fallo.
	//NOTA 2.: Cuando este método pasa la prueba, el método testCobros fallará, ya que por alguna razón JUnit da de baja
	// a los alumnos antes de que se realice el cobro y por lo tanto sale un resultado distinto.
	//NOTA 3.: Se mantiene este método comentado por si se desea comprobar que efectivamente pasa la prueba.
	/*@Test
	public void testDar_de_baja_colectiva() {
		int ini = nochoques.getLista_alumnos().size();
		Iterator<Alumnos> it = nochoques.getLista_alumnos().iterator();
		while (it.hasNext()) {
			it.next().setExamen(tipoExamen.practico);
		}
		Recepcionista.dar_de_baja_colectiva(nochoques);
		if (ini > nochoques.getLista_alumnos().size())
			assertTrue(true);
		else
			fail("No hubo bajas.");
	}*/
	
	

}
