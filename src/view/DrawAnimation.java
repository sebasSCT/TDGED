package view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// WORK IN PROGRESS NOSIRVE
public class DrawAnimation
{

	private int time;
	private ArrayList<BufferedImage> imgs;
	private String type;
	private float timeSection;
	private Point pos;

	public DrawAnimation ()
	{
		this.imgs = new ArrayList<>();
	}

	private float cont;
	private float ind = 1;
	public void draw ( Graphics g )
	{
		if ( !imgs.isEmpty() )
		{
			switch ( type )
			{
				case "loop":
					loopAnim(g);
					break;
			}
		}
	}

	private void loopAnim ( Graphics g )
	{

		cont += 0.016;
		// System.out.println("a: " + ind + " cont: " + cont + " timeSection: "
		// + timeSection);
		// System.out.println(timeSection * ind);
		if ( timeSection * ind >= time )
		{
			cont = 0;
			ind = 1;
		}
		if ( cont >= timeSection * ind )
		{
			// System.out.println("si");
			g.drawImage(imgs.get((int) ind - 1), pos.x, pos.y, null);
			ind++;
		}
	}

	public void setAnimation (	String type, int time, ArrayList<BufferedImage> imgs,
								Point pos )
	{
		this.type = type;
		this.time = time;
		this.imgs = imgs;
		this.pos = pos;

		timeSection = (float) time / (float) imgs.size();
	}

}
