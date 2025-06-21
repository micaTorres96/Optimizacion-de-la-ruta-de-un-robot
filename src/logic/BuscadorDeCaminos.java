package logic;

import java.util.ArrayList;
import java.util.List;

public class BuscadorDeCaminos {
    private final Grilla grilla;
    private int llamadas;
    private final List<List<Coordenada>> caminosEncontrados = new ArrayList<>();

    public BuscadorDeCaminos(Grilla grilla) {
        this.grilla = grilla;
    }

    public void buscar(boolean usarPoda) {
        caminosEncontrados.clear();
        llamadas = 0;
        buscarDesde(0, 0, 0, usarPoda, new ArrayList<>());
    }

    private void buscarDesde(int fila, int columna, int suma, boolean usarPoda, List<Coordenada> caminoActual) {
        llamadas++;

        int n = grilla.obtenerFilas();
        int m = grilla.obtenerColumnas();

        if (fila >= n || columna >= m) return;

        suma += grilla.obtenerValorEnPosicion(fila, columna);
        caminoActual.add(new Coordenada(fila, columna));

        if (fila == n - 1 && columna == m - 1) {
            if (suma == 0) {
                caminosEncontrados.add(new ArrayList<>(caminoActual));
            }
            caminoActual.remove(caminoActual.size() - 1);
            return;
        }

        int pasosRestantes = (n - 1 - fila) + (m - 1 - columna);
        if (usarPoda && Math.abs(suma) > pasosRestantes) {
            caminoActual.remove(caminoActual.size() - 1);
            return;
        }

        buscarDesde(fila + 1, columna, suma, usarPoda, caminoActual);
        buscarDesde(fila, columna + 1, suma, usarPoda, caminoActual);

        caminoActual.remove(caminoActual.size() - 1);
    }

    public int getCantidadDeLlamadas() {
        return llamadas;
    }

    public List<List<Coordenada>> getCaminosValidos() {
        return new ArrayList<>(caminosEncontrados); // Copia defensiva
    }
}

