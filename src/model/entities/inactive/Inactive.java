package model.entities.inactive;

import java.awt.Point;
import model.entities.Entity;

public abstract class Inactive extends Entity
{

	private boolean[] colision;
	private boolean cooldown;

	public Inactive ( String id, Point pos, Point offset )
	{
		super(id, pos, offset);

		this.colision = new boolean[2];
	}

	public boolean isCooldown ()
	{
		return cooldown;
	}

	public void setCooldown ( boolean cooldown )
	{
		this.cooldown = cooldown;
	}

	public boolean[] getColision ()
	{
		return colision;
	}

}
