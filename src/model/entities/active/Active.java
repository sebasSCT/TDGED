package model.entities.active;

import java.awt.Point;
import model.entities.Entity;
import model.staticTools.vars;

public abstract class Active extends Entity
{

	protected double vel, acl;
	protected double wg;
	protected int g = vars.gravity, carry, ps;
	protected boolean falling, walking, carrying, ladder;

	public Active ( String id, Point pos, int ps, double vel, double maxVel, Point offset )
	{
		super(id, pos, offset);
		this.vel = vel;
		this.wg = maxVel;
		this.ps = ps;
		this.acl = 0;
	}

	public boolean isLadder ()
	{
		return ladder;
	}

	public void setLadder ( boolean ladder )
	{
		this.ladder = ladder;
	}

	public boolean isCarrying ()
	{
		return carrying;
	}

	public void setCarrying ( boolean carrying )
	{
		this.carrying = carrying;
	}

	public int getCarry ()
	{
		return carry;
	}

	public void setCarry ( int carry )
	{
		this.carry = carry;
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

	public int getPs ()
	{
		return ps;
	}

	public void setPs ( int ps )
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
