package modelo;

import java.io.Serializable;

import Excepciones.*;

public class Seleccion implements Serializable, Comparable<Seleccion> {

	private Seleccion next;
	private String pais;
	private String imagen;
	private int puntos;
	private double promAltura;
	private double promEdad;
	private double promFifa;
	private Jugador primero;
	private Jugador ultimo;
	private int numJugadores;

	public Seleccion(String pais, String imagen, int puntos) {

		this.pais = pais;
		this.imagen = imagen;
		this.puntos = puntos;
		primero = ultimo = null;
		numJugadores = 0;

	}

	public void linkNext(Seleccion next) {

		this.next = next;

	}

	public Seleccion getNext() {

		return next;

	}

	public Jugador getPrimero() {

		return primero;

	}

	public Jugador getUltimo() {

		return ultimo;

	}

	// Metodo para saber si la seleccion no tiene jugadores (No tiene = true, tiene
	// = false)
	public boolean isEmpty() {

		return numJugadores == 0;

	}

	// Metodo para saber cuantos jugadores tiene la seleccion
	public int size() {

		return numJugadores;

	}

	// Metodo para saber si hay un jugador que ya esta registrado en la seleccion
	public boolean yaExiste(String nombre) {

		boolean existe = false;

		int i = 0;

		Jugador jugador = primero;

		while (!(jugador.getNombre().equalsIgnoreCase(nombre)) && i != numJugadores) {

			jugador = jugador.getNext();
			i++;

		}

		if (jugador.getNombre().equalsIgnoreCase(nombre)) {

			existe = true;

		}

		return existe;

	}

	// Permite agregar a un jugador en la seleccion.
	public Jugador agregarJugador(String imagen, String nombre, String posicion, double puntaje, String fechaNac,
			double altura) throws JugadorYaExisteException {

		Jugador nuevo = new Jugador(imagen, nombre, posicion, puntaje, fechaNac, altura);

		if (isEmpty()) {

			primero = ultimo = nuevo;
			primero.linkNext(ultimo);
			primero.linkPrevious(ultimo);
			ultimo.linkNext(primero);
			ultimo.linkPrevious(primero);
			numJugadores++;

		} else {

			if (yaExiste(nombre)) {

				throw new JugadorYaExisteException(nombre);

			} else {

				ultimo.linkNext(nuevo);
				Jugador temp = ultimo;
				ultimo = nuevo;
				ultimo.linkNext(primero);
				primero.linkPrevious(ultimo);
				ultimo.linkPrevious(temp);
				numJugadores++;
			}
		}

		return nuevo;

	}

	// Permite buscar a un jugador por su nombre
	public Jugador buscarJugador(String nombre)
			throws JugadorNoRegistradoException, NoHayJugadoresRegistradosException {

		Jugador player = null;
		int i = 0;

		if (isEmpty()) {

			throw new NoHayJugadoresRegistradosException();

		} else {

			if (primero.getNombre().equalsIgnoreCase(nombre)) {

				player = primero;

			} else {

				Jugador actual = primero.getNext();

				while (!(actual.getNombre().equalsIgnoreCase(nombre)) && i != numJugadores) {

					actual = actual.getNext();
					i++;

				}

				if (actual.getNombre().equals(nombre)) {
					player = actual;
				} else {

					throw new JugadorNoRegistradoException(nombre);

				}
			}
		}

		return player;

	}

	// Permite eliminar a un jugador por su nombre de la seleccion
	public void eliminarJugador(String nombre) throws JugadorNoRegistradoException, NoHayJugadoresRegistradosException {

		if (isEmpty()) {

			throw new NoHayJugadoresRegistradosException();

		} else {

			if (yaExiste(nombre)) {
				if (primero.getNombre().equalsIgnoreCase(nombre)) {

					primero = primero.getNext();
					primero.linkPrevious(ultimo);
					ultimo.linkNext(primero);

				} else {

					Jugador actual = primero.getNext();
					Jugador anterior = actual.getPrevious();

					while (!(actual.getNombre().equalsIgnoreCase(nombre))) {

						actual = actual.getNext();
						anterior = actual.getPrevious();

					}

					anterior.linkNext(actual.getNext());
					actual.getNext().linkPrevious(anterior);

				}
				numJugadores--;
			} else {

				throw new JugadorNoRegistradoException(nombre);

			}
		}

	}

	// Permite modificar un jugador en el caso que el usuario vaya a registrar un
	// jugador ya registrado
	public void modificarJugador(Jugador player,String imagen, String nombre, String posicion, double puntaje, String fechaNac,
			double altura) throws JugadorNoRegistradoException {

		Jugador aModificar = player;
	
		if (aModificar == null) {

			throw new JugadorNoRegistradoException(nombre);

		} else {

			aModificar.setImagen(imagen);
			aModificar.setNombre(nombre);
			aModificar.setPosicion(posicion);
			aModificar.setPuntajeFifa(puntaje);
			aModificar.setFechaNac(fechaNac);
			aModificar.setAltura(altura);

		}

	}

	// Permite calcular el promedio de la altura de lso jugadores de la seleccion
	public double calcularPromedioAltura() {

		promAltura = 0;

		int i = 1;

		Jugador inicial = primero;

		while (i <= numJugadores) {

			promAltura += inicial.getAltura();
			inicial = inicial.getNext();
			i++;

		}

		promAltura = promAltura / numJugadores;

		return promAltura;

	}

	// Permite calcular el promedio de la edad de los jugadores de la seleccion
	public double calcularPromedioEdad() {

		promEdad = 0;

		int i = 1;

		Jugador inicial = primero;

		while (i <= numJugadores) {

			promEdad += inicial.getEdad();
			inicial = inicial.getNext();
			i++;

		}

		promEdad = promEdad / numJugadores;

		return promEdad;

	}

	// Permite calcular el promedio del puntaje fifa de la seleccion
	public double calcularPromedioFifa() {

		promFifa = 0;

		int i = 1;

		Jugador inicial = primero;

		while (i <= numJugadores) {

			promFifa += inicial.getPuntajeFifa();
			inicial = inicial.getNext();
			i++;

		}

		promFifa = promFifa / numJugadores;

		return promFifa;

	}

	// Setters & Getters
	/**
	 * 
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * @param imagen
	 *            the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos
	 *            the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * @param promAltura
	 *            the promAltura to set
	 */
	public void setPromAltura(double promAltura) {
		this.promAltura = promAltura;
	}

	/**
	 * @param promEdad
	 *            the promEdad to set
	 */
	public void setPromEdad(double promEdad) {
		this.promEdad = promEdad;
	}

	/**
	 * @param promFifa
	 *            the promFifa to set
	 */
	public void setPromFifa(double promFifa) {
		this.promFifa = promFifa;
	}

	// Compara en orden lexicografico la seleccion que invoca el metodo con la
	// seleccion que llega como parametro
	@Override
	public int compareTo(Seleccion sel) {

		return this.getPais().compareToIgnoreCase(sel.getPais());

	}

	@Override
	public String toString() {

		return pais;

	}

}
