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

import DAO.BBDD;
import modelo.Autoescuela;
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;

public class Ventana_actualizar_profesor {

	JFrame ventana2;
	JPanel p;
	
	public Ventana_actualizar_profesor(Autoescuela a) {
		pinta(a);
	}
	
	/**
	 * M�todo que se encarga de crear y hacer visible la interfaz
	 * @param a Autoescuela
	 */
	private void pinta(Autoescuela a) {
		ventana2 = new JFrame();
		ventana2.setSize(600, 400);
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
		JLabel telefono = new JLabel("TEL�FONO");
		telefono.setBounds(110, 160, 100, 20);
		p.add(telefono);
		
		JTextField escrito_tel = new JTextField(15);
		escrito_tel.setBounds(200, 160, 200, 20);
		p.add(escrito_tel);
		
		JLabel matricula_coche = new JLabel("MTR.COCHE");
		matricula_coche.setBounds(110, 190, 100, 20);
		p.add(matricula_coche);
		
		JTextField escrito_matricula_coche = new JTextField(15);
		escrito_matricula_coche.setBounds(200, 190, 200, 20);
		p.add(escrito_matricula_coche);
		
		//Boton que va a dar de alta al profesor que hayamos creado
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 250, 200, 20);
		//Esta es la acci�n
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Variables que necesito para crear al profesor
				String dni_profesor;
				String nombre_profesor;
				int edad_profesor;
				int num_profesor;
				String matricula;
				//Las enlazo con el texto del TextField() correspondiente
				dni_profesor = escrito_dni.getText();
				//OJOOOO lo paso a integer porque el TEXTO me viene como STRING
				edad_profesor= Integer.parseInt(escrito_edad.getText());
				nombre_profesor = escrito_nombre.getText();
				num_profesor = Integer.parseInt(escrito_tel.getText());
				matricula = escrito_matricula_coche.getText();
				
				//Borramos todo
				escrito_nombre.setText(null);
				escrito_dni.setText(null);
				escrito_edad.setText(null);
				escrito_tel.setText(null);
				escrito_matricula_coche.setText(null);
				
			}
		};
		//Metemos la acci�n en el bot�n
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		//Hacemos la ventana visible, siempre va de �ltimo porque sino no se ve
		ventana2.setVisible(true);
	}
	
	/**
	 * M�todo que escoje un coche de la lista de coches de la autoescuela y la asigna al profesor en el momento en el
	 * que se le da de alta
	 * @param matricula String con la matricula del coche
	 * @param a Autoescuela donde se va a dar de alta al profesor
	 * @return Coches Coche a asignar al profesor 
	 * @throws SQLException 
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
		try {
			Recepcionista.alta(nuevo, a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nuevo;
	}
}

