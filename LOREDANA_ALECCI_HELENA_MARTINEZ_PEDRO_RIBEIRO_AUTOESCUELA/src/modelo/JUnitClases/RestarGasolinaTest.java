package modelo.JUnitClases;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;

/**
 * Clase de pruebas parametrizadas para el método RestarGasolina.
 * @author Helena Martinez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
@RunWith (Parameterized.class)
public class RestarGasolinaTest {
	
	private static Coches c1;
	private static Profesor pr1;
	private static Autoescuela auto;
	private static int numClases;
	private static float result;
	
	public RestarGasolinaTest(int num, float res) throws SQLException {
		this.numClases = num;
		this.result = res;
		this.auto = new Autoescuela();
		this.c1 = new Coches("AAA2323");
		this.pr1 = new Profesor("12345678T", 45, "Javier", 633987654, c1);
		Recepcionista.alta(c1, auto);
		Recepcionista.alta(pr1, auto);
	}
	
	@Parameters
	public static Collection<Object[]> clases() {
		return Arrays.asList(new Object[][] {{2, 45}, {8, 30}, {11, 10}});
	}
	
	@Test
	public void testAsignar_matricula() throws SQLException {
		pr1.restarGasolina(numClases, auto);
		assertEquals(result, pr1.getCoche().getLitros_gasolina(), 0.10);
	}

}
