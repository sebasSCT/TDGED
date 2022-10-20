package model.entities.active;

import java.awt.Point;
import model.entities.Entity;
import model.staticTools.vars;

public abstract class Active extends Entity
{

	protected boolean inColision;// Eliminar?

	protected double vel, ps;
	protected int g = vars.gravity;
	protected boolean falling, walking;

	public Active ( String id, Point pos, double ps, double vel, Point offset )
	{
		super(id, pos, offset);
		this.vel = vel;
		this.pos = pos;
	}

	// Eliminar ? ****
	public boolean isInColision ()
	{
		return inColision;
	}

	public void setInColision ( boolean inColision )
	{
		this.inColision = inColision;
	}
	// ***********

	public void fall ()
	{
		pos.y += g * vars.delta;
		g += (g >= 4) ? 0 : 1;
		cb.setBox(pos.x + offset.x, pos.y + offset.y);
		falling = true;
	}

	public void move ( String direction )
	{
		switch ( direction )
		{
			case "left":
				pos.x -= vel;
				cb.setBox(pos.x + offset.x, pos.y + offset.y);
				break;

			case "right":
				pos.x += vel;
				cb.setBox(pos.x + offset.x, pos.y + offset.y);
				break;

			case "up":
				pos.y -= vel;
				cb.setBox(pos.x + offset.x, pos.y + offset.y);
				break;

			// case "down":
			// pos.y += vel * vars.delta;
			// cb.setBox(pos.x + offset.x, pos.y + offset.y);
			// break;
		}
	}

	public double getVel ()
	{
		return vel;
	}

	public void setVel ( double vel )
	{
		this.vel = vel;
	}

	public double getPs ()
	{
		return ps;
	}

	public void setPs ( double ps )
	{
		this.ps = ps;
	}

	public int getG ()
	{
		return g;
	}

	public void setG ( int g )
	{
		this.g = g;
	}

	public boolean isFalling ()
	{
		return falling;
	}

	public void setFalling ( boolean moving )
	{
		this.falling = moving;
	}

	public boolean isWalking ()
	{
		return walking;
	}

	public void setWalking ( boolean walking )
	{
		this.walking = walking;
	}

}
