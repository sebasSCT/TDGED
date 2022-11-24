package model.threads;

import controller.DUController;

public class Draw implements Runnable
{

	DUController duc;

	public Draw ( DUController duc )
	{
		this.duc = duc;
	}

	@ Override
	public void run ()
	{

		duc.draw();

	}

}
