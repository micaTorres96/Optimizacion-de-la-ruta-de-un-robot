package logic;

public class Coordenada {

	private int fila, columna;
	
	public Coordenada(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public int obtenerFila() {
		return fila;
	}

	public int obtenerColumna() {
		return columna;
	}
}
