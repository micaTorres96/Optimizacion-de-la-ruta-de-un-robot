package logic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BuscadorDeCaminosTest {
	@Test
	public void caminoValidoExistente() {
		int[][] matriz = {{1,1},{1,-1},{-1,1},{-1,-1}};
		Grilla g = new Grilla();
		g.cargarMatriz(matriz);
		BuscadorDeCaminos bc = new BuscadorDeCaminos(g);
		bc.buscar(true);
		assertNotNull(bc.getCaminosValidos());
	}
	@Test
	public void cantidadDeRecursionesMenorA0() {
		int[][] matriz = {{1,1},{1,-1},{-1,1},{-1,-1}};
		Grilla g = new Grilla();
		g.cargarMatriz(matriz);
		BuscadorDeCaminos buscador = new BuscadorDeCaminos(g);
		buscador.buscar(true);
		assertTrue(buscador.getCantidadDeLlamadas() > 0);
	}
	
}
