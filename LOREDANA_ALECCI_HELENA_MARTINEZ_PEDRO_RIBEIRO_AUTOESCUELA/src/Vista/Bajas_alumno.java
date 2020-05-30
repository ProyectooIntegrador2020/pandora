package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;
/**
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de dar de 
 *	baja a un alumno. Se ejecutará con el botón ALUMNOS.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */
public class Bajas_alumno {
	
	JFrame ventana2;
	JPanel p;
	
	public Bajas_alumno(Autoescuela a) {
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
		titulo.setBounds(85, 11, 500, 44);
		p.add(titulo);
	
		JLabel dni = new JLabel("DNI");
		dni.setBounds(110, 100, 100, 20);
		p.add(dni);
		
		JTextField escrito_dni = new JTextField(15);
		escrito_dni.setBounds(200, 100, 200, 20);
		p.add(escrito_dni);
		
		//Boton que va a dar de baja al alumno que hayamos creado
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 200, 200, 20);
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dni_alumno;

				dni_alumno = escrito_dni.getText();
				try {
					Recepcionista.dar_de_baja_individual_alumno(dni_alumno, a);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				escrito_dni.setText(null);
			}
		};
		
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		ventana2.setVisible(true);
	}
	

}
