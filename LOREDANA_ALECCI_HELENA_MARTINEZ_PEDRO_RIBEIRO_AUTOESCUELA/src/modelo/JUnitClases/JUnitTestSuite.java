package modelo.JUnitClases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Clase que une todas las clases de pruebas de JUnit.
 * @author Helena Martinez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
@RunWith(Suite.class)
@SuiteClasses({ Asignar_matriculaTest.class, AutoescuelaTest.class, RecepcionistaTest.class, RestarGasolinaTest.class })
public class JUnitTestSuite {

}
