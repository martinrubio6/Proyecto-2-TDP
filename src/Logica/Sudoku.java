package Logica;

import java.io.*;
import java.util.Random;

public class Sudoku {

	private Celda[][] tablero;
	private static int cantFilas = 9;
	private static int cantColumnas = 9;
	private boolean estadoInicialCorrecto=false;

	public Sudoku() {
		String linea;
		String[] palabras;
		int fil = 0;
		Random miRandom = new Random();
		int N;

		/**
		 * Creo el tablero
		 */

		tablero = new Celda[cantFilas][cantColumnas];
		for (int i = 0; i < cantFilas; i++) {
			for (int j = 0; j < cantColumnas; j++) {
				tablero[i][j] = new Celda();
			}
		}

		/**
		 * Inserto los elementos del archivo de texto en el tablero
		 */

		try {
			String dirArchivo = "ArchivoDeTexto/estadoInicial.txt";
			InputStream in = Sudoku.class.getClassLoader().getResourceAsStream(dirArchivo);
			InputStreamReader isr = new InputStreamReader(in);
			if (in != null) {
				BufferedReader br = new BufferedReader(isr);
				while ((linea = br.readLine()) != null) {
					if (!linea.isEmpty()) {
						palabras = linea.split(" ");
						for (int col = 0; col < palabras.length; col++) {
							tablero[fil][col].setValor(Integer.parseInt(palabras[col]));
						}
						fil++;
					}
				}
				br.close();
			}
		} catch (IOException e) {
			System.out.println("Error al procesar el archivo");
		}

		/**
		 * Verifico que el archivo era una solucion valida y creo el estado inicial eliminando celdas
		 */

		if (comprobarSolucion()) {
			estadoInicialCorrecto = true;
			for (int filTablero = 0; filTablero < getCantFilas(); filTablero++) {
				for (int colTablero = 0; colTablero < getCantColumnas(); colTablero++) {
					N = miRandom.nextInt(10);
					if ((N <= 5)) {
						tablero[filTablero][colTablero].setValor(0);
					}
				}
			}
		}

	}// Fin del constructor

	/**
	 * Le manda el mensaje a una Celda para que vaya actualizando su valor y su
	 * grafico correspondiente
	 * 
	 * @param c
	 */
	public void accionar(Celda c) {
		c.actualizarCelda();
	}

	/**
	 * Determina si la solucion del archivo de texto, era valida o no
	 * 
	 * @return true si la solucion es valida. Caso contrario, false
	 */
	public boolean EstadoInicialCorrecto() {
		return estadoInicialCorrecto;
	}

	/**
	 * Retorna la celda que se encuentra en una fila y columa especifica
	 * 
	 * @param i valor de la fila
	 * @param j valor de columna
	 * @return celda del tablero
	 */
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}

	/**
	 * Retorna la cantidad de filas del tablero
	 * 
	 * @return cantidad de filas
	 */
	public int getCantFilas() {
		return cantFilas;
	}

	/**
	 * Retorna la cantidad de columnas del tablero
	 * 
	 * @return cantidad de columnas
	 */
	public int getCantColumnas() {
		return cantColumnas;
	}

	/**
	 * Revisa que la celda no se repita en una fila
	 * 
	 * @param fila    donde se encuentra la celda y a revisar si se repite la celda
	 * @param columna donde se encuentra la celda
	 * @param num     valor de la celda
	 * @return true si la celda se repite, caso contrario false.
	 */
	public boolean estaEnFila(int fila, int columna, int num) {
		boolean esta = false;

		tablero[fila][columna].setTieneError(false);

		if (num == 0) {
			esta = true;
			tablero[fila][columna].setTieneError(true);
		}

		for (int col = 0; col < getCantColumnas(); col++) {
			// Este if es para saltearme la celda que ya contiene el numero y que de esa
			// forma no me diga que esta repetido//
			if (col != columna) {
				if (num == tablero[fila][col].getValor()) {
					esta = true;
					tablero[fila][columna].setTieneError(true);// Si anda mal, cambiar columna por col
					// System.out.println("Entre en el estaEnFila() por la celda en la fila:
					// "+fila+" y columna: "+col);
				}

			}
		}
		return esta;
	}

	/**
	 * Revisa que la celda no se repita en una columna
	 * 
	 * @param col  donde se encuentra la celda y a revisar si se repite la celda
	 * @param fila donde se encuentra la celda
	 * @param num  valor de la celda
	 * @return true si la celda se repite, caso contrario false.
	 */
	public boolean estaEnColumna(int col, int fila, int num) {
		boolean esta = false;

		tablero[fila][col].setTieneError(false);

		if (num == 0) {
			esta = true;
			tablero[fila][col].setTieneError(true);
		}

		for (int fil = 0; fil < getCantFilas(); fil++) {
			// Este if es para saltearme la celda que ya contiene el numero y que de esa
			// forma no me diga que esta repetido//
			if (fil != fila) {
				if (num == tablero[fil][col].getValor()) {
					esta = true;
					tablero[fila][col].setTieneError(true);// Si anda mal cambiar fila por fil
					// System.out.println("Entre en el estaEnColumna() por la celda en la fila:
					// "+fil+" y columna: "+col);
				}

			}
		}
		return esta;
	}

	/**
	 * Revisa que la celda no se repita en un cuadrante
	 * 
	 * @param fila    donde se encuentra la celda
	 * @param columna donde se encuentra la celda
	 * @param numero  valor de la celda
	 * @return true si la celda se repite, caso contrario false;
	 */
	public boolean estaEnCuadrante(int fila, int columna, int numero) {
		boolean esta = false;
		int cantFilaSubpanel = 3;
		int filacuadrante = ((int) (fila / cantFilaSubpanel)) * cantFilaSubpanel;
		int columnacuadrante = ((int) (columna / cantFilaSubpanel)) * cantFilaSubpanel;

		tablero[fila][columna].setTieneError(false);

		if (numero == 0) {
			esta = true;
			tablero[fila][columna].setTieneError(true);
		}

		for (int i = filacuadrante; i < filacuadrante + 3; i++) {
			for (int j = columnacuadrante; j < columnacuadrante + 3 && !esta; j++) {
				// Este if es para saltearme la celda que ya contiene el numero y que de esa
				// forma no me diga que esta repetida//
				if (i != fila && j != columna) {
					if (numero == tablero[i][j].getValor()) {
						esta = true;
						tablero[fila][columna].setTieneError(true);
						// System.out.println("Entre en el estaEnCuadrante() por la celda en la fila:
						// "+i+" y columna: "+j);
					}
				}
			}
		}

		return esta;
	}

	/**
	 * Comprueba que cada celda del tablero no se repita en una fila,columna o
	 * cuadrante.
	 * 
	 * @return true si no hay celdas repetidas en una fila,columna o cuadrante. Caso
	 *         contrario, false.
	 */
	public boolean comprobarSolucion() {
		boolean correcto = true;
		for (int fila = 0; fila < getCantFilas(); fila++) {
			for (int columna = 0; columna < getCantColumnas(); columna++) {
				if (estaEnFila(fila, columna, tablero[fila][columna].getValor())
						|| estaEnColumna(columna, fila, tablero[fila][columna].getValor())
						|| estaEnCuadrante(fila, columna, tablero[fila][columna].getValor())) {
					correcto = false;
				}
			}
		}
		return correcto;
	}
}
