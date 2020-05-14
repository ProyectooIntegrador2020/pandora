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
	private int clases_dadas;

	public Alumnos(String dni, int edad, String nombre, int num, tipoMatricula matricula) {
		super(dni, edad, nombre, num);
		this.matricula_pagos = matricula;
		this.num_intentos = 0;
		this.examen = tipoExamen.teorico;
		this.pagado = false;
		this.clases_dadas = 0;
	}
	
	public Alumnos(Alumnos a) {
		super(a);
		this.num_intentos = a.getNum_intentos();
		this.matricula_pagos = a.getMatricula_pagos();
		this.examen = a.getExamen();
		this.pagado = a.isPagado();
		this.clases_dadas = a.getClases_dadas();
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

	public int getClases_dadas() {
		return clases_dadas;
	}

	public void setClases_dadas(int clases_dadas) {
		this.clases_dadas = clases_dadas;
	}

	@Override
	public String toString() {
		return "Alumno " + super.toString() + ", num_intentos=" + num_intentos + ", matricula_pagos=" + matricula_pagos + ", examen=" + examen
				+ ", pagado=" + pagado + ", clases_dadas=" + clases_dadas + "]";
	}
	
}
