package model.entities.active;

import java.awt.Point;

public class Material extends Active
{

	private boolean carry;

	public Material ( String id, Point pos, double maxVel, Point offset )
	{
		super(id, pos, 0, 0, maxVel, offset);
	}

	public boolean isCarry ()
	{
		return carry;
	}

	public void setCarry ( boolean carry )
	{
		this.carry = carry;
	}

}
