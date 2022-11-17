
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
					staticAnim(g);
					break;

				case "once":
					onceAnim(g);
					break;
			}
		}
	}

	private boolean reset = false;
	private void onceAnim ( Graphics g )
	{
		if ( reset )
		{
			ind = 1;
			cont = 0;
			reset = false;
		}

		cont += 0.016;

		g.drawImage(e.getAnimations().get(type).get(ind - 1), e.getPos().x, e.getPos().y,
				null);

		if ( cont >= timeSection * (float) ind && !(ind == frames) )
		{
			ind++;
		}
	}

	private void staticAnim ( Graphics g )
	{
		reset = true;
		g.drawImage(e.getAnimations().get(type).get((int) animTime), e.getPos().x,
				e.getPos().y, null);
	}

	private void loopAnim ( Graphics g )
	{
		reset = true;
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

	public void setAnimation ( String type, String anim, float time )
	{
		this.type = type;
		this.anim = anim;
		this.animTime = (float) time;
	}

	public void setAnimation ( String type, String anim, float time, int frames )
	{
		this.type = type;
		this.anim = anim;
		this.animTime = (float) time;
		this.frames = frames;
	}
}
