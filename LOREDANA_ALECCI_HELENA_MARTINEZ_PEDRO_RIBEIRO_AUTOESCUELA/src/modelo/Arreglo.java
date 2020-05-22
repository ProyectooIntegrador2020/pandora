package modelo;

/**
 * Clase que contiene la información de cada arreglo que es necesario en un coche en concreto.
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 *
 */
public class Arreglo {
	
	private String nombre;
	private float precio;
	
	public Arreglo(String nombre, float precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Arreglo [nombre=" + nombre + ", precio=" + precio + "]";
	}
}
