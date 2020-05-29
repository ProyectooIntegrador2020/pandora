package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.Autoescuela;
import modelo.Recepcionista;
/**
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de dar de realizar los cobros
 * a final de mes. Se ejecutará con el botón COBROS.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */
public class Ventana_cobros {


	JFrame ventana2;
	JPanel p;
	
	public Ventana_cobros(Autoescuela a) {
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
		listo.setBounds(270, 200, 200, 20);
		p.add(listo);
		
		//Boton que va a realizar el cobro
		JButton boton_aceptar = new JButton("COBRAR");
		boton_aceptar.setBounds(200, 100, 200, 20);
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Recepcionista.cobros(a);
				listo.setText("HECHO!");
				
			}
		};
		
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		ventana2.setVisible(true);
	}
	
}
