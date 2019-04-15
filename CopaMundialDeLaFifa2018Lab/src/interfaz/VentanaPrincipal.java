package interfaz;

import modelo.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import Excepciones.NoHaySeleccionesRegistradasException;
import Excepciones.PaisYaExisteException;
import Excepciones.SeleccionNoRegistradaException;

public class VentanaPrincipal extends JFrame {

	private Mundial mundial;
	private PanelSelecciones selecciones;
	private PanelJugadores jugadores;
	private PanelJugadorActual jugadorActual;

	public VentanaPrincipal() {

		setLayout(new GridLayout(1, 3, 3, 3));
		setTitle("Copa Mundial De La FIFA Rusia 2018");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(1280, 500));
		setResizable(false);
		setBackground(Color.DARK_GRAY);
		// setUndecorated(true);
		// setOpacity(0.70f);
		// setCursor(new Cursor(Cursor.WAIT_CURSOR));

		mundial = new Mundial();
		selecciones = new PanelSelecciones(this);
		jugadores = new PanelJugadores(this);
		jugadorActual = new PanelJugadorActual(this);

		add(selecciones);
		add(jugadores);
		add(jugadorActual);

		// Nimbus Style
		try {

			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		recuperarInfo();
		if(mundial.getPrimero()!=null) {
		actualizarConDatosGuardados();
		}
		setVisible(true);

	}

	public void setElActual(String elActual) {

		jugadorActual.setElActual(elActual);

	}

	public DefaultListModel<Seleccion> getListModelSeleccion() {

		return selecciones.getListSel();

	}

	public JList<Jugador> getListJug() {

		return jugadores.getListJug();

	}

	public JPanel getPanelSelecciones() {

		return selecciones;

	}

	public JPanel getPanelJugadores() {

		return jugadores;

	}

	public JPanel getPanelJugadorActual() {

		return jugadorActual;

	}

	public JTextField getTxtNombre() {

		return jugadorActual.getTxtNombre();

	}

	public JTextField getTxtPosicion() {

		return jugadorActual.getTxtPosicion();

	}

	public String getTxtImagen() {

		return jugadorActual.getRutaImagen();

	}

	public JTextField getTxtPuntajeFifa() {

		return jugadorActual.getTxtPuntajeFifa();

	}

	public JTextField getTxtAltura() {

		return jugadorActual.getTxtAltura();

	}

	public String getFechaNac() {

		return jugadorActual.getFechaNac();

	}

	public Icon getIconoJugadorNuevo() {

		return jugadorActual.getIconoJugadorSinImagen();

	}

	public JLabel getImage() {

		return jugadorActual.getImage();

	}

	public JComboBox<String> getDiasCombo() {

		return jugadorActual.getDiasCombo();

	}

	public JComboBox<String> getMesesCombo() {

		return jugadorActual.getMesesCombo();

	}

	public JComboBox<String> getAniosCombo() {

		return jugadorActual.getAniosCombo();

	}

	public JList<Seleccion> getListSel() {

		return selecciones.getListaSeleccion();

	}

	public Mundial getMundial() {

		return mundial;

	}

	public Seleccion agregarSeleccion(String pais, String imagen, int puntos) {
		Seleccion nuevo = null;
		try {
			nuevo = mundial.agregarSeleccion(pais, imagen, puntos);
		} catch (PaisYaExisteException e) {
			nuevo = null;
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return nuevo;
	}

	public void modificarSeleccion(Seleccion sel, String pais, String imagen, int puntos) {

		try {

			mundial.modificarSeleccion(sel, pais, imagen, puntos);

		} catch (PaisYaExisteException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void eliminarSeleccion(String pais) {

		try {
			mundial.eliminarSeleccion(pais);
		} catch (SeleccionNoRegistradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Seleccion buscarSeleccion(String pais) {

		Seleccion sel = null;

		try {
			sel = mundial.buscarSeleccion(pais);
		} catch (SeleccionNoRegistradaException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		} catch (NoHaySeleccionesRegistradasException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}

		return sel;

	}

	public void save() {

		
		FileOutputStream fS = null;
		ObjectOutputStream oS = null;
		Mundial toSave = mundial;
		

		try {

			fS = new FileOutputStream("./files/dataMundial.dat");
			oS = new ObjectOutputStream(fS);

			oS.writeObject(toSave);

		} catch (FileNotFoundException ex) {

			ex.printStackTrace();

		} catch (IOException ex) {

			ex.printStackTrace();

		} finally {

			try {

				if (toSave != null) {

					fS.close();

				}
				if (oS != null) {

					oS.close();

				}

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
	

	}

	public void recuperarInfo() {

		FileInputStream fS = null;
		ObjectInputStream oS = null;
		Mundial mun = null;

		try {

			fS = new FileInputStream("./files/dataMundial.dat");
			oS = new ObjectInputStream(fS);
			mun = (Mundial) oS.readObject();
			mundial = mun;
			
		} catch (Exception ex) {

			System.out.println(ex.getMessage());

		} finally {

			try {
				if (fS != null) {
					fS.close();
				}
				if (oS != null) {
					oS.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	public void actualizarConDatosGuardados() {

		Seleccion sel = mundial.getPrimero();

		int i = 0;
		while(i!=mundial.getCantidad()) {
			
		selecciones.getListSel().addElement(sel);
		sel = sel.getNext();
		i++;
			
		}

		
	}

	public static void main(String[] args) {

		VentanaPrincipal ventana = new VentanaPrincipal();

	}

}
