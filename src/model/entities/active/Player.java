package model.entities.active;

import java.awt.Point;

public class Player extends Active
{

	private boolean carrying;
	private int carry;

	public Player ( String id, Point pos, double ps, double vel, double maxVel, Point offset )
	{
		super(id, pos, ps, vel, maxVel, offset);
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

}
