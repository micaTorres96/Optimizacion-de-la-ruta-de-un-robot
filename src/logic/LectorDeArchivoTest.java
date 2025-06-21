package logic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.Test;

public class LectorDeArchivoTest {
	
	 @Test
	 public void cargarDesdeArchivoValidoDevuelveRutaCorrecta() {
	     try {
	         File archivo = File.createTempFile("grillaTest", ".txt");
	         archivo.deleteOnExit();

	         Files.write(archivo.toPath(), List.of("1 -1", "1 1"));

	         RutaDeRobot ruta = LectorDeArchivo.cargarDesdeArchivo(archivo);

	         int[][] esperada = {
	             {1, -1},
	             {1, 1}
	         };

	         assertArrayEquals(esperada, ruta.obtenerGrillaRobot().obtenerMatriz());

	     } catch (Exception e) {
	         fail("Se lanzó una excepción inesperada: " + e.getMessage());
	     }
	 }

	 @Test
	 public void matrizLeida()  {
	  try {
	   File archivo = File.createTempFile("grillaTest", ".txt");
	   Files.write(archivo.toPath(), List.of(
	     "1 -1 0",
	     "0 1 -1",
	     "-1 0 1"
	    ));
	   
	   RutaDeRobot ruta = LectorDeArchivo.cargarDesdeArchivo(archivo);
	   int[][] matriz = ruta.obtenerGrillaRobot().obtenerMatriz();
	   
	   assertEquals(3, matriz.length);
	   assertEquals(3, matriz[0].length);
	   assertEquals(-1, matriz[1][2]); 
	   } catch (Exception e) {
	   fail("Excepcion inesperada" + e.getMessage());
	   }
	  }
}
