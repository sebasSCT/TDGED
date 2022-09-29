package application;

import controller.DUController;
import model.staticTools.vars;

public class TDMain
{

	private static Loop l;
	private static DUController duc;

	public static void main ( String[] args )
	{
		startConfig();
		l.startLoop(duc);
	}

	private static void startConfig ()
	{
		l = new Loop();
		duc = new DUController(vars.size, vars.title);
	}

}
