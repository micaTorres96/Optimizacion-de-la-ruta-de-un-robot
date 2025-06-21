package controller;

import java.io.File;
import java.util.List;
import logic.LectorDeArchivo;
import logic.RutaDeRobot;
import view.ObserverRuta;

public class Controlador {
	private RutaDeRobot logic;
	
	public Controlador(RutaDeRobot logic) {
		this.logic = logic;
	}
	
	public int[][] obtenerGrilla() {
		return logic.obtenerGrillaRobot().obtenerMatriz();
	}
	
	public int obtenerFilas() {
		return logic.obtenerGrillaRobot().obtenerFilas();
	}
	
	public int obtenerColumnas() {
		return logic.obtenerGrillaRobot().obtenerColumnas();
	}
	
	public int obtenerValorEn(int fila, int columna) {
		return logic.obtenerGrillaRobot().obtenerValorEnPosicion(fila, columna);
	}
	
	public void cargarArchivo(File archivo) {
		RutaDeRobot nuevaRuta = LectorDeArchivo.cargarDesdeArchivo(archivo);
		if(nuevaRuta != null) {
			for(var obs : logic.getObservers()) {
				nuevaRuta.registrarObserver(obs);
			}
			this.logic = nuevaRuta;
			logic.notificarObservadores();
		}
	}
	
	public void registrarObserver(ObserverRuta observer) {
		this.logic.registrarObserver(observer);
	}
	
	public void generarCaminoSinPoda() {
		logic.buscarSinPoda();
	}
	
	public void generarCaminoConPoda() {
		logic.buscarConPoda();
	}
	
	public List<int[]> obtenerPrimerCaminoValido(){
		return logic.getPrimerCaminoValido();		
	}
	
	public String obtenerTamanioGrilla() {
	    return obtenerFilas() + " x " + obtenerColumnas();
	}

	public double obtenerTiempoSinPoda() {
	    return logic.getTiempoSinPodaMs();
	}

	public double obtenerTiempoConPoda() {
	    return logic.getTiempoConPodaMs();
	}

	public int obtenerCaminosValidosSinPoda() {
	    return logic.getCaminosValidosSinPoda();
	}

	public int obtenerCaminosValidosConPoda() {
	    return logic.getCaminosValidosConPoda();
	}

	public int obtenerLlamadasSinPoda() {
	    return logic.getLlamadasSinPoda();
	}

	public int obtenerLlamadasConPoda() {
	    return logic.getLlamadasConPoda();
	}
}
