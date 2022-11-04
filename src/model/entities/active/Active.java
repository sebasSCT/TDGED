package model.entities.active;

import java.awt.Point;
import model.entities.Entity;
import model.staticTools.vars;

public abstract class Active extends Entity
{

	protected double vel, ps, acl;
	protected double wg;
	protected int g = vars.gravity;
	protected boolean falling, walking;

	public Active ( String id, Point pos, double ps, double vel, double maxVel, Point offset )
	{
		super(id, pos, offset);
		this.vel = vel;
		this.wg = maxVel;
		this.ps = ps;
		this.acl = 0;
	}

	public double getMaxVel ()
	{
		return wg;
	}

	public double getAcl ()
	{
		return acl;
	}

	public void setAcl ( double acl )
	{
		this.acl = acl;
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
