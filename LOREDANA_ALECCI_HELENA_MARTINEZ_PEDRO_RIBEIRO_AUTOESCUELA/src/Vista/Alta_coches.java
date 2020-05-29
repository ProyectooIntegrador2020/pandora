package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Recepcionista;

/**
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de dar de alta a un
 * coches. Se ejecutará con el botón COCHES.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */
public class Alta_coches {

		JFrame ventana2;
		JPanel p;
		
		public Alta_coches(Autoescuela a) {
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
			
			JLabel mat = new JLabel("MATRÍCULA");
			mat.setBounds(110, 70, 100, 20);
			p.add(mat);
			
			JTextField escrito_mat = new JTextField(15);
			escrito_mat.setBounds(200, 70, 200, 20);
			p.add(escrito_mat);
			
			//Boton que va a dar de alta al coche que hayamos creado
			JButton boton_aceptar = new JButton("ACEPTAR");
			boton_aceptar.setBounds(200, 200, 200, 20);
			ActionListener aceptar = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Coches coche = new Coches(escrito_mat.getText());
					escrito_mat.setText(null);
				}
			};
			
			boton_aceptar.addActionListener(aceptar);
			p.add(boton_aceptar);
			
			ventana2.setVisible(true);
		}
	}

