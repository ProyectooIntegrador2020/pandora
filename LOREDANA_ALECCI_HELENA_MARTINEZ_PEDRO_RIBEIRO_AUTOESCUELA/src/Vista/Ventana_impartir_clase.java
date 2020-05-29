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

import modelo.Autoescuela;
import modelo.Profesor;
import modelo.Recepcionista;
/**
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de dar de 
 * gestionar el número de clases de cada alumno y la gasolina que se vaya gastando.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */
public class Ventana_impartir_clase {
	JFrame ventana3;
	JPanel p;
	
	//El constructor va a llamar al método pintar, así en el ActionListener del botón, llama a la creación de
	//la interfaz.
	public Ventana_impartir_clase(Autoescuela a) {
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
		
		JLabel dni_profe = new JLabel("DNI PROFESOR");
		dni_profe.setBounds(110, 100, 100, 20);
		p.add(dni_profe);
		
		JTextField escrito_dni_profe = new JTextField(15);
		escrito_dni_profe.setBounds(250, 100, 200, 20);
		p.add(escrito_dni_profe);
		
		JLabel dni_alumno = new JLabel("DNI ALUMNO");
		dni_alumno.setBounds(110, 140, 100, 20);
		p.add(dni_alumno);
		
		JTextField escrito_dni_alumno = new JTextField(15);
		escrito_dni_alumno.setBounds(250, 140, 200, 20);
		p.add(escrito_dni_alumno);
		
		JLabel numero = new JLabel("NUMERO DE CLASES");
		numero.setBounds(110, 180, 150, 20);
		p.add(numero);
		
		JTextField escrito_num = new JTextField(15);
		escrito_num.setBounds(250, 180, 200, 20);
		p.add(escrito_num);
		
		JLabel fallo = new JLabel();
		fallo.setBounds(220, 280, 300, 20);
		p.add(fallo);
		
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 250, 200, 20);
		//Esta es la acción
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dni_prof;
				String dni_alu;
				int numero;
				Profesor p=null;
				dni_prof=escrito_dni_profe.getText();
				dni_alu=escrito_dni_alumno.getText();
				numero= Integer.parseInt(escrito_num.getText());
				if((p=buscar_profesor(dni_prof, a))!=null) {
					boton_aceptar.setBounds(150, 250, 200, 20);
					fallo.setText(p.imparte_clase(a, numero, dni_alu));
				}
				else {
					fallo.setText("EL PROFESOR NO EXISTE");
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
	 * Método que recupera el objeto de tipo Profesor para ser usado en la interfaz.
	 * @param dni_prof DNI del profesor a buscar
	 * @param a Autoescuela que se está gestionando
	 * @return Profesor objeto profesor que corresponde a ese dni.
	 */
	public Profesor buscar_profesor(String dni_prof, Autoescuela a) {
		Profesor profe = null;
		for(Profesor p: a.getLista_profesores()) {
			if(p.getDni().equalsIgnoreCase(dni_prof)) {
				profe = new Profesor(p);
			}
		}
		return profe;
	}
	
}
