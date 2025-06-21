package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordenadaTest {

	@Test
	public void testObtenerFilaYColumna() {
		Coordenada coordenada = new Coordenada(4,3);
		int fila = coordenada.obtenerFila();
		int columna = coordenada.obtenerColumna();
		assertEquals(4,fila);
		assertEquals(3, columna);		
	}
}
