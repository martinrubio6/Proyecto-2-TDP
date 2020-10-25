package Logica;

import javax.swing.ImageIcon;

/**
 * Modela la el funcionamiento de la entidad grafica de una celda y sus
 * atributos
 * 
 * @author Martin Rubio
 *
 */
public class Entidad_Grafica {

	private ImageIcon imagen;
	private String[] imagenes;

	/**
	 * Crea una entidad grafica y las posibles imagenes que esta puede tomar
	 */
	public Entidad_Grafica() {
		this.imagen = new ImageIcon();
		this.imagenes = new String[] { "/img/Vacio.png", "/img/Uno.png", "/img/Dos.png", "/img/Tres.png",
				"/img/Cuatro.png", "/img/Cinco.png", "/img/Seis.png", "/img/Siete.png", "/img/Ocho.png",
				"/img/Nueve.png" };
	}

	/**
	 * Actualiza la imagen de una entidad grafica
	 * 
	 * @param indice posicion del arreglo con la imagen nueva
	 */
	public void actualizarImagen(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imagenNueva = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.imagen.setImage(imagenNueva.getImage());
		}
	}

	// ---------------------- SETTERS ---------------------//

	/**
	 * Asigna una imagen a la entidad grafica
	 * 
	 * @param imag Imagen nueva a insertar
	 */
	public void setImagen(ImageIcon imag) {
		this.imagen = imag;
	}

	/**
	 * Asigna una nueva coleccion de Imagenes disponibles para la entidad grafica
	 * 
	 * @param imags coleccion de imagenes
	 */
	public void setImagenes(String[] imags) {
		this.imagenes = imags;
	}

	// ---------------------- GETTERS ---------------------//

	/**
	 * Retorna la imagen que tiene la entidad grafica
	 * 
	 * @return imagen entidad grafica
	 */
	public ImageIcon getImagen() {
		return imagen;
	}

	/**
	 * Retorna la colecion de imagenes disponibles que puede tomar la entidad
	 * grafica
	 * 
	 * @return
	 */
	public String[] getImagenes() {
		return this.imagenes;
	}
}
