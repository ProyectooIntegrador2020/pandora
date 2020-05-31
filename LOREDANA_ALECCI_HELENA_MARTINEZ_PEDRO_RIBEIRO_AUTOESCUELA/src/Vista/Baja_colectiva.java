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
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de dar de baja
 * a todos los alumnos que ya se hayan sacado el carnet. Se ejecutará con el botón COLECTIVA.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */
public class Baja_colectiva {


	JFrame ventana2;
	JPanel p;
	
	public Baja_colectiva(Autoescuela a) {
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
	
		JLabel listo = new JLabel();
		listo.setBounds(230, 200, 200, 20);
		p.add(listo);
		
		//Boton que va a dar de baja a todos los que estén aprobados
		JButton boton_aceptar = new JButton("DAR DE BAJA");
		boton_aceptar.setBounds(200, 100, 200, 20);
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Recepcionista.dar_de_baja_colectiva(a)) {
						listo.setText("HECHO!");
					}
				} catch (SQLException e1) {
					listo.setText("No hay gente aprobada");
				}
				catch (Exception e2) {
					listo.setText("No hay gente aprobada");
				}
			}
		};
		
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		ventana2.setVisible(true);
	}
	

}
