package model.entities;

import java.awt.Point;
import model.entities.active.Active;

public class Player extends Active
{

	public Player ( String id, Point pos, double ps, double vel, Point offset )
	{
		super(id, pos, ps, vel, offset);
	}

}
