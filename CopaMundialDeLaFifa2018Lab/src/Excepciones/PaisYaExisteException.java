package Excepciones;

public class PaisYaExisteException extends Exception{

	public PaisYaExisteException(String pais) {
		
	super(pais+" ya existe entre los paises registrados.");	
		
	}
	
}
