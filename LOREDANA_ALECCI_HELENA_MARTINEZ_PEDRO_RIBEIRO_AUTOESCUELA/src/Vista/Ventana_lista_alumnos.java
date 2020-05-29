package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import DAO.BBDD;
import modelo.Autoescuela;
import modelo.Recepcionista;

/**
 * Esta clase es una interfaz que va a mostrar la lista de alumnos de la autoescuela.
 * 
 * @author Loredana Alecci
 * @author Helena Martinez
 * @author Pedro Ribeiro
 */
public class Ventana_lista_alumnos {

	JFrame ventana2;
	JPanel p;
	
	public Ventana_lista_alumnos(Autoescuela a) {
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
		
		String nombreColumnas[] = {"DNI", "Edad", "Nombre", "Tel�fono", "Tipo examen", "Tipo matr�cula", "Clases por dar", "Pagado", "DNI profesor"};
		//String contenidoColumnas[][] = "SELECT * FROM alumnos";
		//JTable tabla = new JTable(contenidoColumnas, nombreColumnas);
		
		ventana2.setVisible(true);
	}
	
}
