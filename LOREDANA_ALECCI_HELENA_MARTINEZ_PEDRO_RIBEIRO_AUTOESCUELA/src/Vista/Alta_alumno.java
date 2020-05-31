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
 * Esta clase es una interfaz, dentro de la interfaz principal, la cual se va a encargar de dar de alta a un
 * alumno. Se ejecutará con el botón ALUMNO.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */

public class Alta_alumno {
	JFrame ventana2;
	JPanel p;
	
	public Alta_alumno(Autoescuela a) {
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
		p.add(escrito_edad);
		
		JLabel telefono = new JLabel("TELÉFONO");
		telefono.setBounds(110, 160, 100, 20);
		p.add(telefono);
		
		JTextField escrito_tel = new JTextField(15);
		escrito_tel.setBounds(200, 160, 200, 20);
		p.add(escrito_tel);
		
		JLabel descripcion = new JLabel("1(BÁSICO) 2(INTERMEDIO) 3(COMPLETO)");
		descripcion.setFont(new Font("SimSun", Font.BOLD, 14));
		descripcion.setBounds(160, 190, 250, 18);
		p.add(descripcion);
		
		JLabel num_matricula = new JLabel("TIPO MTR");
		num_matricula.setBounds(110, 220, 100, 20);
		p.add(num_matricula);
		
		JTextField escrito_num_matricula = new JTextField(15);
		escrito_num_matricula.setBounds(200, 220, 200, 20);
		p.add(escrito_num_matricula);
		
		JLabel error = new JLabel();
		error.setBounds(245, 300, 200, 23);
		p.add(error);
		
		//Boton que va a dar de alta al alumno que hayamos creado
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 270, 200, 20);
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Variables
				String dni_alumno;
				String nombre_alumno;
				int edad_alumno;
				int num;
				int numero_matricula;
				//A cada variable le asigno lo que saque del JTextField()
				dni_alumno = escrito_dni.getText();
				//OJOOOO lo paso a integer porque el TEXTO me viene como STRING
				edad_alumno= Integer.parseInt(escrito_edad.getText());
				nombre_alumno = escrito_nombre.getText();
				num = Integer.parseInt(escrito_tel.getText());
				numero_matricula=Integer.parseInt(escrito_num_matricula.getText());
				Alumnos alumno = new Alumnos(dni_alumno, edad_alumno, nombre_alumno, num, Recepcionista.asignar_matricula(numero_matricula));
				try {
					Recepcionista.alta(alumno, a);
					error.setText("HECHO");
					//Borramos todo
					escrito_nombre.setText(null);
					escrito_dni.setText(null);
					escrito_edad.setText(null);
					escrito_tel.setText(null);
					escrito_num_matricula.setText(null);
				} catch (SQLException e1) {
					error.setText("DATOS DUPLICADOS");
				}
				
			}
		};
		
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		ventana2.setVisible(true);
	}
	
	/*public tipoMatricula asignar_matricula(int numero) {
		tipoMatricula tm = null;
		switch (numero) {
		case 1:
			tm=tipoMatricula.basico;
			break;
		case 2:
			tm=tipoMatricula.intermedio;
			break;
		case 3:
			tm=tipoMatricula.completo;
			break;
		default:
			break;
		}
		return tm;
	}
	*/
}


