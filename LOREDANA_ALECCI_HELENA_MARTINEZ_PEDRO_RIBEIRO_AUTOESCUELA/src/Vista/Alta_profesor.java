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

import modelo.Alumnos;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;

public class Alta_profesor {

	JFrame ventana3;
	JPanel p;
	
	public Alta_profesor(Autoescuela a) {
		pinta(a);
	}
	
	private void pinta(Autoescuela a) {
		ventana3 = new JFrame();
		ventana3.setSize(600, 400);
		//Cierra sólo la ventana
		ventana3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana3.setLocationRelativeTo(null);
		p = new JPanel();
		ventana3.add(p);
		p.setLayout(null);
		
		JLabel titulo = new JLabel("AUTOESCUELA NOCHOQUES");
		titulo.setForeground(Color.BLUE);
		titulo.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		titulo.setBounds(85, 11, 500, 44);
		p.add(titulo);
		
		JLabel nombre = new JLabel("NOMBRE");
		nombre.setBounds(110, 70, 100, 20);
		p.add(nombre);
		
		JTextField escrito_nombre = new JTextField(15);
		escrito_nombre.setBounds(200, 70, 200, 20);
		p.add(escrito_nombre);
		
		JLabel dni = new JLabel("DNI");
		dni.setBounds(110, 100, 100, 20);
		p.add(dni);
		
		JTextField escrito_dni = new JTextField(15);
		escrito_dni.setBounds(200, 100, 200, 20);
		p.add(escrito_dni);
		
		JLabel edad = new JLabel("EDAD");
		edad.setBounds(110, 130, 100, 20);
		p.add(edad);
		
		JTextField escrito_edad = new JTextField(15);
		escrito_edad.setBounds(200, 130, 200, 20);
		p.add(escrito_edad)
		;
		JLabel telefono = new JLabel("TELÉFONO");
		telefono.setBounds(110, 160, 100, 20);
		p.add(telefono);
		
		JTextField escrito_tel = new JTextField(15);
		escrito_tel.setBounds(200, 160, 200, 20);
		p.add(escrito_tel);
		
		JLabel matricula_coche = new JLabel("MT.COCHE");
		matricula_coche.setBounds(110, 190, 100, 20);
		p.add(matricula_coche);
		
		JTextField escrito_matricula_coche = new JTextField(15);
		escrito_matricula_coche.setBounds(200, 190, 200, 20);
		p.add(escrito_matricula_coche);
		
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 250, 200, 20);
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dni_profesor;
				String nombre_profesor;
				int edad_profesor;
				int num_profesor;
				String matricula;
				dni_profesor = escrito_dni.getText();
				edad_profesor= Integer.parseInt(escrito_edad.getText());
				nombre_profesor = escrito_nombre.getText();
				num_profesor = Integer.parseInt(escrito_tel.getText());
				matricula = escrito_matricula_coche.getText();
				Profesor profe = new Profesor(dni_profesor, edad_profesor, nombre_profesor, num_profesor, asignar_coche(matricula, a));
				Recepcionista.alta(profe, a);
			}
		};
		
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		ventana3.setVisible(true);
	}
	
	/**
	 * Método que escoje un coche de la lista de coches de la autoescuela y la asigna al profesor en el momento en el
	 * que se le da de alta
	 * @param matricula String con la matricula del coche
	 * @param Autoescuela donde se va a dar de alta al profesor
	 * @return Coche a asignar al profesor 
	 */
	public Coches asignar_coche(String matricula, Autoescuela a) {
		//Variable que almacena un coche nuevo en caso de necesitarlo.
		Coches nuevo = null;
		//Recorro la lista de coches de la autoescuela
		for (Coches c: a.getLista_vehiculos()) {
			//Si coincide con la matricula pasada devuelve el coche actual.
			if (c.getMatricula().equalsIgnoreCase(matricula))
				return c;
		}
		//Si se sale del bucle y llega aqui es porque no ha habido coincidencias. El coche no existe. Se crea uno,
		// se le da de alta en la autoescuela y se devuelve.
		nuevo = new Coches(matricula);
		Recepcionista.alta(nuevo, a);
		return nuevo;
	}
}
