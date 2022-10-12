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
	protected double ps, vel;

	protected Hashtable<String, ArrayList<BufferedImage>> animations;

	public Entity ( String id, Point pos, double ps, double vel )
	{
		this.id = id;
		this.pos = pos;
		this.ps = ps;
		this.vel = vel;
		cb = new ColisionBox(5, vars.spriteSize - 6, pos.x + 5, pos.y + 4);// (modificar)
		animations = new Hashtable<>();
		System.out.println("Entity");
	}

	public Hashtable<String, ArrayList<BufferedImage>> getAnimations ()
	{
		return animations;
	}

	public void setPos ( int x, int y )
	{
		pos.x = x;
		pos.y = y;
		cb.setBox(pos.x + 5, pos.y + 4);
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
