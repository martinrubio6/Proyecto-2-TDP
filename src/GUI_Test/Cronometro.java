package GUI_Test;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Modela el funcionamiento del reloj y sus atributos
 * 
 * @author Martin Rubio
 *
 */
public class Cronometro extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ------------------ ATRIBUTOS -------------------------//
	protected Timer timer;
	protected int segundos, minutos, horas;
	protected ImageIcon[] imagenes;
	protected JLabel horasDecena, horasUnidad, minutosDecena, minutosUnidad, segundosDecena, segundosUnidad;

	/**
	 * Inicializa el reloj
	 */
	public Cronometro() {
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
		JLabel dp1 = new JLabel();
		JLabel dp2 = new JLabel();
		horasDecena = new JLabel();
		horasUnidad = new JLabel();
		minutosDecena = new JLabel();
		minutosUnidad = new JLabel();
		segundosDecena = new JLabel();
		segundosUnidad = new JLabel();
		imagenes = setImagenes();
		organizar(dp1, dp2);
		segundos = minutos = horas = 0;
		timer = new Timer(1000, (ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (segundos < 59) {
					segundos++;
				} else if (minutos < 59) {
					minutos++;
					segundos = 0;

				} else {
					horas++;
					minutos = 0;
					segundos = 0;
				}
				segundosUnidad.setIcon(imagenes[segundos % 10]);
				reDimensionar(segundosUnidad, imagenes[segundos % 10]);
				segundosDecena.setIcon(imagenes[segundos / 10]);
				reDimensionar(segundosDecena, imagenes[segundos / 10]);
				minutosUnidad.setIcon(imagenes[minutos % 10]);
				reDimensionar(minutosUnidad, imagenes[minutos % 10]);
				minutosDecena.setIcon(imagenes[minutos / 10]);
				reDimensionar(minutosDecena, imagenes[minutos / 10]);
				horasUnidad.setIcon(imagenes[horas % 10]);
				reDimensionar(horasUnidad, imagenes[horas % 10]);
				horasDecena.setIcon(imagenes[horas / 10]);
				reDimensionar(horasDecena, imagenes[horas / 10]);
			}
		});
	}

	/**
	 * Agrega los diferentes labels al panel y se definen sus vistas gráficas.
	 * 
	 * @param dp1 - Label al que se le setea una imagen.
	 * @param dp2 - Label al que se le setea una imagen.
	 */
	public void organizar(JLabel dp1, JLabel dp2) {
		this.setLayout(new GridLayout(0, 8, 0, 0));

		horasDecena.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		this.add(horasDecena);

		horasUnidad.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		this.add(horasUnidad);

		dp1.setIcon(imagenes[10]);
		dp1.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		this.add(dp1);

		minutosDecena.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		this.add(minutosDecena);

		minutosUnidad.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		this.add(minutosUnidad);

		dp2.setIcon(imagenes[10]);
		dp2.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		this.add(dp2);

		segundosDecena.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		this.add(segundosDecena);

		segundosUnidad.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
		this.add(segundosUnidad);
	}

	/**
	 * Inicia el timer.
	 */
	public void start() {
		timer.start();
	}

	/**
	 * Detiene el timer.
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * Retorna el valor de segundos
	 * 
	 * @return segundos
	 */
	public int getSegundos() {
		return segundos;
	}

	/**
	 * Retorna el valor de minutos
	 * 
	 * @return minutos
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * Retorna el valor de las horas
	 * 
	 * @return horas
	 */
	public int getHoras() {
		return horas;
	}

	/**
	 * Crea una coleccion de Imagenes para ser utilizadas en las etiquetas
	 * 
	 * @return Coleccion de imagenes
	 */
	public ImageIcon[] setImagenes() {
		ImageIcon[] toReturn = new ImageIcon[11];
		toReturn[0] = new ImageIcon(getClass().getResource("/img/Cero.png"));
		toReturn[1] = new ImageIcon(getClass().getResource("/img/Uno.png"));
		toReturn[2] = new ImageIcon(getClass().getResource("/img/Dos.png"));
		toReturn[3] = new ImageIcon(getClass().getResource("/img/Tres.png"));
		toReturn[4] = new ImageIcon(getClass().getResource("/img/Cuatro.png"));
		toReturn[5] = new ImageIcon(getClass().getResource("/img/Cinco.png"));
		toReturn[6] = new ImageIcon(getClass().getResource("/img/Seis.png"));
		toReturn[7] = new ImageIcon(getClass().getResource("/img/Siete.png"));
		toReturn[8] = new ImageIcon(getClass().getResource("/img/Ocho.png"));
		toReturn[9] = new ImageIcon(getClass().getResource("/img/Nueve.png"));
		toReturn[10] = new ImageIcon(getClass().getResource("/img/Dots.png"));
		return toReturn;
	}

	/**
	 * Retorna la coleccion de Imagenes
	 * 
	 * @return coleccion de Imagenes
	 */
	public ImageIcon[] getImagenes() {
		return imagenes;
	}

	/**
	 * Redimensiona el grafico para que se ajuste al tamaño de la etiqueta
	 */
	protected void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}
