package controller.entities;

import java.awt.Graphics;

public abstract class InactiveEntityController extends EntityController
{

	public InactiveEntityController ()
	{
		super();
	}

	public void update ()
	{
		super.update();
	}

	public void draw ( Graphics g )
	{
		super.draw(g);
	}

}
