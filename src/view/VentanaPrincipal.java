package view;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import controller.Controlador;

public class VentanaPrincipal implements ObserverRuta{
	public JFrame frame;
	private JPanel panel;	
	private Controlador controller;
	private JButton cargarArchivo;
	private JButton primerCaminoValido; 
	private JButton botonGenerarCaminoSinPoda; 
	private JButton botonGenerarCaminoConPoda; 
	private JTextArea infoArea;
	
	public VentanaPrincipal(Controlador controller) 
	{
		this.controller = controller;
		initialize();
		controller.registrarObserver(this);
	}
	
	private void initialize() 
	{
		frame = new JFrame("TP N°3 - Optimizacion de la ruta de un robot");
		frame.setBounds(200, 25, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		cargarArchivo = new JButton("Cargar Archivo");
		cargarArchivo.setBounds(370,20,160,40);
		cargarArchivo.setFont(new Font("SansSerif", Font.BOLD, 14));
		cargarArchivo.addActionListener(e -> {
			JFileChooser elegirArchivo = new JFileChooser();
			int resultado = elegirArchivo.showOpenDialog(frame);
			
			if(resultado == JFileChooser.APPROVE_OPTION) {
				java.io.File Archivo = elegirArchivo.getSelectedFile();
				controller.cargarArchivo(Archivo);
			}	
		});
		
		panel = new JPanel();
		panel.setBackground(Color.darkGray);
		panel.setBounds(100, 80, 700, 350);
		panel.setLayout(new GridLayout());
		
		primerCaminoValido = new JButton("Primer camino valido");
		primerCaminoValido.setBounds(80,450,200,40);
		primerCaminoValido.setFont(new Font("SansSerif", Font.PLAIN, 13));
		primerCaminoValido.addActionListener(e -> {
		controller.generarCaminoSinPoda();
		});
		
		botonGenerarCaminoSinPoda = new JButton("Camino sin poda");
		botonGenerarCaminoSinPoda.setBounds(350,450,200,40);
		botonGenerarCaminoSinPoda.setFont(new Font("SansSerif", Font.PLAIN, 13));
		botonGenerarCaminoSinPoda.addActionListener(e -> {
		controller.generarCaminoSinPoda();
		});
		
		botonGenerarCaminoConPoda = new JButton("Camino con poda");
		botonGenerarCaminoConPoda.setBounds(620,450,200,40);
		botonGenerarCaminoConPoda.setFont(new Font("SansSerif", Font.PLAIN, 13));
		botonGenerarCaminoConPoda.addActionListener(e -> {
		controller.generarCaminoConPoda();
		});
	
		infoArea = new JTextArea();
		infoArea.setEditable(false);
		infoArea.setBounds(80,510,740,90);
		infoArea.setBackground(new Color(245,245,245));
		infoArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		infoArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		frame.add(panel);
		frame.add(cargarArchivo);
		frame.add(primerCaminoValido);
		frame.add(botonGenerarCaminoConPoda);
		frame.add(botonGenerarCaminoSinPoda);
		frame.add(infoArea);
		frame.setVisible(true);
	}

	public void notificar() {
		mostrarGrilla();
		mostrarResultados();
	}
	
	private void mostrarGrilla() {
		panel.removeAll();
		
		int filas = controller.obtenerFilas();
		int columnas = controller.obtenerColumnas();
		panel.setLayout(new GridLayout(filas, columnas,2,2));
		List<int[]> camino = controller.obtenerPrimerCaminoValido();
		
		for(int fila = 0; fila < filas; fila++) {
			for(int columna = 0; columna < columnas; columna++) {				
				panel.add(crearCelda(fila, columna, camino));
			}
		}
		panel.revalidate();
		panel.repaint();
	}
	
	private JLabel crearCelda(int fila, int columna, List<int[]> camino) {
		int valor = controller.obtenerValorEn(fila, columna);
		JLabel celda = new JLabel(String.valueOf(valor), SwingConstants.CENTER);
		celda.setOpaque(true);
		celda.setForeground(Color.BLACK);
		celda.setFont(new Font("SansSerif", Font.BOLD, 14));
		celda.setBackground(esParteDelCamino(fila, columna, camino) ? new Color(144,238,144) : Color.WHITE);
		celda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		return celda;
	}
	
	private boolean esParteDelCamino(int fila, int columna, List<int[]> camino) {
		for(int[] posicion : camino) {
			if(posicion[0] == fila && posicion[1] == columna) {
				return true;
			}
		}
		return false;
	}
	
	private void mostrarResultados() {
		String tamanio = controller.obtenerTamanioGrilla();
		double tiempoSinPoda = controller.obtenerTiempoSinPoda();
		int caminosSinPoda = controller.obtenerCaminosValidosSinPoda();
		int llamadasSinPoda = controller.obtenerLlamadasSinPoda();
		double tiempoConPoda = controller.obtenerTiempoConPoda();
		int caminosConPoda = controller.obtenerCaminosValidosConPoda();
		int llamadasConPoda = controller.obtenerLlamadasConPoda();
		
		String resumen = String.format(
				"Tamaño de la grilla: %s\n"
				+ "| Camino sin poda: %.2fms (%d caminos, %d llamadas)\n"
				+ "| Camino con poda: %2fms (%d caminos, %d llamadas)",
				tamanio, tiempoSinPoda, caminosSinPoda, llamadasSinPoda, 
				tiempoConPoda, caminosConPoda, llamadasConPoda);
		infoArea.setText(resumen);
	}	
}
