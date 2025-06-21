package logic;

public class Grilla {
	private int[][] matriz;
	
	public Grilla() {	
	}
	
	public void cargarMatriz(int[][] nuevaMatriz) {
		this.matriz = nuevaMatriz;
	}
		
	public int[][] obtenerMatriz(){
		return matriz;
	}
	
	public int obtenerFilas() {
		return matriz.length;
	}
	
	public int obtenerColumnas() {
		return matriz[0].length;
	}
	
	public int obtenerValorEnPosicion(int fila, int columna) {
		return matriz[fila][columna];
	}	
}
