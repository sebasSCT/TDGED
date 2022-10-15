package model.entities.active;

import java.awt.Point;
import model.entities.Entity;

public class Active extends Entity
{

	protected boolean inColision;// Eliminar?

	protected double vel;
	protected double ps;

	public Active ( String id, Point pos, double ps, double vel, Point offset )
	{
		super(id, pos, offset);
		this.vel = vel;
		this.pos = pos;
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
