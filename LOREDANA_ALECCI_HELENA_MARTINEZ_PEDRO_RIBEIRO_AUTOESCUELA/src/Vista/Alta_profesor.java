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
import modelo.Coches;
import modelo.Profesor;
import modelo.Recepcionista;
/**
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de dar de alta a un
 * profesor. Se ejecutará con el botón PROFESOR.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */

public class Alta_profesor {

	JFrame ventana3;
	JPanel p;
	
	//El constructor va a llamar al método pintar, así en el ActionListener del botón, llama a la creación de
	//la interfaz.
	public Alta_profesor(Autoescuela a) {
		pinta(a);
	}
	
	/**
	 * Método que se encarga de crear y hacer visible la interfaz
	 * @param Autoescuela
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
		
		JLabel matricula_coche = new JLabel("MTR.COCHE");
		matricula_coche.setBounds(110, 190, 100, 20);
		p.add(matricula_coche);
		
		JTextField escrito_matricula_coche = new JTextField(15);
		escrito_matricula_coche.setBounds(200, 190, 200, 20);
		p.add(escrito_matricula_coche);
		
		//Boton que va a dar de alta al profesor que hayamos creado
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 250, 200, 20);
		//Esta es la acción
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
				Profesor profe = new Profesor(dni_profesor, edad_profesor, nombre_profesor, num_profesor, asignar_coche(matricula, a));
				Recepcionista.alta(profe, a);
				//Borramos todo
				escrito_nombre.setText(null);
				escrito_dni.setText(null);
				escrito_edad.setText(null);
				escrito_tel.setText(null);
				escrito_matricula_coche.setText(null);
				
			}
		};
		//Metemos la acción en el botón
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		//Hacemos la ventana visible, siempre va de último porque sino no se ve
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

