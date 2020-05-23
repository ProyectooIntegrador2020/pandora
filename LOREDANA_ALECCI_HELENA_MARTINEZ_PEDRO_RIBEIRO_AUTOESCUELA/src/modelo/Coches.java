package modelo;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Clase que contiene la informaci�n sobre cada coche que dispone la autoescuela y sus profesores. Tambi�n contiene la
 * funci�n de repostar.
 * @author Helena Mart�nez
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
			this.lista_arreglos = c.getLista_arreglos();
		else
			this.lista_arreglos = new HashSet<Arreglo>();
		this.litros_gasolina = c.getLitros_gasolina();
		this.precio_gasolina = c.getPrecio_gasolina();
		this.gasolina_max = c.getGasolina_max();
	}
	
	/**
	 * Constructor sin par�metros 
	 */
	public Coches() {
		this.matricula = this.getMatricula();
		if (this.getLista_arreglos().size() > 0)
			this.lista_arreglos = this.getLista_arreglos();
		else
			this.lista_arreglos = new HashSet<Arreglo>();
		this.litros_gasolina = this.getLitros_gasolina();
		this.precio_gasolina = this.getPrecio_gasolina();
		this.gasolina_max = this.getGasolina_max();
	}
	
	/**
	 * M�todo que le a�ade los litros de gasolina necesarios al coche cuando �ste se quede vac�o. Tambi�n almacena el
	 * precio de la gasolina usada.
	 * @param Autoescuela Autoescuela donde se va a actualizar el estado del vehiculo.
	 */
	public void repostar(Autoescuela auto) {
		//Solo se va a repostar los litros que faltan para llegar a los 60
		float aRepostar = gasolina_max - litros_gasolina;
		precio_gasolina += aRepostar*1.33;
		litros_gasolina = gasolina_max;
		actualizarVehiculoEnAutoescuela(auto);
	}
	
	/**
	 * M�todo que actualiza los datos del coche en la lista de vehiculos de la autoescuela.
	 * @param Autoescuela donde se va a actualizar.
	 */
	public void actualizarVehiculoEnAutoescuela(Autoescuela auto) {
		//Recorro la lista de vehiculos de la autoescuela
		Iterator<Coches> it = auto.getLista_vehiculos().iterator();
		//Buscamos que coche estamos modificando y lo eliminamos de la lista.
		while (it.hasNext()) {
			if (it.next().getMatricula().equalsIgnoreCase(this.matricula))
				it.remove();
		}
		//Metemos de nuevo el coche en la lista actualizada
		auto.getLista_vehiculos().add(this);
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
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (this instanceof Coches) {
			Coches c = (Coches) o;
			if (this.matricula.equalsIgnoreCase(c.getMatricula()))
				return true;
		}
		return false;
	}
	
	public String mostrarArreglos() {
		String cadena = "\n";

		for (Arreglo a: this.lista_arreglos) {
			cadena += a.toString() + "\n";
		}
		return cadena;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", litros_gasolina=" + litros_gasolina + ", precio_gasolina="
				+ precio_gasolina + mostrarArreglos();
	}
	
}
