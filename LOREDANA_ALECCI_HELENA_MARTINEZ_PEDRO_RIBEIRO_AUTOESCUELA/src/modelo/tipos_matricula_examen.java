package modelo;

/**
 * Interfaz que implementa las opciones enums de matrícula y tipo de examen.
 * @author Helena Martinez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public interface tipos_matricula_examen {

	/**
	 * Enum que contiene tres opciones de matricula: Básico, Intermedio, Completo. 
	 * @author Helena Martinez
	 * @author Loredana Alecci
	 * @author Pedro Ribeiro
	 */
	public enum tipoMatricula {basico, intermedio, completo};
	
	/**
	 * Enum que contiene dos tipos de examen: teórico y práctico. 
	 * @author Helena Martinez
	 * @author Loredana Alecci
	 * @author Pedro Ribeiro
	 */
	public enum tipoExamen {teorico, practico};
}
