package interfaz;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import Excepciones.JugadorNoRegistradoException;
import Excepciones.JugadorYaExisteException;
import Excepciones.NoHayJugadoresRegistradosException;
import modelo.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelJugadores extends JPanel implements ActionListener {

	public final static String AGREGAR = "agregar";
	public final static String ELIMINAR = "eliminar";
	public final static String BUSCAR = "buscar";
	public final static String GUARDAR = "guardar";

	private JPanel panelAuxiliarBotones;
	private JButton agregar;
	private JButton eliminar;
	private JButton buscar;
	private JButton guardar;
	private JList<Jugador> listaJug;
	private DefaultListModel<Jugador> listModel;
	private VentanaPrincipal ventana;

	public PanelJugadores(VentanaPrincipal window) {

		setLayout(new BorderLayout());

		ventana = window;

		Border bordePro = BorderFactory.createRaisedBevelBorder();
		TitledBorder border = BorderFactory.createTitledBorder(bordePro, "Jugadores", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, new Font("Garamond", Font.BOLD, 20), Color.WHITE);
		setBorder(border);
		setBackground(Color.DARK_GRAY);

		listModel = new DefaultListModel<Jugador>();
		listaJug = new JList<Jugador>(listModel);
		listaJug.setFixedCellWidth(400);
		listaJug.setFixedCellHeight(40);
		listaJug.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaJug.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaJug.setBackground(Color.GRAY);
		listaJug.setFont(new Font("Garamond", 1, 24));
		listaJug.setForeground(Color.WHITE);

		// Listener para mostrar los jugadores de cada seleccion
		ventana.getListSel().addListSelectionListener(e -> {

			
			listModel.clear();
			
			ventana.getTxtNombre().setText("");
			ventana.getTxtPuntajeFifa().setText("");
			ventana.getTxtPosicion().setText("");
			ventana.getTxtAltura().setText("");
			ventana.getImage().setIcon(ventana.getIconoJugadorNuevo());
			ventana.getDiasCombo().setSelectedIndex(0);
			ventana.getMesesCombo().setSelectedIndex(0);
			ventana.getAniosCombo().setSelectedIndex(0);

			Seleccion sel = ventana.getListSel().getSelectedValue();

			if (sel != null) {
				int i = 0;

				Jugador elJuga = sel.getPrimero();
//				System.out.println(elJuga);

				if (elJuga != null) {
					while (i != sel.size()) {

						listModel.addElement(elJuga);

						elJuga = elJuga.getNext();
						i++;

					}
				}
			}

		});

		// Listener para actualizar el panel del jugador seleccionado
		listaJug.getSelectionModel().addListSelectionListener(e -> {

			if (listaJug.getSelectedValue() != null) {
				Jugador player = listaJug.getSelectedValue();

				ventana.setElActual(player.getNombre());

				String[] fecha = player.getFechaNac().split("/");

				BufferedImage crop = null;
				try {
					if (player.getImagen() == null || player.getImagen().equals("")) {
						crop = ImageIO.read(new File("./data/images/cutUsuario.jpg"));
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {

					BufferedImage original = ImageIO.read(new File(player.getImagen()));
					if (original.getHeight() >= 200 || original.getWidth() >= 200) {
						crop = original.getSubimage(0, 0, 200, 200);
					} else {

						crop = ImageIO.read(new File(player.getImagen()));

					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NullPointerException ex) {

					

				}
				ventana.getImage().setIcon(new ImageIcon(crop));
				ventana.getTxtNombre().setText(player.getNombre());
				ventana.getTxtPosicion().setText(player.getPosicion());
				ventana.getTxtPuntajeFifa().setText(String.valueOf(player.getPuntajeFifa()));
				ventana.getTxtAltura().setText(String.valueOf(player.getAltura()));
				ventana.getDiasCombo().setSelectedItem(fecha[0]);
				ventana.getMesesCombo().setSelectedItem(fecha[1]);
				ventana.getAniosCombo().setSelectedItem(fecha[2]);
			}
		});

		JScrollPane myScrollList = new JScrollPane(listaJug);
		myScrollList.setPreferredSize(new Dimension(400, 800));

		botones();

		panelAuxiliarBotones = new JPanel();
		panelAuxiliarBotones.setBackground(Color.DARK_GRAY);
		panelAuxiliarBotones.setLayout(new GridLayout(1, 4, 2, 2));
		panelAuxiliarBotones.add(agregar);
		panelAuxiliarBotones.add(eliminar);
		panelAuxiliarBotones.add(buscar);
		panelAuxiliarBotones.add(guardar);

		add(myScrollList, BorderLayout.CENTER);
		add(panelAuxiliarBotones, BorderLayout.SOUTH);

	}

	public void botones() {

		agregar = new JButton("Agregar");
		agregar.setBackground(Color.DARK_GRAY);
		agregar.setForeground(Color.WHITE);
		agregar.setActionCommand(AGREGAR);
		agregar.addActionListener(this);

		eliminar = new JButton("Eliminar");
		eliminar.setBackground(Color.DARK_GRAY);
		eliminar.setForeground(Color.WHITE);
		eliminar.setActionCommand(ELIMINAR);
		eliminar.addActionListener(this);

		buscar = new JButton("Buscar");
		buscar.setBackground(Color.DARK_GRAY);
		buscar.setForeground(Color.WHITE);
		buscar.setActionCommand(BUSCAR);
		buscar.addActionListener(this);

		guardar = new JButton("Guardar");
		guardar.setBackground(Color.DARK_GRAY);
		guardar.setForeground(Color.WHITE);
		guardar.setActionCommand(GUARDAR);
		guardar.addActionListener(this);

	}

	public JList<Jugador> getListJug() {

		return listaJug;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(AGREGAR)) {

			Seleccion sel = ventana.getListSel().getSelectedValue();

			if (sel == null) {

				JOptionPane.showMessageDialog(null,
						"Por favor seleccione una seleccion a la que desea agregar el jugador", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else {

				try {
					String imagen = ventana.getTxtImagen();
					String nombre = ventana.getTxtNombre().getText();
					String fecha = ventana.getFechaNac();
					double puntaje = Double.parseDouble(ventana.getTxtPuntajeFifa().getText());
					double altura = Double.parseDouble(ventana.getTxtAltura().getText());
					String posicion = ventana.getTxtPosicion().getText();
					Jugador jug = null;

					try {
						jug = sel.agregarJugador(imagen, nombre, posicion, puntaje, fecha, altura);
						ventana.save();
					} catch (JugadorYaExisteException ex) {

						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

					}

					if (jug != null) {
						listModel.addElement(jug);

						ventana.getTxtNombre().setText("");
						ventana.getTxtPuntajeFifa().setText("");
						ventana.getTxtPosicion().setText("");
						ventana.getTxtAltura().setText("");
						ventana.getImage().setIcon(ventana.getIconoJugadorNuevo());
						ventana.getDiasCombo().setSelectedIndex(0);
						ventana.getMesesCombo().setSelectedIndex(0);
						ventana.getAniosCombo().setSelectedIndex(0);
					}
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null,
							"Todos los campos deben completarse. (Los campos altura y puntaje deben ser numeros!!!)",
							"Error", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		}

		if (command.equals(BUSCAR)) {

			Seleccion sel = ventana.getListSel().getSelectedValue();

			if (sel != null) {

				try {
					String buscar = JOptionPane.showInputDialog(null,
							"Ingrese el nombre del Jugador que desea eliminar", "Eliminar Jugador",
							JOptionPane.INFORMATION_MESSAGE);
					Jugador player = sel.buscarJugador(buscar);

					listaJug.setSelectedValue(player, true);

					String[] fecha = player.getFechaNac().split("/");

					BufferedImage crop = null;
					try {
						if (player.getImagen() == null || player.getImagen().equals("")) {
							crop = ImageIO.read(new File("./data/images/cutUsuario.jpg"));
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {

						BufferedImage original = ImageIO.read(new File(player.getImagen()));
						if (original.getHeight() >= 200 || original.getWidth() >= 200) {
							crop = original.getSubimage(0, 0, 200, 200);
						} else {

							crop = ImageIO.read(new File(player.getImagen()));

						}

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NullPointerException ex) {

						

					}
					ventana.getImage().setIcon(new ImageIcon(crop));
					ventana.getTxtNombre().setText(player.getNombre());
					ventana.getTxtPosicion().setText(player.getPosicion());
					ventana.getTxtPuntajeFifa().setText(String.valueOf(player.getPuntajeFifa()));
					ventana.getTxtAltura().setText(String.valueOf(player.getAltura()));
					ventana.getDiasCombo().setSelectedItem(fecha[0]);
					ventana.getMesesCombo().setSelectedItem(fecha[1]);
					ventana.getAniosCombo().setSelectedItem(fecha[2]);

				} catch (JugadorNoRegistradoException e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				} catch (NoHayJugadoresRegistradosException ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}

			} else {

				JOptionPane.showMessageDialog(null, "Por favor seleccione una seleccion para ver sus jugadores", "Info",
						JOptionPane.INFORMATION_MESSAGE);

			}

		}

		if (command.equals(ELIMINAR)) {

			Seleccion sel = ventana.getListSel().getSelectedValue();
			if (sel != null) {
				try {
					String eliminar = JOptionPane.showInputDialog(null,
							"Ingrese el nombre del Jugador que desea eliminar", "Eliminar Jugador",
							JOptionPane.INFORMATION_MESSAGE);
					Jugador remove = sel.buscarJugador(eliminar);
					sel.eliminarJugador(eliminar);
					listModel.removeElement(remove);
					ventana.getTxtNombre().setText("");
					ventana.getTxtPuntajeFifa().setText("");
					ventana.getTxtPosicion().setText("");
					ventana.getTxtAltura().setText("");
					ventana.getImage().setIcon(ventana.getIconoJugadorNuevo());
					ventana.getDiasCombo().setSelectedIndex(0);
					ventana.getMesesCombo().setSelectedIndex(0);
					ventana.getAniosCombo().setSelectedIndex(0);
					ventana.save();
				} catch (JugadorNoRegistradoException e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				} catch (NoHayJugadoresRegistradosException ex) {

					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}

			} else {

				JOptionPane.showMessageDialog(null, "Por favor seleccione una seleccion para ver sus jugadores", "Info",
						JOptionPane.INFORMATION_MESSAGE);

			}
		}
		if (command.equals(GUARDAR)) {

			Seleccion sel = ventana.getListSel().getSelectedValue();

			if (sel != null) {

				Jugador player = ventana.getListJug().getSelectedValue();

				if (player == null) {

					JOptionPane.showMessageDialog(null,
							"Selecciona un jugador para guardar la informacion que le modifiques", "Info",
							JOptionPane.INFORMATION_MESSAGE);

				}
				try {
					String imagen = ventana.getTxtImagen();
					String nombre = ventana.getTxtNombre().getText();
					String fecha = ventana.getFechaNac();
					double puntaje = Double.parseDouble(ventana.getTxtPuntajeFifa().getText());
					double altura = Double.parseDouble(ventana.getTxtAltura().getText());
					String posicion = ventana.getTxtPosicion().getText();

					sel.modificarJugador(player, imagen, nombre, posicion, puntaje, fecha, altura);
					ventana.save();

					JOptionPane.showMessageDialog(null, "Se han guardados los datos de " + player.getNombre(), "Info",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,
							"Todos los campos deben completarse. (Los campos altura y puntaje deben ser numeros!!!)",
							"Error", JOptionPane.INFORMATION_MESSAGE);
				}

			} else {

				JOptionPane.showMessageDialog(null, "Por favor seleccione una seleccion para ver sus jugadores", "Info",
						JOptionPane.INFORMATION_MESSAGE);

			}

		}

	}

}
