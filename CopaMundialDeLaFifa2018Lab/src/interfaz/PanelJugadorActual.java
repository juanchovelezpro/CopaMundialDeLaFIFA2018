package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Excepciones.NoHayJugadoresRegistradosException;
import modelo.Jugador;
import modelo.Seleccion;

public class PanelJugadorActual extends JPanel implements ActionListener {

	public static final String ANTERIOR = "anterior";
	public static final String PRIMERO = "primero";
	public static final String SIGUIENTE = "siguiente";
	public static final String DIAS = "dias";
	public static final String MESES = "meses";
	public static final String ANIOS = "anios";
	public static final String INSERTAR = "insertar";
	public static final String LIMPIAR = "limpiar";

	private JPanel panelAuxiliarBotones;
	private JPanel panelAuxiliarInfo;
	private JPanel panelAuxiliarFoto;
	private JPanel panelAuxiliarFecha;
	private JButton anterior;
	private JButton siguiente;
	private JButton primero;
	private JLabel labImagen;
	private JLabel labNombre;
	private JLabel labPosicion;
	private JLabel labPuntajeFifa;
	private JLabel labFechaNac;
	private JLabel labAltura;
	private JLabel image;
	private JButton butInsertar;
	private JButton butLimpiar;
	private JTextField txtNombre;
	private JTextField txtPosicion;
	private JTextField txtPuntajeFifa;
	private JTextField txtAltura;
	private JComboBox<String> diasCombo;
	private JComboBox<String> mesesCombo;
	private JComboBox<String> aniosCombo;
	private String dia;
	private String mes;
	private String anio;
	private String rutaImagen;
	private Icon sinImagen;
	private String elActual;
	private VentanaPrincipal ventana;

