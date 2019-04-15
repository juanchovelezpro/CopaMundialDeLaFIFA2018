package Excepciones;

public class SeleccionNoRegistradaException extends Exception {

	public SeleccionNoRegistradaException(String seleccion) {
		
		
		super("La seleccion "+seleccion+" no se encuentra registrada");
		
	}
	
}
