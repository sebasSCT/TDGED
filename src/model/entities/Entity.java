package model.entities;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import model.logic.ColisionBox;
import model.staticTools.vars;

public abstract class Entity
{

	protected String id;
	protected Point pos;
	protected ColisionBox cb;
	protected Point offset;

	protected Hashtable<String, ArrayList<BufferedImage>> animations;

	public Entity ( String id, Point pos, Point offset )
	{
		this.id = id;
		this.pos = new Point(pos.x - offset.x, pos.y - offset.y);
		this.offset = offset;
		cb = new ColisionBox(	vars.entitySpriteSize + 8 - offset.x * 2,
								vars.entitySpriteSize + 2 - offset.y, pos.x + offset.x,
								pos.y + offset.y);
		// mas exactitud
		// (modificar)
		// No valores tan relativos.
		animations = new Hashtable<>();
		System.out.println("Entity");
	}

	public Hashtable<String, ArrayList<BufferedImage>> getAnimations ()
	{
		return animations;
	}

	public void setPos ( double x, double y )
	{
		pos.setLocation(x - offset.x, y - offset.y);
		// pos.y = y;
		cb.setBox(pos.x - 4 + offset.x, pos.y + offset.y);
	}

	public ColisionBox getCB ()
	{
		return cb;
	}

	public Point getPos ()
	{
		return pos;
	}

}
