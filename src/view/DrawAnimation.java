package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Hashtable;
import model.entities.active.Active;

public class DrawAnimation
{

	private Hashtable<String, Float> types;

	private float time;
	private int ind = 1;
	private float timeSection;
	private float cont;

	private String type;

	private Active e;

	public DrawAnimation ( Active e )
	{
		this.e = e;
		this.types = new Hashtable<>();
		startTypes();

		System.out.println("DrawAnimation " + e.getID());
	}

	private void startTypes ()
	{
		// idle
		types.put("a0", (float) 1); // right
		types.put("a1", (float) 1); // left

		// walk
		types.put("a2", (float) 0.5); // right
		types.put("a3", (float) 0.5); // left

		// falling
		types.put("a4", (float) 0); // replace
		types.put("a5", (float) 0);
	}

	public void draw ( Graphics g )
	{
		time = (float) types.get(type);
		timeSection = time / (float) 4;

		switch ( type )
		{
			// LOOPANIMATIONS
			case "a0":
			case "a1":
			case "a2":
			case "a3":
				loopAnim(g);
				break;

			// STATICANIMATIONS
			case "a4":
			case "a5":
				staticAnim(g);
				break;
		}

		g.setColor(Color.white);
		// g.drawString("entity", e.getPos().x + 5, e.getPos().y + 5);

	}

	private void staticAnim ( Graphics g )
	{
		g.drawImage(e.getAnimations().get(type).get(0), e.getPos().x, e.getPos().y, null);
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

	public void setAnimation ( String type )
	{
		this.type = type;

	}

}
