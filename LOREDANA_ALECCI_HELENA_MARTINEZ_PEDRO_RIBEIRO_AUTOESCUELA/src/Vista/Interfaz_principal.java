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
 * Esta clase es la interfaz principal del programa
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 *
 */
public class Interfaz_principal {

	/**
	 * Métodoe encargado de generar la interfaz
	 * @param a Autoescuela
	 */
	public static void ventana_principal(Autoescuela a) {
		JFrame ventana = new JFrame();
		ventana.setSize(600, 500);
		//La ventana se va a cerrar completamente y también todas las demás ventanas
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel p = new JPanel();
		ventana.add(p);
		//Asignamos un color al panel con Color+.+"color"
		p.setBackground(Color.lightGray);
		//Le ponemos un Layout para poder colocar los botones donde queramos con los BOUNDS
		p.setLayout(null);
		
		JLabel titulo = new JLabel("AUTOESCUELA NOCHOQUES");
		titulo.setForeground(Color.BLUE);
		//El número es el tamaño de la fuente
		titulo.setFont(new Font("Showcard Gothic", Font.BOLD, 30));
		//x y ancho alto
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
					//Llamamos al constructor de la ventana que queremos que se abra
					Alta_alumno alumno = new Alta_alumno(a);
				}
				catch (Exception u) {
					u.printStackTrace();
				}
			}
		};
		//le asignamos una función al botón
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
		
		JButton boton_coche_altas = new JButton("COCHES");
		boton_coche_altas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_coche_altas.setBounds(60, 115, 93, 23);
		ActionListener accion_alta_coche = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Llamamos al constructor de la ventana que queremos que se abra
					Alta_coches coche = new Alta_coches(a);
				}
				catch (Exception u) {
					u.printStackTrace();
				}
			}
		};
		//le asignamos una función al botón
		boton_coche_altas.addActionListener(accion_alta_coche);
		p.add(boton_coche_altas);
		
		JLabel bajas = new JLabel("BAJAS");
		bajas.setFont(new Font("Tahoma", Font.BOLD, 14));
		bajas.setBounds(284, 51, 48, 14);
		p.add(bajas);
		
		JButton boton_alumnos_bajas = new JButton("ALUMNO");
		boton_alumnos_bajas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_alumnos_bajas.setBounds(294, 80, 89, 23);
		
		ActionListener accion_baja_alumno = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Bajas_alumno baja = new Bajas_alumno(a);
				}
				catch (Exception u) {
					u.printStackTrace();
				}
			}
		};
		boton_alumnos_bajas.addActionListener(accion_baja_alumno);
		p.add(boton_alumnos_bajas);
		
		JButton boton_colectiva = new JButton("COLECTIVA");
		boton_colectiva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_colectiva.setBounds(393, 80, 115, 23);
		ActionListener accion_baja_colectiva = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Baja_colectiva baja = new Baja_colectiva(a);
			}
		};
		boton_colectiva.addActionListener(accion_baja_colectiva);
		p.add(boton_colectiva);
		
		JLabel pagos = new JLabel("PAGOS");
		pagos.setFont(new Font("Tahoma", Font.BOLD, 14));
		pagos.setBounds(50, 154, 55, 22);
		p.add(pagos);
		
		JButton boton_cobros = new JButton("COBROS");
		boton_cobros.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_cobros.setBounds(60, 187, 89, 23);
		ActionListener accion_cobrar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Ventana_cobros co = new Ventana_cobros(a);
				}
				catch (Exception k) {
					k.printStackTrace();
				}
			}
		};
		boton_cobros.addActionListener(accion_cobrar);
		p.add(boton_cobros);
		
		JLabel gestion = new JLabel("GESTIÓN");
		gestion.setFont(new Font("Tahoma", Font.BOLD, 14));
		gestion.setBounds(284, 114, 79, 22);
		p.add(gestion);
		
		JButton boton_asignar_alumno = new JButton("ASIGNAR ALUMNO A PROFESOR");
		boton_asignar_alumno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_asignar_alumno.setBounds(294, 148, 214, 23);
		ActionListener accion_asignar_alumno_profesor = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Ventana_asignar_alumnos_profesor vent_al_p = new Ventana_asignar_alumnos_profesor(a);
				}
				catch (Exception h) {
					h.printStackTrace();
				}
			}
		};
		boton_asignar_alumno.addActionListener(accion_asignar_alumno_profesor);
		p.add(boton_asignar_alumno);
		
		JButton boton_aprobado_teorico = new JButton("APROBADO DE TEÓRICOS");
		boton_aprobado_teorico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_aprobado_teorico.setBounds(294, 182, 214, 23);
		ActionListener accion_aprobado_teorico = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Recepcionista.gestionarAprobadosTeoricos(a);
				}
				catch (Exception s) {
					s.printStackTrace();
				}
			}
		};
		boton_aprobado_teorico.addActionListener(accion_aprobado_teorico);
		p.add(boton_aprobado_teorico);
		
		
		JButton boton_impartir_clase = new JButton("IMPARTIR CLASE");
		boton_impartir_clase.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_impartir_clase.setBounds(294, 216, 214, 23);
		ActionListener accion_impartir_clase = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Ventana_impartir_clase imp = new Ventana_impartir_clase(a);
				}
				catch (Exception s) {
					s.printStackTrace();
				}
			}
		};
		boton_impartir_clase.addActionListener(accion_impartir_clase);
		p.add(boton_impartir_clase);
		
		JButton botono_repostar = new JButton("REPOSTAR");
		botono_repostar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		botono_repostar.setBounds(294, 250, 214, 23);
		ActionListener accion_repostar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Ventana_repostar rep = new Ventana_repostar(a);
				}
				catch (Exception s) {
					s.printStackTrace();
				}
			}
		};
		botono_repostar.addActionListener(accion_repostar);
		p.add(botono_repostar);
		
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
		ActionListener accion_mostrar_alumnos = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				//Ventana_lista_alumnos vent = new Ventana_lista_alumnos(a);
				}
				catch (Exception b) {
					b.printStackTrace();
				}
			}
		};
		boton_lista_alumnos.addActionListener(accion_mostrar_alumnos);
		p.add(boton_lista_alumnos);
		
		JButton boton_lista_vehiculos = new JButton("LISTA VEHÍCULOS");
		boton_lista_vehiculos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_lista_vehiculos.setBounds(60, 318, 195, 23);
		p.add(boton_lista_vehiculos);
		
		JButton boton_datos_persona = new JButton("DATOS PERSONA");
		boton_datos_persona.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_datos_persona.setBounds(60, 354, 133, 23);
		p.add(boton_datos_persona);
		
		JButton boton_datos_coche = new JButton("DATOS COCHE");
		boton_datos_coche.setFont(new Font("Tahoma", Font.PLAIN, 12));
		boton_datos_coche.setBounds(60, 388, 133, 23);
		p.add(boton_datos_coche);
		

		//Coloca la ventana en el centro nada más abrirse
		ventana.setLocationRelativeTo(null);
		//Hace visible la ventana, se tiene que poner de último
		ventana.setVisible(true);
	
	}
}

