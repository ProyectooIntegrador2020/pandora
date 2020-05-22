package modelo;

/**
 * Clase que almacena la información específica de cada alumno de la Autoescuela.
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class Alumnos extends Persona implements tipos_matricula_examen {
	
	private tipoMatricula matricula_pagos;
	private tipoExamen examen;
	private boolean pagado;
	private int clases_por_dar;

	public Alumnos(String dni, int edad, String nombre, int num, tipoMatricula matricula) {
		super(dni, edad, nombre, num);
		this.matricula_pagos = matricula;
		this.examen = tipoExamen.teorico;
		this.pagado = false;
		this.clases_por_dar = 0;
	}
	
	public Alumnos(Alumnos a) {
		super(a);
		this.matricula_pagos = a.getMatricula_pagos();
		this.examen = a.getExamen();
		this.pagado = a.isPagado();
		this.clases_por_dar = a.getClases_por_dar();
	}
	
	/**
	 * Método que resta clases pendientes al alumno.
	 * @param numClases a restar.
	 * @return true si se han restado clases, false si su numero pendiente de clases es 0.
	 */
	public boolean restarClases(int numClases) {
		//Si el alumno no le quedan clases por dar devuelve falso
		if (clases_por_dar == 0)
			return false;
		//Si el numero de clases a dar es mayor de las que le quedan al alumno, se hacen solo las que le quedan al alumno.
		if (numClases > clases_por_dar)
			numClases = clases_por_dar;
		//Se restan las clases
		clases_por_dar -= numClases;
		
		//Si se ha quedado sin clases se le resetea el estado de pago para que pague la matrícula de nuevo.
		if (clases_por_dar <= 0)
			pagado = false;
		
		//Devuelve true si se han restado clases.
		return true;
	}

	public tipoMatricula getMatricula_pagos() {
		return matricula_pagos;
	}

	public void setMatricula_pagos(tipoMatricula matricula_pagos) {
		this.matricula_pagos = matricula_pagos;
	}

	public tipoExamen getExamen() {
		return examen;
	}

	public void setExamen(tipoExamen examen) {
		this.examen = examen;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public int getClases_por_dar() {
		return clases_por_dar;
	}

	public void setClases_por_dar(int clases_dadas) {
		this.clases_por_dar = clases_dadas;
	}

	@Override
	public String toString() {
		return "Alumno " + super.toString() + ", matricula_pagos=" + matricula_pagos + ", examen=" + examen
				+ ", pagado=" + pagado + ", clases_por_dar=" + clases_por_dar + "]";
	}
	
}
