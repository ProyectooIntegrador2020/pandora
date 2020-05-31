package Vista;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.BBDD;
import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;

public class Ventana_mostrar_coches {


	JFrame ventana2;
	JPanel p;
	
	public Ventana_mostrar_coches(Autoescuela a) {
		pinta(a);
	}
	
	/**
	 * Método que se encarga de crear y hacer visible la interfaz
	 * @param a Autoescuela
	 */
	private void pinta(Autoescuela a) {
		ventana2 = new JFrame();
		ventana2.setSize(500, 400);
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
		titulo.setBounds(30, 11, 500, 44);
		p.add(titulo);
		
		DefaultTableModel dt = new DefaultTableModel();
	
		dt.addColumn("MATRÍCULA");
		dt.addColumn("GASOLINA");
		
		JScrollPane s= new JScrollPane();
		s.setBounds(130, 80, 200, 200);
		Object[] fila = new Object[dt.getColumnCount()];
		ArrayList<Coches> lt;
		try {
			lt = BBDD.obtenerCoches(a);
			for(Coches c: lt) {
				fila[0] = c.getMatricula();
				fila[1] = c.getLitros_gasolina();
				
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
