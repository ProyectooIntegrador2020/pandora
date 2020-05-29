package modelo;

/**
 * Interfaz que implementa las opciones enums de matr�cula y tipo de examen.
 * @author Helena Martinez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public interface tipos_matricula_examen {

	/**
	 * Enum que contiene tres opciones de matricula: B�sico, Intermedio, Completo. 
	 * @author Helena Martinez
	 * @author Loredana Alecci
	 * @author Pedro Ribeiro
	 */
	public enum tipoMatricula {basico, intermedio, completo};
	
	/**
	 * Enum que contiene dos tipos de examen: te�rico y pr�ctico. 
	 * @author Helena Martinez
	 * @author Loredana Alecci
	 * @author Pedro Ribeiro
	 */
	public enum tipoExamen {teorico, practico};
}
