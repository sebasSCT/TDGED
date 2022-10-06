package controller;

import java.awt.Point;
import view.DrawCanvas;
import view.Window;

// App controller o game controller (decision)
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

	// private int i = 500;
	public void update ()
	{
		// i -= 5;
		// if ( i == 250 )
		// {
		// sc.setCS(1);
		// }
		// if ( i == 0 )
		// {
		// sc.setCS(0);
		// i = 500;
		// }
	}

	public void draw ()
	{
		c.draw(sc);
	}

	// Metodo para cerrar el juego
	// metodo drawDebug ?????
}
