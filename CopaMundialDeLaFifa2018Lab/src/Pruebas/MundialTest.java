package Pruebas;

import modelo.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Excepciones.NoHaySeleccionesRegistradasException;
import Excepciones.PaisYaExisteException;
import Excepciones.SeleccionNoRegistradaException;
import junit.framework.TestCase;

class MundialTest extends TestCase{

	private Mundial mundial;
	
	private void escenarioUno() {
		
	mundial = new Mundial();		
		
	}
	
	private void escenarioDos() {
		
		mundial = new Mundial();	
		
		try {
			mundial.agregarSeleccion("Colombia", "", 15);
		} catch (PaisYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void testAgregarSeleccion() {
		
	escenarioUno();
	
	
	try {
		mundial.agregarSeleccion("Colombia", "", 15);
	} catch (PaisYaExisteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	assertEquals(mundial.getPrimero().getPais(), "Colombia");
	
		
		
	}
	
	
	@Test
	public void testEliminarSeleccion() {
		
		escenarioDos();
		
		try {
			mundial.eliminarSeleccion("Colombia");
		} catch (SeleccionNoRegistradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(0, mundial.getCantidad());
		
		
	}
	
	@Test 
	public void testBuscarSeleccion() {
		
		escenarioDos();
		Seleccion encontrada = null;
		
		try {
			encontrada = mundial.buscarSeleccion("Colombia");
		} catch (SeleccionNoRegistradaException | NoHaySeleccionesRegistradasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(mundial.getPrimero(), encontrada);
		
	}
	
	@Test
	public void testModificarSeleccion() {
		
	escenarioDos();
	
	Seleccion aModificar = mundial.getPrimero();
	try {
		mundial.modificarSeleccion(aModificar, "Argentina", "", 19);
	} catch (PaisYaExisteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	assertEquals("Argentina", mundial.getPrimero().getPais());
	
	
		
		
	}

}
