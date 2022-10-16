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
		offset.x += 2;
		this.offset = offset;
		cb = new ColisionBox(	vars.entitySpriteSize - offset.x - 12,
								vars.entitySpriteSize - offset.y - 1, pos.x + offset.x,
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
		System.out.println("a");
		pos.setLocation(x * vars.spriteSize - offset.x, y * vars.spriteSize - offset.y);
		cb.setBox(pos.x + vars.screenOffSet - 4 + offset.x, pos.y + offset.y);
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
