package controller;

import java.awt.Point;
import controller.scene.SceneController;
import view.DrawCanvas;
import view.Window;

// App controller o game controller (decision)
//Draw Update Controller
public class DUController
{

	private DrawCanvas c;
	private Window w;

	private SceneController sc;

	public DUController ( Point size, String title )
	{
		c = new DrawCanvas(size);
		w = new Window(title, c);
		sc = new SceneController();
		System.out.println("DUController");
	}

	// Actualizar
	public void update ()
	{
		sc.update();
	}

	// Dibujar
	public void draw ()
	{
		c.draw(sc);
	}

	// Metodo para cerrar el juego
	// metodo drawDebug ?????
}
