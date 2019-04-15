package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Excepciones.JugadorNoRegistradoException;
import Excepciones.JugadorYaExisteException;
import Excepciones.NoHayJugadoresRegistradosException;
import Excepciones.PaisYaExisteException;
import junit.framework.TestCase;
import modelo.Jugador;
import modelo.Mundial;
import modelo.Seleccion;

class SeleccionTest extends TestCase {

	private Mundial elMundial;
	private Seleccion seleccion;
	private Jugador jugador;

	private void escenarioUno() {

		Mundial mundial = new Mundial();

		try {
			seleccion = mundial.agregarSeleccion("Colombia", null, 15);
		} catch (PaisYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void escenarioDos() {

		Mundial mundial = new Mundial();
		try {
			seleccion = mundial.agregarSeleccion("Colombia", null, 15);

			seleccion.agregarJugador("", "Radamel Falcao", "Delantero", 85, "10/02/1986", 177);
		} catch (JugadorYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (PaisYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void escenarioTres() {
		
		elMundial = new Mundial();
		try {
			seleccion = elMundial.agregarSeleccion("Colombia", null, 15);

			seleccion.agregarJugador("", "Radamel Falcao", "Delantero", 85, "10/02/1986", 177);
			seleccion.agregarJugador("", "James Rodriguez", "Mediocampista", 90, "12/07/1991", 180);
		} catch (JugadorYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (PaisYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testAgregarJugador() {

		escenarioUno();

		try {
			seleccion.agregarJugador("", "Radamel Falcao", "Delantero", 85, "10/02/1986", 177);
		} catch (JugadorYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(seleccion.getPrimero().getNombre(), "Radamel Falcao");
		assertEquals(1, seleccion.size());
	}

	@Test
	public void testBuscarJugador() {

		escenarioDos();
		Jugador buscado = null;
		try {
			buscado = seleccion.buscarJugador("Radamel Falcao");
		} catch (JugadorNoRegistradoException | NoHayJugadoresRegistradosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jugador = seleccion.getPrimero();

		assertEquals(buscado, jugador);

	}

	@Test
	public void testEliminarJugador() {

		escenarioDos();

		try {
			seleccion.eliminarJugador("Radamel Falcao");

		} catch (JugadorNoRegistradoException | NoHayJugadoresRegistradosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(seleccion.size(), 0);

	}

	@Test
	public void testModificarJugador() {

		escenarioDos();

		Jugador jugador = seleccion.getPrimero();
		System.out.println(jugador);

		try {
			seleccion.modificarJugador(jugador, "", "Radamel Falcao Garcia", "Delantero", 85, "10/02/1986", 176);
			Jugador modificado = seleccion.getPrimero();
			System.out.println(modificado);
			assertTrue("Jugadores no iguales", jugador.getNombre().equalsIgnoreCase(modificado.getNombre()));
		} catch (JugadorNoRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testGetEdad() {
		
	escenarioDos();
	
	int years = seleccion.getPrimero().getEdad();	
	
	assertEquals(32, years);
			
		
	}
	
	@Test
	public void testPromedios() {
		
	escenarioTres();	
		
	double promedioEdad = elMundial.getPrimero().calcularPromedioEdad();
	double promedioAltura = elMundial.getPrimero().calcularPromedioAltura();
	double promedioPuntaje = elMundial.getPrimero().calcularPromedioFifa();
	
	assertEquals(29.0, promedioEdad);
	assertEquals(178.5, promedioAltura);
	assertEquals(87.5, promedioPuntaje);
	
	
		
		
	}
	
	
	

}
