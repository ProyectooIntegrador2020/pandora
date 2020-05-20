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

public class Interfaz_principal {

	public static void ventana_principal(Autoescuela a) {
		JFrame ventana = new JFrame();
		ventana.setSize(600, 700);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
		ventana.add(p);
		p.setBackground(Color.pink);
		p.setLayout(null);
		
		JLabel titulo = new JLabel("AUTOESCUELA NOCHOQUES");
		titulo.setForeground(Color.BLUE);
		titulo.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		titulo.setBounds(85, 11, 500, 44);
		p.add(titulo);
		
		JLabel altas = new JLabel("ALTAS");
		altas.setFont(new Font("Tahoma", Font.BOLD, 14));
		altas.setBounds(50, 47, 55, 22);
		p.add(altas);
		
		JButton boton_alumno_altas = new JButton("ALUMNO");
		boton_alumno_altas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_alumno_altas.setBounds(60, 80, 93, 23);
		ActionListener accion_alta_alumno = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Alta_alumno alumno = new Alta_alumno(a);
				}
				catch (Exception u) {
					u.printStackTrace();
				}
			}
		};
		boton_alumno_altas.addActionListener(accion_alta_alumno);
		p.add(boton_alumno_altas);
		
		JButton boton_profesor_altas = new JButton("PROFESOR");
		boton_profesor_altas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_profesor_altas.setBounds(159, 80, 93, 23);
		ActionListener accion_alta_profesor = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Alta_profesor profe = new Alta_profesor(a);
				}
				catch (Exception u) {
					u.printStackTrace();
				}
			}
		};
		boton_profesor_altas.addActionListener(accion_alta_profesor);
		p.add(boton_profesor_altas);
		
		JLabel bajas = new JLabel("BAJAS");
		bajas.setFont(new Font("Tahoma", Font.BOLD, 14));
		bajas.setBounds(284, 51, 48, 14);
		p.add(bajas);
		
		JButton boton_alumnos_bajas = new JButton("ALUMNO");
		boton_alumnos_bajas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_alumnos_bajas.setBounds(294, 80, 89, 23);
		p.add(boton_alumnos_bajas);
		
		JButton boton_colectiva = new JButton("COLECTIVA");
		boton_colectiva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_colectiva.setBounds(393, 80, 115, 23);
		p.add(boton_colectiva);
		
		JLabel pagos = new JLabel("PAGOS");
		pagos.setFont(new Font("Tahoma", Font.BOLD, 14));
		pagos.setBounds(50, 114, 55, 22);
		p.add(pagos);
		
		JButton boton_cobros = new JButton("COBROS");
		boton_cobros.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_cobros.setBounds(60, 147, 89, 23);
		p.add(boton_cobros);
		
		JLabel gestion = new JLabel("GESTIÓN");
		gestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		gestion.setBounds(284, 114, 79, 22);
		p.add(gestion);
		
		JButton boton_asignar_alumno = new JButton("ASIGNAR ALUMNO A PROFESOR");
		boton_asignar_alumno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_asignar_alumno.setBounds(294, 148, 214, 23);
		p.add(boton_asignar_alumno);
		
		JButton boton_aprobado_teorico = new JButton("APROBADO DE TE\u00D3RICOS");
		boton_aprobado_teorico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_aprobado_teorico.setBounds(294, 182, 214, 23);
		p.add(boton_aprobado_teorico);
		
		JLabel informacion = new JLabel("INFORMACIÓN");
		informacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		informacion.setBounds(50, 225, 125, 14);
		p.add(informacion);
		
		JButton boton_lista_profesores = new JButton("LISTA PROFESORES");
		boton_lista_profesores.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_lista_profesores.setBounds(60, 250, 195, 23);
		p.add(boton_lista_profesores);
		
		JButton boton_lista_alumnos = new JButton("LISTA ALUMNOS");
		boton_lista_alumnos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_lista_alumnos.setBounds(60, 284, 195, 23);
		p.add(boton_lista_alumnos);
		
		JButton boton_lista_vehiculos = new JButton("LISTA VEHÍCULOS");
		boton_lista_vehiculos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_lista_vehiculos.setBounds(60, 318, 195, 23);
		p.add(boton_lista_vehiculos);
		
		JButton boton_lista_espera = new JButton("LISTA ALUMNOS ESPERA");
		boton_lista_espera.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_lista_espera.setBounds(60, 352, 195, 23);
		p.add(boton_lista_espera);
		
		JButton boton_lista_alumnos_profesor = new JButton("ALUMNOS DE UN PROFESOR");
		boton_lista_alumnos_profesor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_lista_alumnos_profesor.setBounds(60, 388, 195, 23);
		p.add(boton_lista_alumnos_profesor);
		
		JButton boton_datos_persona = new JButton("DATOS PERSONA");
		boton_datos_persona.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_datos_persona.setBounds(373, 250, 133, 23);
		p.add(boton_datos_persona);
		
		JButton boton_datos_coche = new JButton("DATOS COCHE");
		boton_datos_coche.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_datos_coche.setBounds(374, 285, 133, 23);
		p.add(boton_datos_coche);

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	
	}
}