	public PanelJugadorActual(VentanaPrincipal window) {

		ventana = window;

		elActual = "Jugador Actual";
		setLayout(new BorderLayout());

		rutaImagen = null;
		sinImagen = new ImageIcon("./data/images/cutUsuario.jpg");

		Border bordePro = BorderFactory.createRaisedBevelBorder();
		TitledBorder border = BorderFactory.createTitledBorder(bordePro, elActual, TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Garamond", Font.BOLD, 20), Color.WHITE);
		setBorder(border);
		setBackground(Color.DARK_GRAY);

		dia = mes = anio = "";

		// Panel Auxiliar Foto
		panelAuxiliarFoto = new JPanel();
		panelAuxiliarFoto.setLayout(new GridLayout(1, 2));
		panelAuxiliarFoto.setBackground(Color.GRAY);
		JLabel vacio = new JLabel();
		image = new JLabel();
		ImageIcon usuario = new ImageIcon("./data/images/cutUsuario.jpg");
		image.setIcon(usuario);
		panelAuxiliarFoto.add(vacio);
		panelAuxiliarFoto.add(image);

		putFecha();

		// Panel Auxiliar Info
		panelAuxiliarInfo = new JPanel();
		panelAuxiliarInfo.setLayout(new GridLayout(6, 2, 2, 2));
		panelAuxiliarInfo.setBackground(Color.GRAY);
		labImagen = new JLabel("Imagen");
		labImagen.setForeground(Color.WHITE);
		labImagen.setFont(new Font("Garamond", 1, 18));
		labNombre = new JLabel("Nombre");
		labNombre.setForeground(Color.WHITE);
		labNombre.setFont(new Font("Garamond", 1, 18));
		labPosicion = new JLabel("Posición");
		labPosicion.setForeground(Color.WHITE);
		labPosicion.setFont(new Font("Garamond", 1, 18));
		labPuntajeFifa = new JLabel("Puntaje FIFA");
		labPuntajeFifa.setForeground(Color.WHITE);
		labPuntajeFifa.setFont(new Font("Garamond", 1, 18));
		labFechaNac = new JLabel("Fecha de Nacimiento");
		labFechaNac.setForeground(Color.WHITE);
		labFechaNac.setFont(new Font("Garamond", 1, 18));
		labAltura = new JLabel("Altura");
		labAltura.setForeground(Color.WHITE);
		labAltura.setFont(new Font("Garamond", 1, 18));
		butInsertar = new JButton("Insertar Imagen");
		butInsertar.setFont(new Font("Calibri", 1, 16));
		butInsertar.setBackground(Color.DARK_GRAY);
		butInsertar.setForeground(Color.WHITE);
		butInsertar.setActionCommand(INSERTAR);
		butInsertar.addActionListener(this);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Garamond", Font.BOLD, 16));
		txtPosicion = new JTextField();
		txtPosicion.setFont(new Font("Garamond", Font.BOLD, 16));
		txtPuntajeFifa = new JTextField();
		txtPuntajeFifa.setFont(new Font("Garamond", Font.BOLD, 16));
		txtAltura = new JTextField();
		txtAltura.setFont(new Font("Garamond", Font.BOLD, 16));
		panelAuxiliarInfo.add(labImagen);
		panelAuxiliarInfo.add(butInsertar);
		panelAuxiliarInfo.add(labNombre);
		panelAuxiliarInfo.add(txtNombre);
		panelAuxiliarInfo.add(labPosicion);
		panelAuxiliarInfo.add(txtPosicion);
		panelAuxiliarInfo.add(labPuntajeFifa);
		panelAuxiliarInfo.add(txtPuntajeFifa);
		panelAuxiliarInfo.add(labFechaNac);
		panelAuxiliarInfo.add(panelAuxiliarFecha);
		panelAuxiliarInfo.add(labAltura);
		panelAuxiliarInfo.add(txtAltura);

		// Panel Auxiliar Botones
		panelAuxiliarBotones = new JPanel();
		panelAuxiliarBotones.setLayout(new GridLayout(1, 4, 2, 2));
		panelAuxiliarBotones.setBackground(Color.DARK_GRAY);

		butLimpiar = new JButton("Limpiar");
		butLimpiar.setBackground(Color.DARK_GRAY);
		butLimpiar.setForeground(Color.WHITE);
		butLimpiar.setActionCommand(LIMPIAR);
		butLimpiar.addActionListener(this);
		anterior = new JButton("Anterior");
		anterior.setBackground(Color.DARK_GRAY);
		anterior.setForeground(Color.WHITE);
		anterior.setActionCommand(ANTERIOR);
		anterior.addActionListener(this);
		primero = new JButton("Primero");
		primero.setBackground(Color.DARK_GRAY);
		primero.setForeground(Color.WHITE);
		primero.setActionCommand(PRIMERO);
		primero.addActionListener(this);
		siguiente = new JButton("Siguiente");
		siguiente.setBackground(Color.DARK_GRAY);
		siguiente.setForeground(Color.WHITE);
		siguiente.setActionCommand(SIGUIENTE);
		siguiente.addActionListener(this);

		panelAuxiliarBotones.add(anterior);
		panelAuxiliarBotones.add(primero);
		panelAuxiliarBotones.add(siguiente);
		panelAuxiliarBotones.add(butLimpiar);

		add(panelAuxiliarFoto, BorderLayout.NORTH);
		add(panelAuxiliarInfo, BorderLayout.CENTER);
		add(panelAuxiliarBotones, BorderLayout.SOUTH);

	}

	public String getElActual() {

		return elActual;

	}

	public void setElActual(String elActual) {

		this.elActual = elActual;

	}

	public JComboBox<String> getDiasCombo() {

		return diasCombo;

	}

	public JComboBox<String> getMesesCombo() {

		return mesesCombo;

	}

	public JComboBox<String> getAniosCombo() {

		return aniosCombo;

	}

	public JLabel getImage() {

		return image;

	}

	public Icon getIconoJugadorSinImagen() {

		return sinImagen;

	}

	public void putFecha() {

		// Panel Auxiliar Fecha

		panelAuxiliarFecha = new JPanel();
		panelAuxiliarFecha.setLayout(new GridLayout(1, 3, 2, 2));

		String[] dias = new String[31];

		for (int i = 0, j = 1; i < dias.length; i++, j++) {

			if (j < 10) {
				dias[i] = "0" + j;
			} else {

				dias[i] = j + "";

			}
		}

		String[] meses = new String[12];

		meses[0] = "01";
		meses[1] = "02";
		meses[2] = "03";
		meses[3] = "04";
		meses[4] = "05";
		meses[5] = "06";
		meses[6] = "07";
		meses[7] = "08";
		meses[8] = "09";
		meses[9] = "10";
		meses[10] = "11";
		meses[11] = "12";

		String[] anios = new String[119];
		for (int i = 0, j = 2018; i < anios.length; i++, j--) {

			anios[i] = j + "";

		}

		diasCombo = new JComboBox<String>(dias);
		mesesCombo = new JComboBox<String>(meses);
		aniosCombo = new JComboBox<String>(anios);

		panelAuxiliarFecha.add(diasCombo);
		panelAuxiliarFecha.add(mesesCombo);
		panelAuxiliarFecha.add(aniosCombo);

		diasCombo.setActionCommand(DIAS);
		diasCombo.addActionListener(this);

		mesesCombo.setActionCommand(MESES);
		mesesCombo.addActionListener(this);

		aniosCombo.setActionCommand(ANIOS);
		aniosCombo.addActionListener(this);

	}

	public String getRutaImagen() {

		return rutaImagen;

	}

	public JTextField getTxtNombre() {

		return txtNombre;

	}

	public JTextField getTxtPosicion() {

		return txtPosicion;

	}

	public JTextField getTxtPuntajeFifa() {

		return txtPuntajeFifa;

	}

	public JTextField getTxtAltura() {

		return txtAltura;

	}

	public String getFechaNac() {

		return dia + "/" + mes + "/" + anio;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(INSERTAR)) {

			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			chooser.isVisible();
			Icon imagen = null;

			BufferedImage crop = null;

			try {
				rutaImagen = chooser.getSelectedFile().getAbsolutePath();
				BufferedImage original = ImageIO.read(new File(rutaImagen));
				if (original.getHeight() >= 200 || original.getWidth() >= 200) {
					crop = original.getSubimage(0, 0, 200, 200);

				} else {

					crop = ImageIO.read(new File(rutaImagen));

				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NullPointerException ex) {

				JOptionPane.showMessageDialog(null, "No se ha cargado ninguna imagen", "Info",
						JOptionPane.INFORMATION_MESSAGE);

			}
			imagen = new ImageIcon(crop);
			image.setIcon(imagen);

		}

		if (command.equals(DIAS)) {

			dia = (String) diasCombo.getSelectedItem();

		}

		if (command.equals(MESES)) {

			mes = (String) mesesCombo.getSelectedItem();

		}

		if (command.equals(ANIOS)) {

			anio = (String) aniosCombo.getSelectedItem();

		}

		if (command.equals(LIMPIAR)) {

			image.setIcon(sinImagen);
			txtAltura.setText("");
			txtNombre.setText("");
			txtPosicion.setText("");
			txtPuntajeFifa.setText("");
			diasCombo.setSelectedIndex(0);
			mesesCombo.setSelectedItem(0);
			aniosCombo.setSelectedIndex(0);
			ventana.getListJug().clearSelection();
			ventana.getListSel().clearSelection();

		}

		if (command.equals(ANTERIOR)) {

			Jugador player = ventana.getListJug().getSelectedValue();

			if (player == null) {

				JOptionPane.showMessageDialog(null, "Seleccion primero un jugador del panel jugadores",
						"Navegacion jugadores", JOptionPane.INFORMATION_MESSAGE);

			} else {

				Jugador anterior = player.getPrevious();

				String[] fecha = anterior.getFechaNac().split("/");

				image.setIcon(new ImageIcon(anterior.getImagen()));
				txtAltura.setText(String.valueOf(anterior.getAltura()));
				txtNombre.setText(anterior.getNombre());
				txtPosicion.setText(anterior.getPosicion());
				txtPuntajeFifa.setText(String.valueOf(anterior.getPuntajeFifa()));
				diasCombo.setSelectedItem(fecha[0]);
				mesesCombo.setSelectedItem(fecha[1]);
				aniosCombo.setSelectedItem(fecha[2]);

				ventana.getListJug().setSelectedValue(anterior, true);
			}

		}

		if (command.equals(PRIMERO)) {

			Seleccion sel = ventana.getListSel().getSelectedValue();

			if (sel == null) {

				JOptionPane.showMessageDialog(null, "Debe seleccionar primero una seleccion para ver su primer jugador",
						"Info", JOptionPane.INFORMATION_MESSAGE);

			} else {

				Jugador primero = sel.getPrimero();

				if (primero == null) {

					JOptionPane.showMessageDialog(null, "No hay jugadores registrados aun", "Info",
							JOptionPane.INFORMATION_MESSAGE);

				} else {

					String[] fecha = primero.getFechaNac().split("/");

					image.setIcon(new ImageIcon(primero.getImagen()));
					txtAltura.setText(String.valueOf(primero.getAltura()));
					txtNombre.setText(primero.getNombre());
					txtPosicion.setText(primero.getPosicion());
					txtPuntajeFifa.setText(String.valueOf(primero.getPuntajeFifa()));
					diasCombo.setSelectedItem(fecha[0]);
					mesesCombo.setSelectedItem(fecha[1]);
					aniosCombo.setSelectedItem(fecha[2]);

					ventana.getListJug().setSelectedValue(primero, true);

				}
			}
		}

		if (command.equals(SIGUIENTE)) {

			Jugador player = ventana.getListJug().getSelectedValue();

			if (player == null) {

				JOptionPane.showMessageDialog(null, "Seleccion primero un jugador del panel jugadores",
						"Navegacion jugadores", JOptionPane.INFORMATION_MESSAGE);

			} else {

				Jugador siguiente = player.getNext();

				String[] fecha = siguiente.getFechaNac().split("/");

				image.setIcon(new ImageIcon(siguiente.getImagen()));
				txtAltura.setText(String.valueOf(siguiente.getAltura()));
				txtNombre.setText(siguiente.getNombre());
				txtPosicion.setText(siguiente.getPosicion());
				txtPuntajeFifa.setText(String.valueOf(siguiente.getPuntajeFifa()));
				diasCombo.setSelectedItem(fecha[0]);
				mesesCombo.setSelectedItem(fecha[1]);
				aniosCombo.setSelectedItem(fecha[2]);

				ventana.getListJug().setSelectedValue(siguiente, true);

			}
		}

	}

}
