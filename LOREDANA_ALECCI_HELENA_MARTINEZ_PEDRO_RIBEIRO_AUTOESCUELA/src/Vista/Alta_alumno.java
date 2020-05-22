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
import modelo.Recepcionista;
import modelo.tipos_matricula_examen.tipoMatricula;


public class Alta_alumno {
	JFrame ventana2;
	JPanel p;
	
	public Alta_alumno(Autoescuela a) {
		pinta(a);
	}
	
	private void pinta(Autoescuela a) {
		ventana2 = new JFrame();
		ventana2.setSize(600, 400);
		//Cierra sólo la ventana
		ventana2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana2.setLocationRelativeTo(null);
		p = new JPanel();
		ventana2.add(p);
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
		dni.setBounds(110, 110, 100, 20);
		p.add(dni);
		
		JTextField escrito_dni = new JTextField(15);
		escrito_dni.setBounds(200, 110, 200, 20);
		p.add(escrito_dni);
		
		JLabel edad = new JLabel("EDAD");
		edad.setBounds(110, 150, 100, 20);
		p.add(edad);
		
		JTextField escrito_edad = new JTextField(15);
		escrito_edad.setBounds(200, 150, 200, 20);
		p.add(escrito_edad)
		;
		JLabel telefono = new JLabel("TELÉFONO");
		telefono.setBounds(110, 190, 100, 20);
		p.add(telefono);
		
		JTextField escrito_tel = new JTextField(15);
		escrito_tel.setBounds(200, 190, 200, 20);
		p.add(escrito_tel);
		
		JLabel num_matricula = new JLabel("TIPO MATRÍCULA");
		telefono.setBounds(110, 190, 100, 20);
		p.add(num_matricula);
		
		JTextField escrito_num_matricula = new JTextField(15);
		escrito_tel.setBounds(200, 190, 200, 20);
		p.add(escrito_num_matricula);
		
		JButton boton_aceptar = new JButton("ACEPTAR");
		boton_aceptar.setBounds(200, 300, 200, 20);
		ActionListener aceptar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dni_alumno;
				String nombre_alumno;
				int edad_alumno;
				int num;
				int numero_matricula;
				dni_alumno = escrito_dni.getText();
				edad_alumno= Integer.parseInt(escrito_edad.getText());
				nombre_alumno = escrito_nombre.getText();
				num = Integer.parseInt(escrito_tel.getText());
				numero_matricula=Integer.parseInt(escrito_num_matricula.getText());
				Alumnos alumno = new Alumnos(dni_alumno, edad_alumno, nombre_alumno, num, asignar_matricula(numero_matricula));
				Recepcionista.alta(alumno, a);
			}
		};
		
		boton_aceptar.addActionListener(aceptar);
		p.add(boton_aceptar);
		
		ventana2.setVisible(true);
	}
	
	public tipoMatricula asignar_matricula(int numero) {
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
	
}
