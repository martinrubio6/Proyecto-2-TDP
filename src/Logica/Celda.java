package Logica;

import javax.swing.JLabel;

/**
 * Modela el funcionamiento de una celda del Sudoku y sus atributos
 * 
 * @author Martin Rubio
 *
 */
public class Celda {

	private int valor;
	private Entidad_Grafica entGrafica;
	private boolean tieneError;
	private JLabel etiqueta;

	/**
	 * Inicializa una celda
	 */
	public Celda() {
		this.valor = 0;
		entGrafica = new Entidad_Grafica();
		entGrafica.actualizarImagen(this.valor);
		tieneError = false;
		etiqueta = null;
	}

	/**
	 * Actualiza el valor de la celda con su respetivo grafico
	 */
	public void actualizarCelda() {
		if (this.valor + 1 < this.getCantElementos()) {
			this.valor++;
		} else {
			this.valor = 0;
		}
		entGrafica.actualizarImagen(this.valor);
	}

	// -------------------- SETTERS --------------------------//

	/**
	 * Le asigna un valor en concreto a la celda y por consecuencia, su nuevo
	 * grafico
	 * 
	 * @param val valor nuevo
	 */
	public void setValor(int val) {
		if (val <= this.getCantElementos()) {
			this.valor = val;
			this.entGrafica.actualizarImagen(this.valor);
		}
	}

	/**
	 * Le asigna a una celda una entidad grafica
	 * 
	 * @param enti entidad grafica
	 */
	public void setEntiGrafica(Entidad_Grafica enti) {
		entGrafica = enti;
	}

	/**
	 * Le asigna a una celda si tiene un error o no
	 * 
	 * @param val valor de veracidad
	 */
	public void setTieneError(boolean val) {
		tieneError = val;
	}

	/**
	 * Le asigna una etiqueta grafica a la celda
	 * 
	 * @param eti etiqueta grafica
	 */
	public void setEtiqueta(JLabel eti) {
		etiqueta = eti;
	}

	// -------------------- GETTERS --------------------------//

	/**
	 * Retorna la cantidad de imagenes disponibles que tiene la entidad grafica
	 * 
	 * @return
	 */
	public int getCantElementos() {
		return this.entGrafica.getImagenes().length;
	}

	/**
	 * Retorna el valor de la celda
	 * 
	 * @return valor de la celda
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Retorna la entidad grafica que posee la celda
	 * 
	 * @return entidad grafica celda
	 */
	public Entidad_Grafica getEntiGrafica() {
		return entGrafica;
	}

	/**
	 * Retorna si la celda presenta un error
	 * 
	 * @return true si la celda tiene un error. Caso contrario, false.
	 */
	public boolean getTieneError() {
		return tieneError;
	}

	/**
	 * Retorna la etiqueta asociada a la celda
	 * 
	 * @return etiqueta de la celda
	 */
	public JLabel getEtiqueta() {
		return etiqueta;
	}

}
