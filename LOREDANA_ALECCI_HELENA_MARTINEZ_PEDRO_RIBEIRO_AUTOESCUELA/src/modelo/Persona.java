package modelo;

/**
 * Clase que almacena los datos en común de las personas involucradas en este proyecto.
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class Persona {
	
	private String dni;
	private int edad;
	private String nombre;
	private int num_tel;
	
	public Persona(String dni, int edad, String nombre, int num) {
		this.dni = dni;
		this.edad = edad;
		this.nombre = nombre;
		this.num_tel = num;
	}
	
	public Persona(Persona p) {
		this.dni = p.getDni();
		this.edad = p.getEdad();
		this.nombre = p.getNombre();
		this.num_tel = p.getNum_tel();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNum_tel() {
		return num_tel;
	}

	public void setNum_tel(int num_tel) {
		this.num_tel = num_tel;
	}

	@Override
	public String toString() {
		return "[dni=" + dni + ", edad=" + edad + ", nombre=" + nombre + ", num_tel=" + num_tel;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (this instanceof Persona) {
			Persona p = (Persona) o;
			if (this.dni.equalsIgnoreCase(p.getDni()))
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.dni.hashCode();
	}
}
