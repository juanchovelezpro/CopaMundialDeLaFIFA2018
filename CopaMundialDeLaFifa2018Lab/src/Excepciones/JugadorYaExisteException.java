package Excepciones;

public class JugadorYaExisteException extends Exception {

	public JugadorYaExisteException(String nombre) {
		
		super("El jugador "+nombre+" ya se encuentra registrado en el sistema.");
		
	}
	
}
