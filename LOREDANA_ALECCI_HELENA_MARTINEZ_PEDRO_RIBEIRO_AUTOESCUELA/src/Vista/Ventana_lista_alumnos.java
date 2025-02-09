package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Scrollable;
import javax.swing.table.DefaultTableModel;

import DAO.BBDD;
import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Recepcionista;

/**
 * Esta clase es una interfaz que va a mostrar la lista de alumnos de la autoescuela.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 */
public class Ventana_lista_alumnos {

	JFrame ventana2;
	JPanel p;
	
	public Ventana_lista_alumnos(Autoescuela a) {
		pinta(a);
	}
	
	/**
	 * M�todo que se encarga de crear y hacer visible la interfaz
	 * @param a Autoescuela
	 */
	private void pinta(Autoescuela a) {
		ventana2 = new JFrame();
		ventana2.setSize(1000, 400);
		//Cierra s�lo la ventana
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
		//El n�mero es el tama�o de la letra
		titulo.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		//x - y - ancho - largo
		titulo.setBounds(280, 11, 500, 44);
		p.add(titulo);
		
		DefaultTableModel dt = new DefaultTableModel();
		
		dt.addColumn("DNI");
		dt.addColumn("EDAD");
		dt.addColumn("NOMBRE");
		dt.addColumn("TEL�FONO");
		dt.addColumn("TIPO EXAMEN");
		dt.addColumn("TIPO MATR�CULA");
		dt.addColumn("CLASES POR DAR");
		dt.addColumn("PAGADO");
		
		JScrollPane s= new JScrollPane();
		s.setBounds(0, 80, 1000, 200);
		Object[] fila = new Object[dt.getColumnCount()];
		ArrayList<Alumnos> lt;
		try {
			lt = BBDD.obtenerAlumnos(a);
			for(Alumnos alumno: lt) {
				fila[0] = alumno.getDni();
				fila[1] = alumno.getEdad();
				fila[2] = alumno.getNombre();
				fila[3] = alumno.getNum_tel();
				fila[4] = alumno.getExamen();
				fila[5] = alumno.getMatricula_pagos();
				fila[6] = alumno.getClases_por_dar();
				fila[7] = alumno.isPagado();
				
				dt.addRow(fila);
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JTable tabla = new JTable(dt);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		s.add(tabla);
		//OJO
		s.setViewportView(tabla);
		//p.add(tabla);
		p.add(s);
		
		ventana2.setVisible(true);
	}
	
}
