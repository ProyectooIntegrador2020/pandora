package modelo;

import java.util.HashSet;

/**
 * Clase que contiene la información sobre cada coche que dispone la autoescuela y sus profesores. También contiene la
 * función de repostar.
 * @author Helena Martínez
 * @author Loredana Alecci
 * @author Pedro Ribeiro
 */
public class Coches {
	
	private String matricula;
	private HashSet<Arreglo> lista_arreglos;
	private float litros_gasolina;
	private float precio_gasolina;
	private final int gasolina_max;
	
	public Coches(String matricula) {
		this.matricula = matricula;
		this.lista_arreglos = new HashSet<Arreglo>();
		this.litros_gasolina = 60f;
		this.precio_gasolina = 0f;
		this.gasolina_max = 60;
	}
	
	/**
	 * Constructor de copia de la clase Coches
	 * @param coche Objeto de tipo coche que queremos copiar.
	 */
	public Coches(Coches c) {
		this.matricula = c.getMatricula();
		if (c.getLista_arreglos().size() > 0)
			this.lista_arreglos.addAll(c.getLista_arreglos());
		else
			this.lista_arreglos = new HashSet<Arreglo>();
		this.litros_gasolina = c.getLitros_gasolina();
		this.precio_gasolina = c.getPrecio_gasolina();
		this.gasolina_max = c.getGasolina_max();
	}
	
	/**
	 * Método que le añade los litros de gasolina necesarios al coche cuando éste se quede vacío. También almacena el
	 * precio de la gasolina usada.
	 */
	public void repostar() {
		float aRepostar = gasolina_max - litros_gasolina;
		precio_gasolina += aRepostar*1.33;
		litros_gasolina = gasolina_max;
	}

	public int getGasolina_max() {
		return gasolina_max;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public HashSet<Arreglo> getLista_arreglos() {
		return lista_arreglos;
	}

	public void setLista_arreglos(HashSet<Arreglo> lista_arreglos) {
		this.lista_arreglos = lista_arreglos;
	}

	public float getLitros_gasolina() {
		return litros_gasolina;
	}

	public void setLitros_gasolina(float litros_gasolina) {
		this.litros_gasolina = litros_gasolina;
	}

	public float getPrecio_gasolina() {
		return precio_gasolina;
	}

	public void setPrecio_gasolina(float precio_gasolina) {
		this.precio_gasolina = precio_gasolina;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", litros_gasolina=" + litros_gasolina + ", precio_gasolina="
				+ precio_gasolina + "]";
	}
	
}
