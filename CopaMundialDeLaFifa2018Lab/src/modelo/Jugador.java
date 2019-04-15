package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Jugador implements Serializable {

	private String imagen;
	private String nombre;
	private String posicion;
	private double puntajeFifa;
	private String fechaNac;
	private double altura;
	private Jugador previous;
	private Jugador next;

	public Jugador(String imagen, String nombre, String posicion, double puntajeFifa, String fechaNac, double altura) {

		this.imagen = imagen;
		this.nombre = nombre;
		this.posicion = posicion;
		this.puntajeFifa = puntajeFifa;
		this.fechaNac = fechaNac;
		this.altura = altura;

	}
	
	public void linkPrevious(Jugador previous) {

		this.previous = previous;

	}

	public void linkNext(Jugador next) {

		this.next = next;

	}

	public Jugador getPrevious() {

		return previous;

	}

	public Jugador getNext() {

		return next;

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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the posicion
	 */
	public String getPosicion() {
		return posicion;
	}

	/**
	 * @param posicion
	 *            the posicion to set
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	/**
	 * @return the puntajeFifa
	 */
	public double getPuntajeFifa() {
		return puntajeFifa;
	}

	/**
	 * @param puntajeFifa
	 *            the puntajeFifa to set
	 */
	public void setPuntajeFifa(double puntajeFifa) {
		this.puntajeFifa = puntajeFifa;
	}

	/**
	 * @return the fechaNac
	 */
	public String getFechaNac() {
		return fechaNac;
	}

	/**
	 * @param fechaNac
	 *            the fechaNac to set
	 */
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * @return the altura
	 */
	public double getAltura() {
		return altura;
	}

	public int getEdad() {
		
	int edad = 0;	
	
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDate birth = LocalDate.parse(fechaNac, format);
	LocalDate today = LocalDate.now();
	Period periodo = Period.between(birth, today);
	edad = periodo.getYears();
		
	return 	edad;
		
	}
	
	/**
	 * @param altura
	 *            the altura to set
	 */

	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	@Override
	public String toString() {
		
	return nombre;	
		
	}

}
