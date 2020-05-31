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

import modelo.Autoescuela;
import modelo.Recepcionista;
/**
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de dar de asignar
 * a alumnos a los huecos vacíos de la lista de profesores. Se ejecutará con el botón ASIGNAR ALUMNO A PROFESOR.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */
public class Ventana_asignar_alumnos_profesor {


	JFrame ventana2;
	JPanel p;
	
	public Ventana_asignar_alumnos_profesor(Autoescuela a) {
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
	
		//Va a sacar el mensaje para comprobar lo que ha hecho el boton
		JLabel listo = new JLabel();
		listo.setBounds(270, 200, 200, 20);
		p.add(listo);
		
		//Boton que va a asignar los alumnos a los profesores
		JButton boton_aceptar = new JButton("Asignar alumnos");
		boton_aceptar.setBounds(200, 100, 200, 20);
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Recepcionista.asignar_alumno_profesor(a)) {
						listo.setText("HECHO!");
					}
					else {
						//Como la frase es más larga, se modifica dónde empieza el texto para que
						//este mensaje salga en el centro
						listo.setBounds(210, 200, 200, 20);
						listo.setText("NO SE HA MODIFICADO NADA :(");;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		ventana2.setVisible(true);
	}
	

}
