package modelo.JUnitClases;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.BBDD;
import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;

/**
 * Clase de pruebas de la clase Autoescuela.
 * @author Helena Martinez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class AutoescuelaTest {
	
	private static Autoescuela nochoques;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		BBDD.conectar();
		BBDD.deleteEverything();
		
		nochoques = new Autoescuela();
		Alumnos a1 = new Alumnos("11111111G", 21, "Helena", 644321387, tipoMatricula.completo);
		Alumnos a2 = new Alumnos("22222222T", 21, "Helena", 644321387, tipoMatricula.completo);
		Alumnos a3 = new Alumnos("33333333R", 21, "Helena", 644321387, tipoMatricula.completo);
		Alumnos a4 = new Alumnos("44444444F", 21, "Helena", 644321387, tipoMatricula.completo);
		Alumnos a5 = new Alumnos("55555555H", 21, "Helena", 644321387, tipoMatricula.completo);
		Alumnos a6 = new Alumnos("66666666Y", 21, "Helena", 644321387, tipoMatricula.completo);
		Alumnos a7 = new Alumnos("77777777P", 21, "Helena", 644321387, tipoMatricula.completo);
		Alumnos a8 = new Alumnos("88888888V", 21, "Helena", 644321387, tipoMatricula.completo);
		
		Coches coche1 = new Coches("JYX2345");
		Coches coche2 = new Coches("HMD9876");
		Coches coche3 = new Coches("EWR6574");
		
		Profesor pr1 = new Profesor("12345678T", 45, "Javier", 633987654, coche1);
		Profesor pr2 = new Profesor("09876543R", 34, "Rufina", 765412309, coche2);
		Profesor pr3 = new Profesor("56748932P", 28, "Paco", 678984209, coche3);
		
		Recepcionista.alta(a1, nochoques);
		Recepcionista.alta(a2, nochoques);
		Recepcionista.alta(a3, nochoques);
		Recepcionista.alta(a4, nochoques);
		Recepcionista.alta(a5, nochoques);
		Recepcionista.alta(a6, nochoques);
		Recepcionista.alta(a7, nochoques);
		Recepcionista.alta(a8, nochoques);
		
		Recepcionista.alta(coche1, nochoques);
		Recepcionista.alta(coche2, nochoques);
		Recepcionista.alta(coche3, nochoques);
		
		Recepcionista.alta(pr1, nochoques);
		Recepcionista.alta(pr2, nochoques);
		Recepcionista.alta(pr3, nochoques);
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BBDD.cerrar();
	}

	@Test
	public void testPago_personal() throws SQLException {
		
		nochoques.pago_personal(Recepcionista.cobros(nochoques));
		
		assertEquals(400, nochoques.getBeneficios(), 0.10);
	}

}
