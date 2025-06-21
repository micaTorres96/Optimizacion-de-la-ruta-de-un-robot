package logic;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class LectorDeArchivo {
	
	public static RutaDeRobot cargarDesdeArchivo(File archivo) {
        int[][] matriz = leerMatrizDesdeArchivo(archivo);
        RutaDeRobot ruta = new RutaDeRobot();
        ruta.obtenerGrillaRobot().cargarMatriz(matriz);
        return ruta;
    }

    private static int[][] leerMatrizDesdeArchivo(File archivo) {
        List<String> lineas;
        try {
            lineas = Files.readAllLines(archivo.toPath());
        } catch (Exception e) {
            throw new RuntimeException("No se pudo leer el archivo: " + e.getMessage());
        }

        List<int[]> filas = new ArrayList<>();
        int cantidadColumnas = -1;

        for (String linea : lineas) {
            String lineaLimpia = linea.trim();
            if (lineaLimpia.isEmpty()) continue;

            int[] fila = convertirLineaEnFilaDeEnteros(lineaLimpia);

            if (cantidadColumnas == -1) {
                cantidadColumnas = fila.length;
            } else if (fila.length != cantidadColumnas) {
                throw new IllegalArgumentException("Todas las filas deben tener la misma cantidad de columnas.");
            }

            filas.add(fila);
        }

        return filas.toArray(new int[0][]);
    }

    private static int[] convertirLineaEnFilaDeEnteros(String linea) {
        String[] valores = linea.split("\\s+");
        int[] fila = new int[valores.length];

        for (int i = 0; i < valores.length; i++) {
            fila[i] = convertirTextoANumero(valores[i]);
        }

        return fila;
    }

    private static int convertirTextoANumero(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor no numérico: '" + valor + "'");
        }
    }	
}