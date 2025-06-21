package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RutaDeRobotTest {
	@Test
	public void resultadosSinPodaPositivos() {
		int[][] matriz = {{1,1},{1,-1},{-1,1},{-1,-1}};
		RutaDeRobot ruta = new RutaDeRobot();
		ruta.cargarNuevaGrilla(matriz);
		ruta.buscarSinPoda();
		assertTrue(ruta.getLlamadasSinPoda() > 0);
		assertTrue(ruta.getTiempoSinPodaMs() < 10.0);
	}
	@Test
	public void resultadosConPodaPositivos() {
		int[][] matriz = {{1,1},{1,-1},{-1,1},{-1,-1}};
		RutaDeRobot ruta = new RutaDeRobot();
		ruta.cargarNuevaGrilla(matriz);
		ruta.buscarConPoda();
		assertTrue(ruta.getLlamadasConPoda() > 0);
		assertTrue(ruta.getTiempoConPodaMs() < 10.0);
	}
	
	@Test
	public void cantidadCorrectaDeObservadores() {
		int[][] matriz = {{1,1},{1,-1},{-1,1},{-1,-1}};
		RutaDeRobot ruta = new RutaDeRobot();
		ruta.cargarNuevaGrilla(matriz);
		ruta.buscarConPoda();
		ruta.buscarSinPoda();
		ruta.getPrimerCaminoValido();
		assertEquals(0, ruta.getObservers().size());
	}
	
	@Test
	public void PrimerCaminoValidoCorrecto() {
		int[][] matriz = {{1,1},{1,-1},{-1,1},{-1,-1}};
		RutaDeRobot ruta = new RutaDeRobot();
		ruta.cargarNuevaGrilla(matriz);
		assertNotNull(ruta.getPrimerCaminoValido());
	}
	
}
