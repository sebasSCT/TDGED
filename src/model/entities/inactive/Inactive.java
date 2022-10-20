package model.entities.inactive;

import java.awt.Point;
import model.entities.Entity;

public abstract class Inactive extends Entity
{

	public Inactive ( String id, Point pos, Point offset )
	{
		super(id, pos, offset);
	}

}
