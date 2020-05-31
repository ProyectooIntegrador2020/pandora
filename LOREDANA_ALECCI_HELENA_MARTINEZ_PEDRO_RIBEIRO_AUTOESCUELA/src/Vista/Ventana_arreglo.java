package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.BBDD;
import modelo.Alumnos;
import modelo.Arreglo;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;

public class Ventana_arreglo {

	JFrame ventana2;
	JPanel p;
	
	public Ventana_arreglo(Autoescuela a) {
		pinta(a);
	}
	
	/**
	 * Método que se encarga de crear y hacer visible la interfaz
	 * @param a Autoescuela
	 */
	private void pinta(Autoescuela a) {
		ventana2 = new JFrame();
		ventana2.setSize(600, 400);
		//Cierra sólo la ventana
		ventana2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//La ventana se abre en el centro de la pantalla
		ventana2.setLocationRelativeTo(null);
		p = new JPanel();
		ventana2.add(p);
		//Va a insertar un Layout que nos va a permitir crear los BOUNDS de las cositas
		p.setLayout(null);
		
		JLabel titulo = new JLabel("AUTOESCUELA NOCHOQUES");
		//Color del fondo: Necesito Color+.+"nombre del color"
		titulo.setForeground(Color.BLUE);
		//El número es el tamaño de la letra
		titulo.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		//x - y - ancho - largo
		titulo.setBounds(105, 11, 500, 44);
		p.add(titulo);
		
		JLabel matricula = new JLabel("MATRÍCULA");
		matricula.setBounds(110, 70, 100, 20);
		p.add(matricula);
		
		JTextField escrito_mat = new JTextField(15);
		escrito_mat.setBounds(200, 70, 200, 20);
		p.add(escrito_mat);
		
		JLabel precio = new JLabel("PRECIO");
		precio.setBounds(110, 100, 100, 20);
		p.add(precio);
		
		JTextField escrito_precio = new JTextField(15);
		escrito_precio.setBounds(200, 100, 200, 20);
		p.add(escrito_precio);
		
		JLabel nombre = new JLabel("NOMBRE");
		nombre.setBounds(110, 130, 100, 20);
		p.add(nombre);
		
		JTextField escrito_nombre = new JTextField(15);
		escrito_nombre.setBounds(200, 130, 200, 20);
		p.add(escrito_nombre);
		
		JLabel fallo = new JLabel();
		p.add(fallo);
		
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 200, 200, 20);
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				float precio=Float.parseFloat(escrito_precio.getText());
				Profesor p;
				int id;
				try {
					if((p=buscar_coche(escrito_mat.getText(), a))!=null) {
						id =p.necesidad_arreglo(escrito_nombre.getText(), precio, a);
						BBDD.actualizarArregloCoche(id, p.getCoche().getMatricula());
					}
					else {
						fallo.setBounds(220, 220, 100, 23);
						fallo.setText("ERROR");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				escrito_mat.setText(null);
				escrito_nombre.setText(null);
				escrito_precio.setText(null);
				
			}
		};
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		ventana2.setVisible(true);
	}
	
	/**
	 * Método que se encarga de buscar que el coche introducido existe
	 * @param matricula Matricula del coche
	 * @param a Autoescuela
	 * @return El profesor que tiene la matrícula asignada igual que la pasada por parámetro
	 */
	public Profesor buscar_coche(String matricula, Autoescuela a) {
		Profesor p= null;
		for(Profesor po: a.getLista_profesores()) {
			if(po.getCoche().getMatricula().equalsIgnoreCase(matricula)) {
				p = new Profesor(po);
			}
		}
		return p;
	}
}
