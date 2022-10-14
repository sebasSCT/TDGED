package model.entities;

import java.awt.Point;

public class Active extends Entity
{

	protected boolean inColision;

	public Active ( String id, Point pos, double ps, double vel, Point offset )
	{
		super(id, pos, ps, vel, offset);
	}

	public boolean isInColision ()
	{
		return inColision;
	}

	public void setInColision ( boolean inColision )
	{
		this.inColision = inColision;
	}

}
