package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;

/**
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de repostar 
 * la gasolina de los coches.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */
public class Ventana_repostar {

	JFrame ventana3;
	JPanel p;
	
	//El constructor va a llamar al método pintar, así en el ActionListener del botón, llama a la creación de
	//la interfaz.
	public Ventana_repostar(Autoescuela a) {
		pinta(a);
	}
	
	/**
	 * Método que se encarga de crear y hacer visible la interfaz
	 * @param a Autoescuela
	 */
	private void pinta(Autoescuela a) {
		ventana3 = new JFrame();
		ventana3.setSize(600, 400);
		//Cierra sólo esta ventana
		ventana3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//La ventana se abre en el centro de la pantalla
		ventana3.setLocationRelativeTo(null);
		p = new JPanel();
		ventana3.add(p);
		//Va a insertar un Layout que nos va a permitir crear los BOUNDS de las cositas
		p.setLayout(null);
		
		JLabel titulo = new JLabel("AUTOESCUELA NOCHOQUES");
		//Color del fondo: Necesito Color+.+"nombre del color"
		titulo.setForeground(Color.BLUE);
		//El número es el tamaño de la letra
		titulo.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		//x - y - ancho - alto
		titulo.setBounds(85, 11, 500, 44);
		p.add(titulo);
		
		JLabel matricula_coche = new JLabel("Matrícula");
		matricula_coche.setBounds(110, 100, 100, 20);
		p.add(matricula_coche);
		
		JTextField escrito_matricula_coche = new JTextField(15);
		escrito_matricula_coche.setBounds(250, 100, 200, 20);
		p.add(escrito_matricula_coche);
		
		JLabel fallo = new JLabel();
		p.add(fallo);
		
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 200, 200, 20);
		//Esta es la acción
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String matricula;
				matricula = escrito_matricula_coche.getText();
				Coches c = null;
				if((c=buscar_coche(matricula, a))!=null) {
					c.repostar(a);
					fallo.setBounds(250, 280, 300, 20);
					fallo.setText("REPOSTADO");
				}
				else {
					fallo.setBounds(220, 280, 300, 20);
					fallo.setText("EL COCHE NO EXISTE");
				}
				
			}
		};
		//Metemos la acción en el botón
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		//Hacemos la ventana visible, siempre va de último porque sino no se ve
		ventana3.setVisible(true);
	}
	
	//Vamos a buscar al profesor porque sino no se puede llamar al método
	/**
	 * Método que busca recupera el objeto del coche que buscamos para usarlo en la interfaz.
	 * @param matricula String con la matricula del coche que buscamos.
	 * @param a Autoescuela que se está gestionando
	 * @return Coches un objeto de tipo Coche
	 */
	public Coches buscar_coche(String matricula, Autoescuela a) {
		Coches c = null;
		for(Coches p: a.getLista_vehiculos()) {
			if(p.getMatricula().equals(matricula)) {
				c=new Coches(p);
			}
		}
		return c;
	}
}
