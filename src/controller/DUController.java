package controller;

import com.sun.javafx.geom.Point2D;
import view.DrawCanvas;
import view.Window;

// App controller o game controller (decision)
public class DUController
{

	private DrawCanvas c;
	private Window w;

	public DUController ( Point2D size, String title )
	{
		c = new DrawCanvas(size);
		w = new Window(title, c);
	}

	public void update ()
	{

	}

	public void draw ()
	{
		c.draw();
	}

	// Metodo para cerrar el juego
	// metodo drawDebug ?????
}
