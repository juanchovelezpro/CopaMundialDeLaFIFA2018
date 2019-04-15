package Excepciones;

public class JugadorNoRegistradoException extends Exception {

	public JugadorNoRegistradoException(String nombre) {
		
	super("No se ha encontrado a un jugador con el nombre "+nombre+" en la seleccion actual");		
		
	}
	
	
}
