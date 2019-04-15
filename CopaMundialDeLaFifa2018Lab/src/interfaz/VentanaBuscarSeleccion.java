package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import modelo.Seleccion;

public class VentanaBuscarSeleccion extends JDialog {

	public final static String INSERTAR = "insertar";
	public final static String GUARDAR = "guardar";

	private PanelSelecciones selecciones;
	private JPanel panelAuxiliarImagen;
	private JPanel panelAuxiliarInfo;
	private JLabel labImagenSel;
	private JLabel labPais;
	private JLabel labPuntos;
	private JLabel labPromAltura;
	private JLabel labPromEdad;
	private JLabel labPromFifa;
	private JLabel labImagen;
	private JTextField txtPais;
	private JTextField txtPuntos;
	private JTextField txtPromAltura;
	private JTextField txtPromEdad;
	private JTextField txtPromFifa;
	private JButton butInsertar;
	private JButton butGuardar;
	private String rutaImagen;
	private Icon noDisponible;

	public VentanaBuscarSeleccion(PanelSelecciones sel) {

		selecciones = sel;
		rutaImagen = null;
		noDisponible = new ImageIcon("./data/images/ImagenNoDisponible.png");

		setLayout(new GridLayout(1, 2));
		setTitle("Seleccion encontrada");
		setSize(700, 300);

		setResizable(false);

		panelAuxiliarImagen = new JPanel();
		panelAuxiliarImagen.setLayout(new GridLayout(1, 1));
		panelAuxiliarImagen.setBackground(Color.DARK_GRAY);
		labImagenSel = new JLabel();
		labImagenSel.setIcon(noDisponible);
		panelAuxiliarImagen.add(labImagenSel);

		panelAuxiliarInfo = new JPanel();
		panelAuxiliarInfo.setLayout(new GridLayout(7, 2));
		panelAuxiliarInfo.setBackground(Color.DARK_GRAY);

		labPais = new JLabel("Pais");
		labPais.setForeground(Color.WHITE);
		labPais.setFont(new Font("Garamond", 1, 18));
		labPuntos = new JLabel("Puntos");
		labPuntos.setForeground(Color.WHITE);
		labPuntos.setFont(new Font("Garamond", 1, 18));
		labPromAltura = new JLabel("Promedio Altura");
		labPromAltura.setForeground(Color.WHITE);
		labPromAltura.setFont(new Font("Garamond", 1, 18));
		labPromEdad = new JLabel("Promedio Edad");
		labPromEdad.setForeground(Color.WHITE);
		labPromEdad.setFont(new Font("Garamond", 1, 18));
		labPromFifa = new JLabel("Promedio FIFA");
		labPromFifa.setForeground(Color.WHITE);
		labPromFifa.setFont(new Font("Garamond", 1, 18));
		labImagen = new JLabel("Imagen");
		labImagen.setForeground(Color.WHITE);
		labImagen.setFont(new Font("Garamond", 1, 18));
		JLabel nada = new JLabel();

		txtPais = new JTextField();
		txtPais.setFont(new Font("Garamond", 1, 18));
		txtPais.setEditable(false);
		txtPuntos = new JTextField();
		txtPuntos.setFont(new Font("Garamond", 1, 18));
		txtPuntos.setEditable(false);
		txtPromAltura = new JTextField();
		txtPromAltura.setFont(new Font("Garamond", 1, 18));
		txtPromAltura.setEditable(false);
		txtPromEdad = new JTextField();
		txtPromEdad.setFont(new Font("Garamond", 1, 18));
		txtPromEdad.setEditable(false);
		txtPromFifa = new JTextField();
		txtPromFifa.setFont(new Font("Garamond", 1, 18));
		txtPromFifa.setEditable(false);
		butInsertar = new JButton("Insertar Imagen");
		butInsertar.setBackground(Color.DARK_GRAY);
		butInsertar.setForeground(Color.WHITE);
		butInsertar.setEnabled(false);

	
		butGuardar = new JButton("Guardar");
		butGuardar.setForeground(Color.WHITE);
		butGuardar.setBackground(Color.DARK_GRAY);
		butGuardar.setEnabled(false);
	

		panelAuxiliarInfo.add(labPais);
		panelAuxiliarInfo.add(txtPais);
		panelAuxiliarInfo.add(labPuntos);
		panelAuxiliarInfo.add(txtPuntos);
		panelAuxiliarInfo.add(labPromAltura);
		panelAuxiliarInfo.add(txtPromAltura);
		panelAuxiliarInfo.add(labPromEdad);
		panelAuxiliarInfo.add(txtPromEdad);
		panelAuxiliarInfo.add(labPromFifa);
		panelAuxiliarInfo.add(txtPromFifa);
		panelAuxiliarInfo.add(labImagen);
		panelAuxiliarInfo.add(butInsertar);
		panelAuxiliarInfo.add(nada);
		panelAuxiliarInfo.add(butGuardar);

		add(panelAuxiliarImagen);
		add(panelAuxiliarInfo);

	}

	public String getTxtPais() {

		return txtPais.getText();

	}

	public int getTxtPuntos() {

		return Integer.parseInt(txtPuntos.getText());

	}

	public void setTxtPais(String pais) {

		txtPais.setText(pais);

	}

	public void setTxtPuntos(int puntos) {

		txtPuntos.setText(String.valueOf(puntos));

	}

	public JLabel getLabImagenSel() {

		return labImagenSel;

	}

	public String getRutaImagen() {

		return rutaImagen;

	}
	
	public JTextField getTxtPromedioEdad() {
		
	return txtPromEdad;	
		
	}
	
	public JTextField getTxtPromedioAltura() {
		
	return txtPromAltura;	
		
	}
	
	public JTextField getTxtPromedioPuntaje() {
		
	return txtPromFifa;	
		
	}



}
