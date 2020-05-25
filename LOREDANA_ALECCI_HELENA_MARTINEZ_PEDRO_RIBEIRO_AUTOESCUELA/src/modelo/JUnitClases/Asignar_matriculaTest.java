package modelo.JUnitClases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;

/**
 * Clase de pruebas parametrizadas para el método asignar_matriculaTest.
 * @author Helena Martinez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
@RunWith (Parameterized.class)
public class Asignar_matriculaTest {
	
	private int num;
	private String tipoM;
	
	public Asignar_matriculaTest(int num, String tipoM) {
		this.num = num;
		this.tipoM = tipoM;
	}
	
	@Parameters
	public static Collection<Object[]> numeros() {
		return Arrays.asList(new Object[][] {{1, "basico"}, {2, "intermedio"}, {3, "completo"}});
	}
	
	@Test
	public void testAsignar_matricula() {
		assertEquals(tipoM, Recepcionista.asignar_matricula(num).name());
	}

}
