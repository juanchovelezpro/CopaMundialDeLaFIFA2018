
package interfaz;

import modelo.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelSelecciones extends JPanel implements ActionListener {

	public final static String AGREGAR = "agregar";
	public final static String ELIMINAR = "eliminar";
	public final static String BUSCAR = "buscar";
	public final static String MODIFICAR = "modificar";


	private VentanaPrincipal ventana;
	private JPanel panelAuxiliarBotones;
	private JButton agregar;
	private JButton eliminar;
	private JButton buscar;
	private JButton modificar;
	private JList<Seleccion> listaSel;
	private DefaultListModel<Seleccion> listModel;
	private VentanaAgregarSeleccion emergenteSeleccion;
	private VentanaModificarSeleccion emergenteModificar;
	private VentanaBuscarSeleccion emergenteBuscar;

	public PanelSelecciones(VentanaPrincipal window) {

		ventana = window;

		setLayout(new BorderLayout());

		Border bordePro = BorderFactory.createRaisedBevelBorder();
		TitledBorder border = BorderFactory.createTitledBorder(bordePro, "Selecciones", TitledBorder.CENTER,
				TitledBorder.ABOVE_TOP, new Font("Garamond", Font.BOLD, 20), Color.WHITE);
		setBorder(border);
		setBackground(Color.DARK_GRAY);

		emergenteSeleccion = new VentanaAgregarSeleccion(this);
		emergenteModificar = new VentanaModificarSeleccion(this);
		emergenteBuscar = new VentanaBuscarSeleccion(this);

		listModel = new DefaultListModel<Seleccion>();
		listaSel = new JList<Seleccion>(listModel);
		listaSel.setFixedCellHeight(40);
		listaSel.setFixedCellWidth(400);
		listaSel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaSel.setLayoutOrientation(JList.VERTICAL_WRAP);	
		listaSel.setBackground(Color.GRAY);
		listaSel.setFont(new Font("Garamond", 1, 24));
		listaSel.setForeground(Color.WHITE);
		JScrollPane myScrollList = new JScrollPane(listaSel);

		myScrollList.setPreferredSize(new Dimension(400, 800));

		botones();

		panelAuxiliarBotones = new JPanel();
		panelAuxiliarBotones.setBackground(Color.DARK_GRAY);
		panelAuxiliarBotones.setLayout(new GridLayout(1, 5, 2, 2));
		panelAuxiliarBotones.add(agregar);
		panelAuxiliarBotones.add(eliminar);
		panelAuxiliarBotones.add(buscar);
		panelAuxiliarBotones.add(modificar);


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

		modificar = new JButton("Modificar");
		modificar.setBackground(Color.DARK_GRAY);
		modificar.setForeground(Color.WHITE);
		modificar.setActionCommand(MODIFICAR);
		modificar.addActionListener(this);
		
	
	}

	public VentanaPrincipal getPrincipal() {

		return ventana;

	}

	public DefaultListModel<Seleccion> getListSel() {

		return listModel;

	}

	public JList<Seleccion> getListaSeleccion() {

		return listaSel;

	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals(AGREGAR)) {

			emergenteSeleccion.setVisible(true);

		}

		if (command.equals(MODIFICAR)) {

			if (listaSel.getSelectedValue() == null) {

				JOptionPane.showMessageDialog(null, "No hay una seleccion seleccionada", "Error",
						JOptionPane.ERROR_MESSAGE);

			} else {

				emergenteModificar.setVisible(true);
				String pais = listaSel.getSelectedValue().getPais();
				int puntos = listaSel.getSelectedValue().getPuntos();
				String ruta = listaSel.getSelectedValue().getImagen();
				emergenteModificar.setTxtPais(pais);
				emergenteModificar.setTxtPuntos(puntos);
				emergenteModificar.getLabImagenSel().setIcon(new ImageIcon(ruta));
				emergenteModificar.getTxtPromedioEdad()
						.setText(String.valueOf(listaSel.getSelectedValue().calcularPromedioEdad()));
				emergenteModificar.getTxtPromedioAltura()
						.setText(String.valueOf(listaSel.getSelectedValue().calcularPromedioAltura()));
				emergenteModificar.getTxtPromedioPuntaje()
						.setText(String.valueOf(listaSel.getSelectedValue().calcularPromedioFifa()));

			}
		}

		if (command.equals(ELIMINAR)) {

			String pais = JOptionPane.showInputDialog(null, "Ingrese la seleccion que desea eliminar",
					"Eliminar Seleccion", JOptionPane.INFORMATION_MESSAGE);

			Seleccion sel = ventana.buscarSeleccion(pais);

			if (sel != null) {
				ventana.eliminarSeleccion(sel.getPais());
				listModel.removeElement(sel);
				ventana.save();
			}

			// else {
			//
			// JOptionPane.showMessageDialog(null, "Seleccione la seleccion a eliminar",
			// "Error",
			// JOptionPane.INFORMATION_MESSAGE);
			//
			// }

		}

		if (command.equals(BUSCAR)) {

			String pais = JOptionPane.showInputDialog(null, "Ingrese la seleccion que desea buscar", "Buscar Seleccion",
					JOptionPane.INFORMATION_MESSAGE);

			Seleccion sel = ventana.buscarSeleccion(pais);

			if (sel != null) {
				emergenteBuscar.setVisible(true);

				int puntos = sel.getPuntos();
				String ruta = sel.getImagen();
				emergenteBuscar.setTxtPais(pais);
				emergenteBuscar.setTxtPuntos(puntos);
				emergenteBuscar.getLabImagenSel().setIcon(new ImageIcon(ruta));
				
				listaSel.setSelectedValue(sel, true);
				
				try {
					emergenteBuscar.getTxtPromedioEdad()
							.setText(String.valueOf(listaSel.getSelectedValue().calcularPromedioEdad()));
					emergenteBuscar.getTxtPromedioAltura()
							.setText(String.valueOf(listaSel.getSelectedValue().calcularPromedioAltura()));
					emergenteBuscar.getTxtPromedioPuntaje()
							.setText(String.valueOf(listaSel.getSelectedValue().calcularPromedioFifa()));
				} catch (NullPointerException ex) {

					JOptionPane.showMessageDialog(null,
							"Los promedios no han sido calculados ya que no hay jugadores registrados en esta seleccion",
							"Info", JOptionPane.INFORMATION_MESSAGE);

				}

				pais = emergenteBuscar.getTxtPais();
				puntos = emergenteBuscar.getTxtPuntos();
				ruta = emergenteBuscar.getRutaImagen();
			}

		}


	}


}
