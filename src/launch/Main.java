package launch;

import javax.swing.SwingUtilities;

import controller.Controlador;
import logic.RutaDeRobot;
import view.VentanaPrincipal;

public class Main {

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(() -> 
		{
			RutaDeRobot model = new RutaDeRobot();
			Controlador controller = new Controlador(model);
			VentanaPrincipal view = new VentanaPrincipal(controller);
			
			model.registrarObserver(view);
			view.frame.setVisible(true);
		});
	}
}
