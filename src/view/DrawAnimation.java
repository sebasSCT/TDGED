
package view;

import java.awt.Graphics;
import java.util.Hashtable;
import model.entities.Entity;
import model.entities.active.Active;
import model.entities.active.Material;
import model.entities.inactive.Inactive;
import model.logic.dataStructure.Duplet;

public class DrawAnimation
{

	private Hashtable<String, Duplet<String, Float>> types;

	private float time;
	private int ind = 1;
	private float timeSection;
	private float cont;

	private String type;

	private Entity e;

	public DrawAnimation ( Active e )
	{
		this.e = e;
		this.types = new Hashtable<>();
		startTypes();

		System.out.println("DrawAnimation " + e.getID());
	}

	public DrawAnimation ( Inactive e )
	{
		this.e = e;
		this.types = new Hashtable<>();
		startTypes();

		System.out.println("DrawAnimation " + e.getID());
	}

	// add materials

	private void startTypes ()
	{
		// idle
		types.put("a0", new Duplet<String, Float>("loop", (float) 1)); // right
		types.put("a1", new Duplet<String, Float>("loop", (float) 1)); // left

		// walk
		types.put("a2", new Duplet<String, Float>("loop", (float) 0.5)); // right
		types.put("a3", new Duplet<String, Float>("loop", (float) 0.5)); // left

		// falling
		types.put("a4", new Duplet<String, Float>("static", (float) 0)); // replace
		types.put("a5", new Duplet<String, Float>("static", (float) 0));

		// carrying
		types.put("a6", new Duplet<String, Float>("loop", (float) 0.5)); // right
		types.put("a7", new Duplet<String, Float>("loop", (float) 0.5)); // left

		// carrying idle
		types.put("a8", new Duplet<String, Float>("loop", (float) 1)); // right
		types.put("a9", new Duplet<String, Float>("loop", (float) 1)); // left

		// materials
	}

	public void draw ( Graphics g )
	{

		if ( e instanceof Material )
		{
			staticSprite(g);
			return;
		}

		if ( types.get(type) != null )
		{
			time = (float) types.get(type).getB();
			timeSection = time / (float) 4;
		}

		switch ( types.get(type).getA() )
		{
			case "loop":
				loopAnim(g);
				break;

			case "static":
				staticAnim(g);
				break;
		}

	}

	private void staticAnim ( Graphics g )
	{
		g.drawImage(e.getAnimations().get(type).get(0), e.getPos().x, e.getPos().y, null);
	}

	private void staticSprite ( Graphics g )
	{
		g.drawImage(e.getSprite(), e.getPos().x, e.getPos().y, null);
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
