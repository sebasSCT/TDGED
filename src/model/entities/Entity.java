package model.entities;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import model.logic.ColisionBox;

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
	}

	public Point getPos ()
	{
		return pos;
	}

}
