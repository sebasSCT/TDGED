package model.entities.active;

import java.awt.Point;

public class Enemy extends Active
{

	private int damage;
	private boolean attacking = false;

	public Enemy (	String id, Point pos, int ps, double vel, double maxVel, Point offset,
					int damage )
	{
		super(id, pos, ps, vel, maxVel, offset);
		this.damage = damage;
	}

	public int getDamage ()
	{
		return damage;
	}

	public boolean isAttacking ()
	{
		return attacking;
	}

	public void setAttacking ( boolean attacking )
	{
		this.attacking = attacking;
	}

}
