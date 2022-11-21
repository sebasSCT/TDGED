package model.entities.inactive;

import java.awt.Point;
import model.entities.Entity;

public abstract class Inactive extends Entity
{

	private boolean[] colision;

	public Inactive ( String id, Point pos, Point offset )
	{
		super(id, pos, offset);

		this.colision = new boolean[2];
	}

	public boolean[] getColision ()
	{
		return colision;
	}

}
