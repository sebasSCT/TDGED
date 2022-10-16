package application;

import java.awt.Point;
import controller.DUController;
import model.staticTools.vars;

public class TDMain
{

	private static DUController duc;

	public static void main ( String[] args )
	{
		startConfig();
		Loop.startLoop(duc);
	}

	private static void startConfig ()
	{
		duc = new DUController(	new Point((int) vars.W_SCREEN, (int) vars.H_SCREEN),
								vars.title);
	}

}
