package modelo;

import java.io.Serializable;

import Excepciones.NoHaySeleccionesRegistradasException;
import Excepciones.PaisYaExisteException;
import Excepciones.SeleccionNoRegistradaException;

public class Mundial implements Serializable {

	private Seleccion primero;
	private Seleccion ultimo;
	private int cantidad;

	public Mundial() {

		primero = ultimo = null;
		cantidad = 0;

	}

	public void setUltimo(Seleccion ultimo) {

		this.ultimo = ultimo;

	}

	public Seleccion getUltimo() {

		return ultimo;

	}

	public void setPrimero(Seleccion primero) {

		this.primero = primero;

	}

	public Seleccion getPrimero() {

		return primero;

	}

	public int getCantidad() {

		return cantidad;

	}

	public Seleccion agregarSeleccion(String pais, String imagen, int puntos) throws PaisYaExisteException {

		Seleccion nuevo = new Seleccion(pais, imagen, puntos);

		Seleccion actual = primero;

		if (yaExiste(pais)) {

			nuevo = null;
			throw new PaisYaExisteException(pais);

		} else {

			if (actual == null) {

				primero = nuevo;
				

			} else {

				while (actual.getNext() != null) {

					actual = actual.getNext();

				}

				actual.linkNext(nuevo);
			
			}
			cantidad++;
		}

		
		return nuevo;
	}

	public boolean yaExiste(String pais) {

		boolean existe = false;

		int i = 0;

		Seleccion seleccion = primero;

		if (seleccion != null) {

			while ((!(seleccion.getPais().equalsIgnoreCase(pais)) && i != cantidad) && seleccion.getNext() != null) {

				seleccion = seleccion.getNext();
				i++;

			}

			if (seleccion.getPais().equalsIgnoreCase(pais)) {

				existe = true;

			}
		}
		return existe;

	}

	public Seleccion buscarSeleccion(String pais)
			throws SeleccionNoRegistradaException, NoHaySeleccionesRegistradasException {

		Seleccion sel = null;
		int i = 0;

		if (primero == null) {

			throw new NoHaySeleccionesRegistradasException();

		} else {

			if (yaExiste(pais)) {

				if (primero.getPais().equalsIgnoreCase(pais)) {

					sel = primero;

				} else {

					Seleccion actual = primero.getNext();

					if (actual != null) {
						while ((!(actual.getPais().equalsIgnoreCase(pais)) && i != cantidad)
								&& actual.getNext() != null) {

							actual = actual.getNext();
							i++;

						}

						if (actual.getPais().equalsIgnoreCase(pais)) {
							sel = actual;
						}

					}
				}
			} else {

				throw new SeleccionNoRegistradaException(pais);

			}
		}
		return sel;

	}

	public void eliminarSeleccion(String pais) throws SeleccionNoRegistradaException {

		if (yaExiste(pais)) {
			if (primero.getPais().equalsIgnoreCase(pais)) {

				primero = primero.getNext();

			} else {

				Seleccion actual = primero.getNext();
				Seleccion anterior = primero;

				while (!(actual.getPais().equalsIgnoreCase(pais))) {

					actual = actual.getNext();
					anterior = anterior.getNext();

				}

				anterior.linkNext(actual.getNext());

			}
			cantidad--;
		} else {

			throw new SeleccionNoRegistradaException(pais);

		}

	}

	public void modificarSeleccion(Seleccion aModificar, String pais, String imagen, int puntos)
			throws PaisYaExisteException {

		if (yaExiste(pais) && !aModificar.getPais().equals(pais)) {

			throw new PaisYaExisteException(pais);

		} else {

			aModificar.setPais(pais);
			aModificar.setImagen(imagen);
			aModificar.setPuntos(puntos);
		}
	}
}
