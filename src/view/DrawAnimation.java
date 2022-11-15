
package view;

import java.awt.Graphics;
import model.entities.Entity;

public class DrawAnimation
{

	private float time, timeSection, cont, animTime;
	private String type, anim;
	private int ind = 1, frames = 4;

	private Entity e;

	public DrawAnimation ( Entity e )
	{
		this.e = e;

		System.out.println("DrawAnimation " + e.getID());
	}

	public void draw ( Graphics g )
	{
		if ( type != null )
		{
			time = (float) animTime;
			timeSection = (float) time / (float) frames;

			switch ( anim )
			{
				case "loop":
					loopAnim(g);
					break;

				case "static":
					staticSprite(g);
					break;
			}
		}
	}

	private void staticSprite ( Graphics g )
	{
		g.drawImage(e.getAnimations().get(type).get((int) animTime), e.getPos().x,
				e.getPos().y, null);
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

	public void setAnimation ( String type, String anim, float input )
	{
		this.type = type;
		this.anim = anim;
		this.animTime = (float) input;
	}

	public void setAnimation ( String type, String anim, float input, int frames )
	{
		this.type = type;
		this.anim = anim;
		this.animTime = (float) input;
		this.frames = frames;
	}
}
