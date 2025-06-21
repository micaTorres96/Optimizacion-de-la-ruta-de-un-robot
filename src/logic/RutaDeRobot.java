package logic;

import java.util.ArrayList;
import java.util.List;
import view.ObserverRuta;

public class RutaDeRobot {
	private Grilla grilla;
	private int cantidadDecaminosValidos;
	private int cantidadDeLlamadasRecursivas;
	private List<Coordenada> primerCaminoValido;
	private List<List<Coordenada>> caminosEncontrados;
	private List<ObserverRuta> observerRutas;
	
	private int caminosValidosSinPoda;
	private int llamadasSinPoda;
	private long tiempoDeEjecucionSinPoda;
	
	private int caminosValidosConPoda;
	private int llamadasConPoda;
	private long tiempoDeEjecucionConPoda;

	public RutaDeRobot() {
		this.grilla = new Grilla();
		this.observerRutas = new ArrayList<>();
		this.primerCaminoValido = null;
		this.caminosEncontrados = new ArrayList<>();
	}
	
	public Grilla obtenerGrillaRobot(){
		return grilla;
	}
	
	public void cargarNuevaGrilla(int[][] nuevaMatriz) {
		grilla.cargarMatriz(nuevaMatriz);
		notificarObservadores();
	} 
	
	public void buscarSinPoda() {
		ejecutarBusqueda(false);
	}

	public void buscarConPoda() {
		ejecutarBusqueda(true);
	}
	
	private void ejecutarBusqueda(boolean conPoda) {
		this.primerCaminoValido = null;
		this.caminosEncontrados.clear();
		
		BuscadorDeCaminos buscador = new BuscadorDeCaminos(grilla);
		
		long inicio = System.nanoTime();
		buscador.buscar(conPoda);
		long fin = System.nanoTime();
		
		this.cantidadDecaminosValidos = buscador.getCaminosValidos().size();
		this.cantidadDeLlamadasRecursivas = buscador.getCantidadDeLlamadas();
		this.caminosEncontrados = buscador.getCaminosValidos();
		
		if (!caminosEncontrados.isEmpty()) {
			this.primerCaminoValido = caminosEncontrados.get(0);
		}

		if (conPoda) {
			this.tiempoDeEjecucionConPoda = fin - inicio;
			this.llamadasConPoda = this.cantidadDeLlamadasRecursivas;
			this.caminosValidosConPoda = this.cantidadDecaminosValidos;
		} else {
			this.tiempoDeEjecucionSinPoda = fin - inicio;
			this.llamadasSinPoda = this.cantidadDeLlamadasRecursivas;
			this.caminosValidosSinPoda = this.cantidadDecaminosValidos;
		}
		
		notificarObservadores();
	}
	
	public List<int[]> getPrimerCaminoValido(){
		List<int[]> camino = new ArrayList<>();
		if(primerCaminoValido == null) return camino;
		for(Coordenada coordenada : primerCaminoValido) {
			camino.add(new int[] {coordenada.obtenerFila(), coordenada.obtenerColumna()});
		}
		return camino;
	}
	
	public int getCaminosValidosConPoda() {
		return caminosValidosConPoda;
	}

	public int getCaminosValidosSinPoda() {
		return caminosValidosSinPoda;
	}

	public int getLlamadasConPoda() {
		return llamadasConPoda;
	}

	public int getLlamadasSinPoda() {
		return llamadasSinPoda;
	}

	public double getTiempoConPodaMs() {
		return tiempoDeEjecucionConPoda / 1_000_000.0;
	}

	public double getTiempoSinPodaMs() {
		return tiempoDeEjecucionSinPoda / 1_000_000.0;
	}

	public List<ObserverRuta> getObservers(){
		return new ArrayList<>(observerRutas);
	}

	public void registrarObserver(ObserverRuta observerRuta) {
		if(!observerRutas.contains(observerRuta)) {
			observerRutas.add(observerRuta);
		}
	}

	public void notificarObservadores() {
		for (ObserverRuta observerRuta : observerRutas) {
			observerRuta.notificar();
		}
	}
}