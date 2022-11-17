package model.entities.active;

import java.awt.Point;

public class Enemy extends Active
{

	public Enemy ( String id, Point pos, double ps, double vel, double maxVel, Point offset )
	{
		super(id, pos, ps, vel, maxVel, offset);
	}

}
