package view;

import java.awt.Graphics;
import model.entities.active.Active;

// WORK IN PROGRESS NOSIRVE
public class DrawAnimation
{

	private float time = (float) 0.5;
	private int ind = 1;
	private float timeSection;
	private float cont;

	private String type;

	private Active e;

	public DrawAnimation ( Active e )
	{
		this.e = e;

		timeSection = time / (float) 4;

		System.out.println("DrawAnimation " + e.getID());
	}

	public void draw ( Graphics g )
	{

		// switch ( type )
		// {
		// case "loop":
		loopAnim(g);
		// break;
		// }

		// g.drawString("ind: " + ind, 270, 220);
		// g.drawString("time: " + timeSection * (float) ind, 270, 230);

	}

	private void loopAnim ( Graphics g )
	{

		cont += 0.016;

		if ( timeSection * (float) ind > time )
		{
			cont = 0;
			ind = 1;
		}

		g.drawImage(e.getAnimations().get(type).get(ind - 1), e.getPos().x, e.getPos().y,
				null);

		if ( cont >= timeSection * (float) ind )
		{
			ind++;
		}

	}

	// test
	public boolean equals ( Active e )
	{
		return e.equals(this.e);
	}

	public void setAnimation ( String type )
	{
		this.type = type;

	}

}
