package view;

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
		types.put("a0", (float) 2);
		types.put("a1", (float) 0.5);
		types.put("a2", (float) 0);
	}

	public void draw ( Graphics g )
	{
		time = (float) types.get(type);
		timeSection = time / (float) 4;

		switch ( type )
		{
			// LOOPANIMATIONS
			case "a0":
				loopAnim(g);

			case "a1":
				loopAnim(g);
				break;

			// STATICANIMATIONS
			case "a2":
				staticAnim(g);
				break;
		}

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
