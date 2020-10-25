package GUI_Test;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Logica.Sudoku;
import Logica.Celda;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * Modela la implementacion grafica del juego Sudoku y sus atributos
 * 
 * @author Martin Rubio
 *
 */
public class GUISudoku extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ----------------------ATRIBUTOS---------------------------//

	private JPanel contentPane;
	private Sudoku juego;
	private Cronometro panel_Reloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISudoku frame = new GUISudoku();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public GUISudoku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		juego = new Sudoku();

		/**
		 * Creacion del tablero
		 */
		JPanel tablero = new JPanel();
		tablero.setBounds(0, 0, 588, 492);
		tablero.setLayout(new GridLayout(0, juego.getCantColumnas(), 0, 0));
		contentPane.add(tablero);

		/**
		 * Creacion del boton "Comprobar Solucion"
		 */
		JButton comprobar_solucion = new JButton("Comprobar Solucion");
		comprobar_solucion.setBounds(598, 11, 259, 66);
		contentPane.add(comprobar_solucion);
		comprobar_solucion.setFont(new Font("Tahoma", Font.PLAIN, 13));

		/**
		 * Creacion del panel de los botones para el reloj
		 */
		JPanel panelBotonesReloj = new JPanel();
		panelBotonesReloj.setBounds(597, 426, 260, 66);
		contentPane.add(panelBotonesReloj);
		panelBotonesReloj.setLayout(new GridLayout(1, 0, 0, 0));

		/**
		 * Creacion del boton "Reanudar" y su oyente
		 */
		JButton btn_Reanudar = new JButton("Reanudar");
		btn_Reanudar.setActionCommand("r");
		btn_Reanudar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Reanudar.setEnabled(false);
		btn_Reanudar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btn_Reanudar.isEnabled()) {
					Component boton = e.getComponent();
					String codigo = ((JButton) boton).getActionCommand();
					if (codigo == "r") {
						panel_Reloj.start();
						btn_Reanudar.setEnabled(false);
					}
				}
			}
		});
		panelBotonesReloj.add(btn_Reanudar);

		/**
		 * Creacion del boton "Pausar" y su oyente
		 */
		JButton btn_Pausar = new JButton("Pausar");
		btn_Pausar.setActionCommand("f");
		btn_Pausar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Pausar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component boton = e.getComponent();
				String codigo = ((JButton) boton).getActionCommand();
				if (codigo == "f") {
					panel_Reloj.stop();
					btn_Pausar.setActionCommand("f");
					btn_Reanudar.setEnabled(true);
					btn_Reanudar.setActionCommand("r");
				}

			}
		});
		panelBotonesReloj.add(btn_Pausar);

		/**
		 * Creo una etiqueta que hace mencion de como se expresa el tiempo del reloj
		 */
		JLabel lbl_Tiempo = new JLabel("Tiempo transcurrido en HS : MS : SS");
		lbl_Tiempo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbl_Tiempo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Tiempo.setBounds(598, 284, 259, 28);
		contentPane.add(lbl_Tiempo);

		/**
		 * Creo una instancia del reloj
		 */
		panel_Reloj = new Cronometro();
		panel_Reloj.setBounds(597, 323, 260, 74);
		contentPane.add(panel_Reloj);

		/**
		 * Oyente del boton comprobar_solucion
		 */
		comprobar_solucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (juego.comprobarSolucion()) {
					panel_Reloj.stop();
					btn_Pausar.setEnabled(false);
					btn_Reanudar.setEnabled(false);
					comprobar_solucion.setEnabled(false);
					inhabilitarTablero();
					JOptionPane.showMessageDialog(null, "Solucion correcta, FELICIDADES!");
				} else {
					for (int fil = 0; fil < juego.getCantFilas(); fil++) {
						for (int col = 0; col < juego.getCantColumnas(); col++) {
							Celda c = juego.getCelda(fil, col);
							if (c.getTieneError()) {
								c.getEtiqueta().setOpaque(true);
								c.getEtiqueta().setBackground(Color.RED);
							}
						}
					}
					JOptionPane.showMessageDialog(null, "Solucion Incorrecta");
				}
			}
		});

		/**
		 * En base a si el archivo contiene una solucion valida, determino si se puede
		 * jugar o no
		 */

		if (juego.EstadoInicialCorrecto()) {
			JOptionPane.showMessageDialog(null,
					"Bienvenido a Sudoku:"
							+ "\nComplete las celdas vacias con numeros del 1 al 9 haciendo click sobre estas"
							+ "\nBuena Suerte!");
		} else {
			JOptionPane.showMessageDialog(null,
					"El archivo contiene una solucion Sudoku incorrecta, modifiquelo y vuelva a intentar para poder jugar");
			tablero.setVisible(false);
			comprobar_solucion.setEnabled(false);
			btn_Pausar.setEnabled(false);
		}

		/**
		 * Relleno el tablero
		 */
		for (int i = 0; i < juego.getCantFilas(); i++) {
			for (int j = 0; j < juego.getCantColumnas(); j++) {
				Celda c = juego.getCelda(i, j);
				ImageIcon grafico = c.getEntiGrafica().getImagen();
				JLabel label = new JLabel();
				c.setEtiqueta(label);

				if (j % 3 == 0 && i % 3 == 0)
					label.setBorder(BorderFactory.createMatteBorder(8, 8, 1, 1, Color.BLACK));
				else if (i % 3 == 0)
					label.setBorder(BorderFactory.createMatteBorder(8, 1, 1, 1, Color.BLACK));
				else if (j % 3 == 0)
					label.setBorder(BorderFactory.createMatteBorder(1, 8, 1, 1, Color.BLACK));
				else
					label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

				/**
				 * Inhabilito las celdas que forman mi estado inicial
				 */

				if (c.getValor() != 0) {
					label.setEnabled(false);
				}

				label.addComponentListener(new ComponentAdapter() {
					public void componentResized(ComponentEvent e) {
						reDimensionar(label, grafico);
						label.setIcon(grafico);
					}
				});

				label.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (label.isEnabled()) {
							juego.accionar(c);
							reDimensionar(label, grafico);
						}
					}
				});

				tablero.add(label);
			}
		}

		/**
		 * Si el estado Inicial es correcto,que comience el reloj
		 */
		if (juego.EstadoInicialCorrecto())
			panel_Reloj.start();
	}

	// --------------------------- METODOS PRIVADOS------------------------//
	/**
	 * Redimensiona el grafico de una etiqueta para que ocupe todo el espacio de la
	 * celda
	 * 
	 * @param label   etiqueta del grafico
	 * @param grafico a redimensionar
	 */
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
			// Esto lo hago para que cuando se acciona sobre una celda a editar, se remueven
			// los rojos//
			ponerCeldasBlanca();
		}
	}

	/**
	 * Le asigna el color blanco de fondo a las celdas del tablero
	 */
	private void ponerCeldasBlanca() {
		for (int fil = 0; fil < juego.getCantFilas(); fil++) {
			for (int col = 0; col < juego.getCantColumnas(); col++) {
				Celda c = juego.getCelda(fil, col);
				c.getEtiqueta().setOpaque(true);
				c.getEtiqueta().setBackground(Color.white);
			}
		}
	}
	
	/**
	 * Inhabilita todas las celdas del tablero
	 */
	private void inhabilitarTablero() {
		for (int fil = 0; fil < juego.getCantFilas(); fil++) {
			for (int col = 0; col < juego.getCantColumnas(); col++) {
				Celda c = juego.getCelda(fil, col);
				c.getEtiqueta().setEnabled(false);
			}
		}
	}
}
