package logic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class GrillaTest {

	@Test
	public void grillaCargada() {	
		Grilla grilla = new Grilla();
		int[][] matriz = {{1, -1},{1,1}};
		grilla.cargarMatriz(matriz);
		
		assertArrayEquals(matriz, grilla.obtenerMatriz());
	}
	
	@Test
	public void cantidadFilasYColumnasCorrectas() {
		Grilla grilla = new Grilla();
		int[][] matriz = {{1,1},{-1,-1}};
		grilla.cargarMatriz(matriz);
		int filas = grilla.obtenerFilas();
		int columnas = grilla.obtenerColumnas();
		assertEquals(2, filas);
		assertEquals(2, columnas);
	}
	
	@Test
	public void hayValorEnPosicion() {
		Grilla grilla = new Grilla();
		int[][] matriz = {{-1,1},
						  {1,-1}};
		
		grilla.cargarMatriz(matriz);
		int valorEnPosicion = grilla.obtenerValorEnPosicion(0, 0);
		assertEquals(-1,valorEnPosicion);
	}
	
}
