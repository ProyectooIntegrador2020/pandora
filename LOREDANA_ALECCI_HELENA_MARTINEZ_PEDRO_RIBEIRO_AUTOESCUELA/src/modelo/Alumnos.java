package modelo;

/**
 * Clase que almacena la información específica de cada alumno de la Autoescuela.
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class Alumnos extends Persona implements tipos_matricula_examen {
	
	private int num_intentos;
	private tipoMatricula matricula_pagos;
	private tipoExamen examen;
	private boolean pagado;
	private int clases_por_dar;

	public Alumnos(String dni, int edad, String nombre, int num, tipoMatricula matricula) {
		super(dni, edad, nombre, num);
		this.matricula_pagos = matricula;
		this.num_intentos = 0;
		this.examen = tipoExamen.teorico;
		this.pagado = false;
		this.clases_por_dar = 0;
	}
	
	public Alumnos(Alumnos a) {
		super(a);
		this.num_intentos = a.getNum_intentos();
		this.matricula_pagos = a.getMatricula_pagos();
		this.examen = a.getExamen();
		this.pagado = a.isPagado();
		this.clases_por_dar = a.getClases_por_dar();
	}
	
	public void restarClases(int numClases) {
		clases_por_dar -= numClases;
		if (clases_por_dar <= 0)
			pagado = false;
	}

	public int getNum_intentos() {
		return num_intentos;
	}

	public void setNum_intentos(int num_intentos) {
		this.num_intentos = num_intentos;
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
		return "Alumno " + super.toString() + ", num_intentos=" + num_intentos + ", matricula_pagos=" + matricula_pagos + ", examen=" + examen
				+ ", pagado=" + pagado + ", clases_por_dar=" + clases_por_dar + "]";
	}
	
}
