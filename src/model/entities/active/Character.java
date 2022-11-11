package model.entities.active;

import java.awt.Point;

public class Character extends Active
{

	public Character (	String id, Point pos, double ps, double vel, double maxVel,
						Point offset )
	{
		super(id, pos, ps, vel, maxVel, offset);
	}

}
