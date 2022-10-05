package controller;

import java.awt.Point;
import model.staticTools.vars;
import view.DrawCanvas;
import view.Window;

// App controller o game controller (decision)
public class DUController
{

	private DrawCanvas c;
	private Window w;

	// mover a scene controller
	MapController mc = new MapController();

	public DUController ( Point size, String title )
	{
		mc.loadMap(vars.map0);
		c = new DrawCanvas(size, mc);
		w = new Window(title, c);
		System.out.println("DUController");

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
